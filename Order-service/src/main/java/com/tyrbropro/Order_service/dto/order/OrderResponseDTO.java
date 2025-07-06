package com.tyrbropro.Order_service.dto.order;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record OrderResponseDTO(
        Long id,
        String title,
        String description,
        String status,
        Long customerId,
        Long executorId,
        LocalDateTime created_at
) {
}
