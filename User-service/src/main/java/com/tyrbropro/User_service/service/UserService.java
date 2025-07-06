package com.tyrbropro.User_service.service;

import com.tyrbropro.User_service.dto.security.JwtAuthenticationDTO;
import com.tyrbropro.User_service.dto.security.RefreshTokenDTO;
import com.tyrbropro.User_service.dto.security.UserCredentialsDTO;
import com.tyrbropro.User_service.dto.user.UserRequestDTO;
import com.tyrbropro.User_service.dto.user.UserResponseDTO;
import javax.security.sasl.AuthenticationException;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface UserService {
    JwtAuthenticationDTO signIn(UserCredentialsDTO userCredentialsDTO) throws AuthenticationException;
    JwtAuthenticationDTO refreshToken(RefreshTokenDTO refreshTokenDTO) throws Exception;
    UserResponseDTO addUser(UserRequestDTO dto);
    UserResponseDTO getUserById(Long id) throws ChangeSetPersister.NotFoundException;
    void updateUser(Long id, UserRequestDTO details) throws ChangeSetPersister.NotFoundException;
}
