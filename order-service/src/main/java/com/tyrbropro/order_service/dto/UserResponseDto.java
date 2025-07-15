package com.tyrbropro.order_service.dto;

import java.time.LocalDateTime;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        String password,
        String role,
        Float rating,
        LocalDateTime createdAt
) {
}
