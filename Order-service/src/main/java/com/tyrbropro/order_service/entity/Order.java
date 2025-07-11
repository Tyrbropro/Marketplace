package com.tyrbropro.order_service.entity;

import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    Long id;
    String title;
    String description;
    String status;
    Long customerId;
    Long executorId;
    LocalDateTime createdAt;

    public enum Status { NEW, ACCEPTED, IN_PROGRESS, DONE, CANCELLED }
}
