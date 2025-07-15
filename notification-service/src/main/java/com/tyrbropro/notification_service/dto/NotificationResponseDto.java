package com.tyrbropro.notification_service.dto;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record NotificationResponseDto(
        Long id,
        Long userId,
        String message,
        String type,
        Boolean isRead,
        LocalDateTime time
) {
}
