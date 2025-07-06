package com.tyrbropro.User_service.mapper;

import com.tyrbropro.User_service.dto.user.UserRequestDTO;
import com.tyrbropro.User_service.dto.user.UserResponseDTO;
import com.tyrbropro.User_service.entity.User;
import java.time.LocalDateTime;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapperImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO toDto(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .rating(user.getRating())
                .createdAt(user.getCreatedAt())
                .build();
    }

    @Override
    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(dto.role());
        user.setRating(dto.rating());
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

    @Override
    public void updateEntity(User user, UserRequestDTO dto) {
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setRole(dto.role());
        user.setRating(dto.rating());
        user.setCreatedAt(LocalDateTime.now());
    }
}
