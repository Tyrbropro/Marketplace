package com.tyrbropro.Order_service.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private Long id;

    private String title;

    private String description;

    private String status;

    private Long customerId;

    private Long executorId;

    private LocalDateTime createdAt;

    public enum Status { NEW, ACCEPTED, IN_PROGRESS, DONE, CANCELLED }
}
