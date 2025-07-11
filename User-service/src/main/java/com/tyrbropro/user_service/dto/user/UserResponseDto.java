package com.tyrbropro.user_service.dto.user;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record UserResponseDto(
        Long id,
        String username,
        String email,
        String password,
        String role,
        Float rating,
        LocalDateTime createdAt
) { }
