package com.tyrbropro.Order_service.service;

import com.tyrbropro.Order_service.dto.order.AcceptOrderDTO;
import com.tyrbropro.Order_service.dto.order.OrderRequestDTO;
import com.tyrbropro.Order_service.dto.order.OrderResponseDTO;
import com.tyrbropro.Order_service.dto.order.UpdateStatusDTO;
import java.util.List;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO getOrderById(Long orderId);

    List<OrderResponseDTO> getAllOrdersByUserId(Long userId);

    void updateStatus(UpdateStatusDTO updateStatusDTO);

    void acceptOrder(AcceptOrderDTO acceptOrderDTO);
}
