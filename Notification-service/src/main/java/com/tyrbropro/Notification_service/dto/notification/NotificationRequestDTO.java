package com.tyrbropro.Notification_service.dto.notification;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record NotificationRequestDTO(
        Long userId,
        String message,
        String type,
        Boolean isRead,
        LocalDateTime time
) {
}
