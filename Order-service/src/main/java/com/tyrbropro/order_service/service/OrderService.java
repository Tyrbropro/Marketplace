package com.tyrbropro.order_service.service;

import com.tyrbropro.order_service.dto.order.AcceptOrderDto;
import com.tyrbropro.order_service.dto.order.OrderRequestDto;
import com.tyrbropro.order_service.dto.order.OrderResponseDto;
import com.tyrbropro.order_service.dto.order.UpdateStatusDto;
import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDTO);

    OrderResponseDto getOrderById(Long orderId);

    List<OrderResponseDto> getAllOrdersByUserId(Long userId);

    void updateStatus(UpdateStatusDto updateStatusDTO);

    void acceptOrder(AcceptOrderDto acceptOrderDTO);
}
