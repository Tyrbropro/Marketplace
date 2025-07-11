package com.tyrbropro.notification_service.entity;

import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationRedis {

    @Id
    Long id;
    Long userId;
    String message;
    String type;
    Boolean isRead;
    LocalDateTime createdAt;

    public Notification toEntity() {
        return new Notification()
                .setId(this.id)
                .setUserId(this.userId)
                .setMessage(this.message)
                .setType(this.type)
                .setIsRead(this.isRead)
                .setCreatedAt(this.createdAt);
    }
}
