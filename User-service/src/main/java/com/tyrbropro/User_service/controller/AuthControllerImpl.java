package com.tyrbropro.User_service.controller;

import com.tyrbropro.User_service.dto.security.JwtAuthenticationDTO;
import com.tyrbropro.User_service.dto.security.RefreshTokenDTO;
import com.tyrbropro.User_service.dto.security.UserCredentialsDTO;
import com.tyrbropro.User_service.service.UserService;
import javax.security.sasl.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthControllerImpl implements AuthController {
    private final UserService userService;

    @Override
    public ResponseEntity<JwtAuthenticationDTO> singIn(UserCredentialsDTO userCredentialsDTO) {
        try {
            JwtAuthenticationDTO jwtAuthenticationDTO = userService.signIn(userCredentialsDTO);
            return ResponseEntity.ok(jwtAuthenticationDTO);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed " + e.getMessage());
        }
    }

    @Override
    public JwtAuthenticationDTO refresh(RefreshTokenDTO refreshTokenDTO) throws Exception {
        return userService.refreshToken(refreshTokenDTO);
    }
}
