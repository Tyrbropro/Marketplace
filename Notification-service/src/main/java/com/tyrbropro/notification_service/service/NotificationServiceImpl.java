package com.tyrbropro.notification_service.service;

import com.tyrbropro.notification_service.dto.OrderTopicDto;
import com.tyrbropro.notification_service.dto.notification.NotificationResponseDto;
import com.tyrbropro.notification_service.entity.Notification;
import com.tyrbropro.notification_service.entity.NotificationRedis;
import com.tyrbropro.notification_service.exception.NotificationNotFoundException;
import com.tyrbropro.notification_service.repository.NotificationRedisRepository;
import com.tyrbropro.notification_service.repository.NotificationRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor()
public class NotificationServiceImpl implements NotificationService {

    NotificationRedisRepository notificationRedisRepository;
    NotificationRepository notificationRepository;

    @Override
    public void processNotification(OrderTopicDto dto) {
        Notification notification = new Notification()
                .setUserId(dto.userId())
                .setMessage(dto.message())
                .setType(dto.type())
                .setIsRead(false)
                .setCreatedAt(dto.time());

        var saved = this.notificationRepository.save(notification);
        this.notificationRedisRepository.save(saved.toEntityCache());
    }

    @Override
    public NotificationResponseDto markAsRead(Long id) {
        var notification = this.notificationRedisRepository.findById(id)
                .map(n -> {
                    this.notificationRedisRepository.delete(n);
                    return n.toEntity();
                })
                .orElseGet(() -> this.notificationRepository.findById(id)
                        .orElseThrow(() -> new NotificationNotFoundException("Notification not found, id: " + id)));

        notification.markAsRead();
        var updated = this.notificationRepository.save(notification);
        return updated.toDto();
    }

    @Override
    public List<NotificationResponseDto> markAllAsRead(Long userId) {
        var redisNotifications =
                this.notificationRedisRepository.findAllByUserIdAndIsReadFalse(userId)
                        .stream()
                        .peek(this.notificationRedisRepository::delete)
                        .map(NotificationRedis::toEntity);

        var dbNotifications = this.notificationRepository.findAllByUserIdAndIsReadFalse(userId).stream();

        var notifications = Stream.concat(redisNotifications, dbNotifications)
                .collect(Collectors.toMap(
                        Notification::getId,
                        Notification::markAsRead,
                        (n1, n2) -> n2
                ))
                .values()
                .stream()
                .toList();

        this.notificationRepository.saveAll(notifications);

        return notifications.stream().map(Notification::toDto).toList();
    }
}
