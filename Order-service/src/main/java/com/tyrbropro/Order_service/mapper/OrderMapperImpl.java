package com.tyrbropro.Order_service.mapper;

import com.tyrbropro.Order_service.dto.order.OrderRequestDTO;
import com.tyrbropro.Order_service.dto.order.OrderResponseDTO;
import com.tyrbropro.Order_service.entity.Order;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderResponseDTO toDTO(Order order) {
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
    public Order toEntity(OrderRequestDTO dto) {
        Order order = new Order();
        order.setTitle(dto.title());
        order.setDescription(dto.description());
        order.setStatus(dto.status());
        order.setCustomerId(dto.customerId());
        order.setExecutorId(dto.executorId());
        order.setCreatedAt(LocalDateTime.now());
        return order;
    }

    @Override
    public void updateOrder(Order order, OrderRequestDTO dto) {
        order.setTitle(dto.title());
        order.setDescription(dto.description());
        order.setStatus(dto.status());
        order.setCustomerId(dto.customerId());
        order.setExecutorId(dto.executorId());
        order.setCreatedAt(LocalDateTime.now());
    }
}
