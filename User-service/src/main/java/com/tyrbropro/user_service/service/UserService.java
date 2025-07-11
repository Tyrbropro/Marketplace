package com.tyrbropro.user_service.service;

import com.tyrbropro.user_service.dto.security.JwtAuthenticationDto;
import com.tyrbropro.user_service.dto.security.RefreshTokenDto;
import com.tyrbropro.user_service.dto.security.UserCredentialsDto;
import com.tyrbropro.user_service.dto.user.UserRequestDto;
import com.tyrbropro.user_service.dto.user.UserResponseDto;
import javax.security.sasl.AuthenticationException;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface UserService {
    JwtAuthenticationDto signIn(UserCredentialsDto userCredentialsDTO) throws AuthenticationException;

    JwtAuthenticationDto refreshToken(RefreshTokenDto refreshTokenDTO) throws Exception;

    UserResponseDto addUser(UserRequestDto dto);

    UserResponseDto getUserById(Long id) throws ChangeSetPersister.NotFoundException;

    void updateUser(Long id, UserRequestDto details) throws ChangeSetPersister.NotFoundException;
}
