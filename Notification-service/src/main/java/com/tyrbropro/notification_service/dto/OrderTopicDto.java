package com.tyrbropro.notification_service.dto;

import java.time.LocalDateTime;

public record OrderTopicDto(
        Long userId,
        String message,
        String type,
        LocalDateTime time
) {
}
