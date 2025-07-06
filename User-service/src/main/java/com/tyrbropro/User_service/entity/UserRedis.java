package com.tyrbropro.User_service.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRedis {
    @Id
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private Float rating;
    private LocalDateTime createdAt;
}
