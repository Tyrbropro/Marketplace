package com.tyrbropro.user_service.dto.security;

import lombok.Data;

@Data
public class UserCredentialsDto {
    private String email;
    private String password;
}
