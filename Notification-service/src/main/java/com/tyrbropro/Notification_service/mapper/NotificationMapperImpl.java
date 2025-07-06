package com.tyrbropro.Notification_service.mapper;

import com.tyrbropro.Notification_service.dto.notification.NotificationRequestDTO;
import com.tyrbropro.Notification_service.dto.notification.NotificationResponseDTO;
import com.tyrbropro.Notification_service.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotificationResponseDTO toDTO(Notification notification) {
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
    public Notification toEntity(NotificationRequestDTO dto) {
        Notification notification = new Notification();
        notification.setUserId(dto.userId());
        notification.setMessage(dto.message());
        notification.setType(dto.type());
        notification.setIsRead(dto.isRead());
        notification.setCreatedAt(dto.time());
        return notification;
    }

    @Override
    public Notification toEntity(NotificationResponseDTO dto) {
        Notification notification = new Notification();
        notification.setId(dto.id());
        notification.setUserId(dto.userId());
        notification.setMessage(dto.message());
        notification.setType(dto.type());
        notification.setIsRead(dto.isRead());
        notification.setCreatedAt(dto.time());
        return notification;
    }
}
