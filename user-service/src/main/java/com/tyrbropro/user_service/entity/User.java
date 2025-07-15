package com.tyrbropro.user_service.entity;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.relational.core.mapping.Table;

@RedisHash("User")
@Table("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class User {

    @Id
    Long id;
    String username;
    String email;
    String password;
    String role;
    Float rating;
    LocalDateTime createdAt;

    public enum Role { CUSTOMER, EXECUTOR, ADMIN }
}
