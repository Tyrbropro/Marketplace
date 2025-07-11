package com.tyrbropro.notification_service.controller;

import com.tyrbropro.notification_service.dto.notification.NotificationResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Notifications", description = "APIs for notification")
@RequestMapping("/api/v1/notification")
public interface NotificationController {

    @Operation(summary = "Get notification by ID", description = "Returns the notification with the specified ID")
    @GetMapping("/{id}")
    ResponseEntity<NotificationResponseDto> getNotificationById(@PathVariable("id") Long id);

    @Operation(summary = "Get all notification", description = "Returns a list of all notification")
    @GetMapping("/user/{id}")
    ResponseEntity<List<NotificationResponseDto>> getAllNotificationByUser(@PathVariable("id") Long userId);
}
