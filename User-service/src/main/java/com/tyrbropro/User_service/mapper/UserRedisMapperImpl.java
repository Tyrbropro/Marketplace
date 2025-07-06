package com.tyrbropro.User_service.mapper;

import com.tyrbropro.User_service.dto.user.UserRequestDTO;
import com.tyrbropro.User_service.dto.user.UserResponseDTO;
import com.tyrbropro.User_service.entity.UserRedis;
import java.time.LocalDateTime;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRedisMapperImpl implements UserRedisMapper {

    private final PasswordEncoder passwordEncoder;

    public UserRedisMapperImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO toDto(UserRedis user) {
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
    public UserRedis toEntity(UserResponseDTO dto) {
        UserRedis user = new UserRedis();
        user.setId(dto.id());
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(dto.role());
        user.setRating(dto.rating());
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }
}
