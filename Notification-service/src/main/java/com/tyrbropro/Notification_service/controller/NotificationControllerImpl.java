package com.tyrbropro.Notification_service.controller;

import com.tyrbropro.Notification_service.dto.notification.NotificationResponseDTO;
import com.tyrbropro.Notification_service.service.NotificationService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationControllerImpl implements NotificationController {

    private final NotificationService notificationService;

    public NotificationControllerImpl(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public ResponseEntity<NotificationResponseDTO> getNotificationById(Long id) {
        return ResponseEntity.ok(notificationService.markAsRead(id));
    }

    @Override
    public ResponseEntity<List<NotificationResponseDTO>> getAllNotificationByUser(Long userId) {
        return ResponseEntity.ok(notificationService.markAllAsRead(userId));
    }
}
