package com.tyrbropro.user_service.mapper;

import com.tyrbropro.user_service.dto.user.UserRequestDto;
import com.tyrbropro.user_service.dto.user.UserResponseDto;
import com.tyrbropro.user_service.entity.User;
import com.tyrbropro.user_service.entity.UserRedis;

public interface UserMapper {
    UserResponseDto toDto(User user);

    User toEntity(UserRequestDto dto);

    User toEntity(UserRedis user);

    void updateEntity(User user, UserRequestDto dto);
}
