package com.tyrbropro.Notification_service.mapper;

import com.tyrbropro.Notification_service.dto.notification.NotificationRequestDTO;
import com.tyrbropro.Notification_service.dto.notification.NotificationResponseDTO;
import com.tyrbropro.Notification_service.entity.Notification;
import com.tyrbropro.Notification_service.entity.NotificationRedis;
import org.springframework.stereotype.Component;

@Component
public class NotificationRedisMapperImpl implements NotificationRedisMapper {

    @Override
    public NotificationResponseDTO toDTO(NotificationRedis notification) {
        return NotificationResponseDTO.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .message(notification.getMessage())
                .type(notification.getType())
                .isRead(notification.getIsRead())
                .time(notification.getCreatedAt())
                .build();
    }

    @Override
    public NotificationRedis toEntity(NotificationResponseDTO dto) {
        NotificationRedis notification = new NotificationRedis();
        notification.setId(dto.id());
        notification.setUserId(dto.userId());
        notification.setMessage(dto.message());
        notification.setType(dto.type());
        notification.setIsRead(dto.isRead());
        notification.setCreatedAt(dto.time());
        return notification;
    }
}
