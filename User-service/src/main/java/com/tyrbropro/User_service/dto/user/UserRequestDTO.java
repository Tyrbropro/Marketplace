package com.tyrbropro.User_service.dto.user;

import com.tyrbropro.User_service.entity.User;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserRequestDTO(
        @NotBlank String username,
        @Email String email,
        @NotBlank String password,
        @NotNull String role,
        @Positive Float rating
) { }
