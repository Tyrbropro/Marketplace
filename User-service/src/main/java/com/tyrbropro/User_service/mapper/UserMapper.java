package com.tyrbropro.User_service.mapper;

import com.tyrbropro.User_service.dto.user.UserRequestDTO;
import com.tyrbropro.User_service.dto.user.UserResponseDTO;
import com.tyrbropro.User_service.entity.User;

public interface UserMapper {
    UserResponseDTO toDto(User user);
    User toEntity(UserRequestDTO dto);
    void updateEntity(User user, UserRequestDTO dto);
}
