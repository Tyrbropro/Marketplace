package com.tyrbropro.User_service.controller;

import com.tyrbropro.User_service.dto.security.JwtAuthenticationDTO;
import com.tyrbropro.User_service.dto.security.RefreshTokenDTO;
import com.tyrbropro.User_service.dto.security.UserCredentialsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
public interface AuthController {

    @PostMapping("/sign-in")
    ResponseEntity<JwtAuthenticationDTO> singIn(@RequestBody UserCredentialsDTO userCredentialsDTO);

    @PostMapping("/refresh")
    JwtAuthenticationDTO refresh(@RequestBody RefreshTokenDTO refreshTokenDTO) throws Exception;
}
