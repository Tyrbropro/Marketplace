package com.tyrbropro.Notification_service.dto.notification;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record NotificationResponseDTO(
        Long id,
        Long userId,
        String message,
        String type,
        Boolean isRead,
        LocalDateTime time
) {
}
