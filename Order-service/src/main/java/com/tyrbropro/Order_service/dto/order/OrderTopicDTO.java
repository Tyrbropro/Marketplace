package com.tyrbropro.Order_service.dto.order;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record OrderTopicDTO(
        Long userId,
        String message,
        String type,
        LocalDateTime time
) { }
