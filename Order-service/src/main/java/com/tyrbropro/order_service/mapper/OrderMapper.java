package com.tyrbropro.order_service.mapper;

import com.tyrbropro.order_service.dto.order.OrderRequestDto;
import com.tyrbropro.order_service.dto.order.OrderResponseDto;
import com.tyrbropro.order_service.entity.Order;
import com.tyrbropro.order_service.entity.OrderRedis;

public interface OrderMapper {
    OrderResponseDto toDTO(Order order);

    Order toEntity(OrderRequestDto dto);

    Order toEntity(OrderRedis redis);
}
