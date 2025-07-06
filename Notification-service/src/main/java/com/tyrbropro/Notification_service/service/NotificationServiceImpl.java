package com.tyrbropro.Notification_service.service;

import com.tyrbropro.Notification_service.dto.OrderTopicDTO;
import com.tyrbropro.Notification_service.dto.notification.NotificationResponseDTO;
import com.tyrbropro.Notification_service.entity.Notification;
import com.tyrbropro.Notification_service.entity.NotificationRedis;
import com.tyrbropro.Notification_service.mapper.NotificationMapper;
import com.tyrbropro.Notification_service.mapper.NotificationRedisMapper;
import com.tyrbropro.Notification_service.repository.NotificationRedisRepository;
import com.tyrbropro.Notification_service.repository.NotificationRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRedisRepository notificationRedisRepository;
    private final NotificationRepository notificationRepository;
    private final NotificationRedisMapper notificationRedisMapper;
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRedisRepository notificationRedisRepository, NotificationRepository notificationRepository,
                                   NotificationRedisMapper notificationRedisMapper,
                                   NotificationMapper notificationMapper) {
        this.notificationRedisRepository = notificationRedisRepository;
        this.notificationRepository = notificationRepository;
        this.notificationRedisMapper = notificationRedisMapper;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public void processNotification(OrderTopicDTO dto) {
        Notification notification = new Notification();
        notification.setUserId(dto.userId());
        notification.setMessage(dto.message());
        notification.setType(dto.type());
        notification.setIsRead(false);
        notification.setCreatedAt(dto.time());

        Notification saved = notificationRepository.save(notification);
        notificationRedisRepository.save(notificationRedisMapper.toEntity(notificationMapper.toDTO(saved)));
    }

    @Override
    @Transactional
    public NotificationResponseDTO markAsRead(Long id) {
        return notificationRedisRepository.findById(id)
                .map(redisNotification -> {
                    redisNotification.setIsRead(true);
                    Notification entity = notificationMapper.toEntity(
                            notificationRedisMapper.toDTO(redisNotification)
                    );
                    notificationRedisRepository.deleteById(id);
                    Notification updated = notificationRepository.save(entity);
                    return notificationMapper.toDTO(updated);
                })
                .orElseGet(() -> notificationRepository.findById(id)
                        .map(entity -> {
                            entity.setIsRead(true);
                            Notification updated = notificationRepository.save(entity);
                            return notificationMapper.toDTO(updated);
                        })
                        .orElseThrow(() -> new RuntimeException("Notification not found")));
    }

    @Override
    @Transactional
    public List<NotificationResponseDTO> markAllAsRead(Long userId) {
        List<NotificationResponseDTO> updatedList = new ArrayList<>();

        Iterable<NotificationRedis> redisNotifications = notificationRedisRepository.findAllByUserIdAndIsReadFalse(userId);
        for (NotificationRedis redisNotification : redisNotifications) {
                redisNotification.setIsRead(true);

                Notification entity = notificationMapper.toEntity(
                        notificationRedisMapper.toDTO(redisNotification)
                );
                notificationRedisRepository.deleteById(entity.getId());
                Notification updated = notificationRepository.save(entity);

                updatedList.add(notificationMapper.toDTO(updated));
        }

        List<Notification> dbNotifications = notificationRepository.findAllByUserIdAndIsReadFalse(userId);
        for (Notification n : dbNotifications) {
            n.setIsRead(true);
            Notification updated = notificationRepository.save(n);
            updatedList.add(notificationMapper.toDTO(updated));
        }

        return updatedList;
    }
}
