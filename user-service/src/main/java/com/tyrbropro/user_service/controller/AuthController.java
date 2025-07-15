package com.tyrbropro.user_service.controller;

import com.tyrbropro.user_service.dto.security.JwtAuthenticationDto;
import com.tyrbropro.user_service.dto.security.RefreshTokenDto;
import com.tyrbropro.user_service.dto.security.UserCredentialsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Authorization", description = "Authorization user")
@RequestMapping("/api/v1/auth")
public interface AuthController {

    @Operation(summary = "singIn")
    @PostMapping("/sign-in")
    ResponseEntity<JwtAuthenticationDto> singIn(@RequestBody UserCredentialsDto userCredentialsDto);

    @Operation(summary = "refresh token")
    @PostMapping("/refresh")
    JwtAuthenticationDto refresh(@RequestBody RefreshTokenDto refreshTokenDto) throws Exception;
}
