package com.tyrbropro.order_service.dto.order;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record OrderResponseDto(
        Long id,
        String title,
        String description,
        String status,
        Long customerId,
        Long executorId,
        LocalDateTime created_at
) {
}
