package com.tyrbropro.order_service.mapper;

import com.tyrbropro.order_service.dto.order.OrderResponseDto;
import com.tyrbropro.order_service.entity.Order;
import com.tyrbropro.order_service.entity.OrderRedis;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class OrderRedisMapperImpl implements OrderRedisMapper {

    @Override
    public OrderResponseDto toDTO(OrderRedis order) {
        return OrderResponseDto.builder()
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
    public OrderRedis toEntity(OrderResponseDto dto) {
        return new OrderRedis()
                .setId(dto.id())
                .setTitle(dto.title())
                .setDescription(dto.description())
                .setStatus(dto.status())
                .setCustomerId(dto.customerId())
                .setExecutorId(dto.executorId())
                .setCreatedAt(dto.created_at());
    }

    @Override
    public OrderRedis toEntity(Order order) {
        return new OrderRedis()
                .setId(order.getId())
                .setTitle(order.getTitle())
                .setDescription(order.getDescription())
                .setStatus(order.getStatus())
                .setCustomerId(order.getCustomerId())
                .setExecutorId(order.getExecutorId())
                .setCreatedAt(LocalDateTime.now());
    }
}