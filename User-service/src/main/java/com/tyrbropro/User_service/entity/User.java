package com.tyrbropro.User_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@RedisHash("User")
@Table("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private Float rating;
    private LocalDateTime createdAt;

    public enum Role {CUSTOMER, EXECUTOR, ADMIN}
}
