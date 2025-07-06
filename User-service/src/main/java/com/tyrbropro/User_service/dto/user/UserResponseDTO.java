package com.tyrbropro.User_service.dto.user;

import com.tyrbropro.User_service.entity.User;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponseDTO (
        Long id,
        String username,
        String email,
        String password,
        String role,
        Float rating,
        LocalDateTime createdAt
) { }
