package com.tyrbropro.user_service.mapper;

import com.tyrbropro.user_service.dto.user.UserRequestDto;
import com.tyrbropro.user_service.dto.user.UserResponseDto;
import com.tyrbropro.user_service.entity.User;
import com.tyrbropro.user_service.entity.UserRedis;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto toDto(User user) {
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
    public User toEntity(UserRequestDto dto) {
        return new User()
                .setUsername(dto.username())
                .setEmail(dto.email())
                .setPassword(passwordEncoder.encode(dto.password()))
                .setRole(dto.role())
                .setRating(dto.rating())
                .setCreatedAt(LocalDateTime.now());
    }

    @Override
    public User toEntity(UserRedis user) {
        return new User()
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setRole(user.getRole())
                .setRating(user.getRating())
                .setCreatedAt(LocalDateTime.now());
    }

    @Override
    public void updateEntity(User user, UserRequestDto dto) {
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setRole(dto.role());
        user.setRating(dto.rating());
        user.setCreatedAt(LocalDateTime.now());
    }
}
