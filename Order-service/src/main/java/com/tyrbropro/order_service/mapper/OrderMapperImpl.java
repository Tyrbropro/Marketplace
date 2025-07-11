package com.tyrbropro.order_service.mapper;

import com.tyrbropro.order_service.dto.order.OrderRequestDto;
import com.tyrbropro.order_service.dto.order.OrderResponseDto;
import com.tyrbropro.order_service.entity.Order;
import com.tyrbropro.order_service.entity.OrderRedis;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderResponseDto toDTO(Order order) {
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
    public Order toEntity(OrderRequestDto dto) {
        return new Order()
                .setTitle(dto.title())
                .setDescription(dto.description())
                .setStatus(dto.status())
                .setCustomerId(dto.customerId())
                .setExecutorId(dto.executorId())
                .setCreatedAt(LocalDateTime.now());
    }

    @Override
    public Order toEntity(OrderRedis redis) {
        return new Order()
                .setTitle(redis.getTitle())
                .setDescription(redis.getDescription())
                .setStatus(redis.getStatus())
                .setCustomerId(redis.getCustomerId())
                .setExecutorId(redis.getExecutorId())
                .setCreatedAt(LocalDateTime.now());
    }
}
