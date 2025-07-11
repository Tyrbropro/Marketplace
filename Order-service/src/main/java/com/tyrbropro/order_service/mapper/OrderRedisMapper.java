package com.tyrbropro.order_service.mapper;

import com.tyrbropro.order_service.dto.order.OrderResponseDto;
import com.tyrbropro.order_service.entity.Order;
import com.tyrbropro.order_service.entity.OrderRedis;

public interface OrderRedisMapper {
    OrderResponseDto toDTO(OrderRedis order);

    OrderRedis toEntity(OrderResponseDto dto);

    OrderRedis toEntity(Order order);
}
