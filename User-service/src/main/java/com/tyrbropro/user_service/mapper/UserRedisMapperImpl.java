package com.tyrbropro.user_service.mapper;

import com.tyrbropro.user_service.dto.user.UserResponseDto;
import com.tyrbropro.user_service.entity.User;
import com.tyrbropro.user_service.entity.UserRedis;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserRedisMapperImpl implements UserRedisMapper {

    PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto toDto(UserRedis user) {
        return UserResponseDto.builder()
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
    public UserRedis toEntity(UserResponseDto dto) {
        return new UserRedis()
                .setId(dto.id())
                .setUsername(dto.username())
                .setEmail(dto.email())
                .setPassword(passwordEncoder.encode(dto.password()))
                .setRole(dto.role())
                .setRating(dto.rating())
                .setCreatedAt(dto.createdAt());
    }

    @Override
    public UserRedis toEntity(User user) {
        return new UserRedis()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setRole(user.getRole())
                .setRating(user.getRating())
                .setCreatedAt(user.getCreatedAt());
    }
}
