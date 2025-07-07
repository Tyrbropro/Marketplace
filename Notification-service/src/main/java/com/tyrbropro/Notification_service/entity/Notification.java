package com.tyrbropro.Notification_service.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    private Long id;

    private Long userId;

    private String message;

    private String type;

    private Boolean isRead;

    private LocalDateTime createdAt;

    public enum Type { INFO, WARNING, ERROR }
}
