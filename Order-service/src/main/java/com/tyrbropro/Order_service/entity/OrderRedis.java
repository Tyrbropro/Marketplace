package com.tyrbropro.Order_service.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRedis {

    @Id
    private Long id;

    private String title;

    private String description;

    private String status;

    private Long customerId;

    private Long executorId;

    private LocalDateTime createdAt;
}
