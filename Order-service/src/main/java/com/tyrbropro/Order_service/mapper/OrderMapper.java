package com.tyrbropro.Order_service.mapper;

import com.tyrbropro.Order_service.dto.order.OrderRequestDTO;
import com.tyrbropro.Order_service.dto.order.OrderResponseDTO;
import com.tyrbropro.Order_service.entity.Order;

public interface OrderMapper {
    OrderResponseDTO toDTO(Order order);
    Order toEntity(OrderRequestDTO dto);
    void updateOrder(Order order, OrderRequestDTO dto);
}
