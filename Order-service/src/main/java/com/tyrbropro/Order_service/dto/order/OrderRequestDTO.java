package com.tyrbropro.Order_service.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.springframework.lang.NonNull;

@Builder
public record OrderRequestDTO(
        @NotBlank String title,
        @NotBlank String description,
        @NonNull String status,
        @Positive Long customerId,
        Long executorId
) {
}
