package com.tyrbropro.User_service.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record UserRequestDTO(
        @NotBlank String username,
        @Email String email,
        @NotBlank String password,
        @NotNull String role,
        @Positive Float rating
) { }
