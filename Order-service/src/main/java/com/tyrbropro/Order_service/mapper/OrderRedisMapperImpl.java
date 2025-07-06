package com.tyrbropro.Order_service.mapper;

import com.tyrbropro.Order_service.dto.order.OrderResponseDTO;
import com.tyrbropro.Order_service.entity.OrderRedis;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class OrderRedisMapperImpl implements OrderRedisMapper {
    @Override
    public OrderResponseDTO toDTO(OrderRedis order) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .title(order.getTitle())
                .description(order.getDescription())
                .status(order.getStatus())
                .customerId(order.getCustomerId())
                .executorId(order.getExecutorId())
                .created_at(order.getCreatedAt())
                .build();
    }

    @Override
    public OrderRedis toEntity(OrderResponseDTO dto) {
        OrderRedis order = new OrderRedis();
        order.setId(dto.id());
        order.setTitle(dto.title());
        order.setDescription(dto.description());
        order.setStatus(dto.status());
        order.setCustomerId(dto.customerId());
        order.setExecutorId(dto.executorId());
        order.setCreatedAt(LocalDateTime.now());
        return order;
    }
}
