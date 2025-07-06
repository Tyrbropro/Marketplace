package com.tyrbropro.Order_service.controller;

import com.tyrbropro.Order_service.dto.order.AcceptOrderDTO;
import com.tyrbropro.Order_service.dto.order.OrderRequestDTO;
import com.tyrbropro.Order_service.dto.order.OrderResponseDTO;
import com.tyrbropro.Order_service.dto.order.UpdateStatusDTO;
import com.tyrbropro.Order_service.service.OrderService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    public OrderControllerImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ResponseEntity<OrderResponseDTO> createOrder(OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequestDTO));
    }

    @Override
    public ResponseEntity<OrderResponseDTO> getOrderById(Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @Override
    public ResponseEntity<List<OrderResponseDTO>> getAllOrderByUserId(Long id) {
        return ResponseEntity.ok(orderService.getAllOrdersByUserId(id));
    }

    @Override
    public ResponseEntity<Void> updateOrderStatus(UpdateStatusDTO updateStatusDTO) {
        orderService.updateStatus(updateStatusDTO);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> acceptOrder(AcceptOrderDTO acceptOrderDTO) {
        orderService.acceptOrder(acceptOrderDTO);
        return ResponseEntity.noContent().build();
    }
}
