package com.tyrbropro.User_service.mapper;

import com.tyrbropro.User_service.dto.user.UserResponseDTO;
import com.tyrbropro.User_service.entity.UserRedis;

public interface UserRedisMapper {
    UserResponseDTO toDto(UserRedis user);

    UserRedis toEntity(UserResponseDTO dto);
}
