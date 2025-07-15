package com.tyrbropro.user_service.controller;

import com.tyrbropro.user_service.dto.user.UserRequestDto;
import com.tyrbropro.user_service.dto.user.UserResponseDto;
import com.tyrbropro.user_service.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    UserService userService;

    @Override
    public ResponseEntity<UserResponseDto> addUser(UserRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(dto));
    }

    @Override
    public ResponseEntity<UserResponseDto> getUserById(Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException("Not found user" + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Void> updateUser(Long id, UserRequestDto details) {
        try {
            userService.updateUser(id, details);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException("Not found user" + e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }
}
