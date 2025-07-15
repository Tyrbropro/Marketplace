package com.tyrbropro.user_service.controller;

import com.tyrbropro.user_service.dto.user.UserRequestDto;
import com.tyrbropro.user_service.dto.user.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users", description = "APIs for users")
@RequestMapping("/api/v1/user")
public interface UserController {

    @Operation(summary = "Create a new user", description = "Creates a new user")
    @PostMapping("/registration")
    ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserRequestDto dto);

    @Operation(summary = "Get user by ID", description = "Returns the user with the specified ID")
    @GetMapping("/{id}")
    ResponseEntity<UserResponseDto> getUserById(@Positive @PathVariable Long id);

    @Operation(summary = "Update a user", description = "Update existing users")
    @PutMapping("/{id}")
    ResponseEntity<Void> updateUser(@Positive @PathVariable Long id, @RequestBody UserRequestDto details);
}
