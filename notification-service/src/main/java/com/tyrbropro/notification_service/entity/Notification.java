package com.tyrbropro.notification_service.entity;

import com.tyrbropro.notification_service.dto.NotificationResponseDto;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {

    @Id
    Long id;
    Long userId;
    String message;
    String type;
    Boolean isRead;
    LocalDateTime createdAt;

    public enum Type { INFO, WARNING, ERROR }

    public NotificationResponseDto toDto() {
        return NotificationResponseDto.builder()
                .id(this.getId())
                .userId(this.getUserId())
                .message(this.getMessage())
                .type(this.getType())
                .isRead(this.getIsRead())
                .time(this.getCreatedAt())
                .build();
    }

    public NotificationRedis toEntityCache() {
        return new NotificationRedis()
                .setId(this.getId())
                .setUserId(this.getUserId())
                .setMessage(this.getMessage())
                .setType(this.getType())
                .setIsRead(this.getIsRead())
                .setCreatedAt(this.getCreatedAt());
    }

    public Notification markAsRead() {
        return this.setIsRead(true);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Notification that = (Notification) o;
        return Objects.equals(id, that.id)
                && Objects.equals(userId, that.userId)
                && Objects.equals(message, that.message)
                && Objects.equals(type, that.type)
                && Objects.equals(isRead, that.isRead)
                && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, message, type, isRead, createdAt);
    }
}
