package com.tyrbropro.Notification_service.mapper;

import com.tyrbropro.Notification_service.dto.notification.NotificationResponseDTO;
import com.tyrbropro.Notification_service.entity.NotificationRedis;

public interface NotificationRedisMapper {
    NotificationResponseDTO toDTO(NotificationRedis notification);
    NotificationRedis toEntity(NotificationResponseDTO notificationRequestDTO);
}
