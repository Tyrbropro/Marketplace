package com.tyrbropro.user_service.dto.security;

import lombok.Data;

@Data
public class JwtAuthenticationDto {
    private String token;
    private String refreshToken;
}
