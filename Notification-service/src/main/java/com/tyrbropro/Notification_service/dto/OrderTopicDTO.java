package com.tyrbropro.Notification_service.dto;

import java.time.LocalDateTime;

public record OrderTopicDTO(
        Long userId,
        String message,
        String type,
        LocalDateTime time
) {
}
