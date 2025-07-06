package com.tyrbropro.User_service.dto.security;

import lombok.Data;

@Data
public class UserCredentialsDTO {
    private String email;
    private String password;
}
