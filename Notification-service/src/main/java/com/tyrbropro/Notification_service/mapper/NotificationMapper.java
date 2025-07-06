package com.tyrbropro.Notification_service.mapper;

import com.tyrbropro.Notification_service.dto.notification.NotificationRequestDTO;
import com.tyrbropro.Notification_service.dto.notification.NotificationResponseDTO;
import com.tyrbropro.Notification_service.entity.Notification;

public interface NotificationMapper {
    NotificationResponseDTO toDTO(Notification notification);
    Notification toEntity(NotificationRequestDTO notificationRequestDTO);
    Notification toEntity(NotificationResponseDTO dto);
}
