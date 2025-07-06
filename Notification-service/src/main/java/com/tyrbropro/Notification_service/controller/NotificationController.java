package com.tyrbropro.Notification_service.controller;

import com.tyrbropro.Notification_service.dto.notification.NotificationResponseDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notification")
public interface NotificationController {

    @GetMapping("/{id}")
    ResponseEntity<NotificationResponseDTO> getNotificationById(@PathVariable("id") Long id);

    @GetMapping("/user/{id}")
    ResponseEntity<List<NotificationResponseDTO>> getAllNotificationByUser(@PathVariable("id") Long userId);
}
