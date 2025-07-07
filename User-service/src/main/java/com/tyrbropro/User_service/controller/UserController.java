package com.tyrbropro.User_service.controller;

import com.tyrbropro.User_service.dto.user.UserRequestDTO;
import com.tyrbropro.User_service.dto.user.UserResponseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
public interface UserController {

    @PostMapping("/registration")
    ResponseEntity<UserResponseDTO> addUser(@Valid @RequestBody UserRequestDTO dto);

    @GetMapping("/{id}")
    ResponseEntity<UserResponseDTO> getUserById(@Positive @PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<Void> updateUser(@Positive @PathVariable Long id, @RequestBody UserRequestDTO details);
}
