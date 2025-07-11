package com.tyrbropro.user_service.mapper;

import com.tyrbropro.user_service.dto.user.UserResponseDto;
import com.tyrbropro.user_service.entity.User;
import com.tyrbropro.user_service.entity.UserRedis;

public interface UserRedisMapper {
    UserResponseDto toDto(UserRedis user);

    UserRedis toEntity(UserResponseDto dto);

    UserRedis toEntity(User user);
}
