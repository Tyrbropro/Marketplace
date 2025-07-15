package com.tyrbropro.user_service.controller;

import com.tyrbropro.user_service.dto.security.JwtAuthenticationDto;
import com.tyrbropro.user_service.dto.security.RefreshTokenDto;
import com.tyrbropro.user_service.dto.security.UserCredentialsDto;
import com.tyrbropro.user_service.service.UserService;
import javax.security.sasl.AuthenticationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthControllerImpl implements AuthController {

    UserService userService;

    @Override
    public ResponseEntity<JwtAuthenticationDto> singIn(UserCredentialsDto userCredentialsDto) {
        try {
            var jwtAuthenticationDTO = this.userService.signIn(userCredentialsDto);
            return ResponseEntity.ok(jwtAuthenticationDTO);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed " + e.getMessage());
        }
    }

    @Override
    public JwtAuthenticationDto refresh(RefreshTokenDto refreshTokenDto) throws Exception {
        return this.userService.refreshToken(refreshTokenDto);
    }
}
