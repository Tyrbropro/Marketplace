package com.tyrbropro.order_service.dto.order;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record OrderTopicDto(
        Long userId,
        String message,
        String type,
        LocalDateTime time
) { }
