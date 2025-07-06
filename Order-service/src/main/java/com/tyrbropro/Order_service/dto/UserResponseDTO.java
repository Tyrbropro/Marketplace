package com.tyrbropro.Order_service.dto;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String username,
        String email,
        String password,
        String role,
        Float rating,
        LocalDateTime createdAt
) {
}
