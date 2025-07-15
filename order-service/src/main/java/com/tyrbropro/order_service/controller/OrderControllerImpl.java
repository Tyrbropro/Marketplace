package com.tyrbropro.order_service.controller;

import com.tyrbropro.order_service.dto.order.AcceptOrderDto;
import com.tyrbropro.order_service.dto.order.OrderRequestDto;
import com.tyrbropro.order_service.dto.order.OrderResponseDto;
import com.tyrbropro.order_service.dto.order.UpdateStatusDto;
import com.tyrbropro.order_service.service.OrderService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    OrderService orderService;

    @Override
    public ResponseEntity<OrderResponseDto> createOrder(OrderRequestDto orderRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequestDTO));
    }

    @Override
    public ResponseEntity<OrderResponseDto> getOrderById(Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @Override
    public ResponseEntity<List<OrderResponseDto>> getAllOrderByUserId(Long id) {
        return ResponseEntity.ok(orderService.getAllOrdersByUserId(id));
    }

    @Override
    public ResponseEntity<Void> updateOrderStatus(UpdateStatusDto updateStatusDTO) {
        orderService.updateStatus(updateStatusDTO);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> acceptOrder(AcceptOrderDto acceptOrderDTO) {
        orderService.acceptOrder(acceptOrderDTO);
        return ResponseEntity.noContent().build();
    }
}
