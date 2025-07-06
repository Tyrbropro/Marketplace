package com.tyrbropro.User_service.controller;

import com.tyrbropro.User_service.dto.user.UserRequestDTO;
import com.tyrbropro.User_service.dto.user.UserResponseDTO;
import com.tyrbropro.User_service.service.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserResponseDTO> addUser(UserRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(dto));
    }

    @Override
    public ResponseEntity<UserResponseDTO> getUserById(Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException("Not found user" + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Void> updateUser(Long id, UserRequestDTO details) {
        try {
            userService.updateUser(id, details);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException("Not found user" + e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }
}
