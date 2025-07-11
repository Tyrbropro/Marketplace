package com.tyrbropro.user_service.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record UserRequestDto(
        @NotBlank String username,
        @Email String email,
        @NotBlank String password,
        @NotNull String role,
        @Positive Float rating
) { }
