package com.tyrbropro.notification_service.controller;

import com.tyrbropro.notification_service.dto.notification.NotificationResponseDto;
import com.tyrbropro.notification_service.service.NotificationService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class NotificationControllerImpl implements NotificationController {

    NotificationService notificationService;

    @Override
    public ResponseEntity<NotificationResponseDto> getNotificationById(Long id) {
        return ResponseEntity.ok(notificationService.markAsRead(id));
    }

    @Override
    public ResponseEntity<List<NotificationResponseDto>> getAllNotificationByUser(Long userId) {
        return ResponseEntity.ok(notificationService.markAllAsRead(userId));
    }
}
