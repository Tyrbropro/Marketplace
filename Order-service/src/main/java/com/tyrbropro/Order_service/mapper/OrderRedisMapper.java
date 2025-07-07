package com.tyrbropro.Order_service.mapper;

import com.tyrbropro.Order_service.dto.order.OrderResponseDTO;
import com.tyrbropro.Order_service.entity.OrderRedis;

public interface OrderRedisMapper {
    OrderResponseDTO toDTO(OrderRedis order);

    OrderRedis toEntity(OrderResponseDTO dto);
}
