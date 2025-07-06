package com.tyrbropro.Order_service.controller;

import com.tyrbropro.Order_service.dto.order.AcceptOrderDTO;
import com.tyrbropro.Order_service.dto.order.OrderRequestDTO;
import com.tyrbropro.Order_service.dto.order.OrderResponseDTO;
import com.tyrbropro.Order_service.dto.order.UpdateStatusDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/orders")
public interface OrderController {

    @PostMapping("/create")
    ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO);

    @GetMapping("/{id}")
    ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id);

    @GetMapping("/user/{id}")
    ResponseEntity<List<OrderResponseDTO>> getAllOrderByUserId(@PathVariable Long id);

    @PutMapping("/status")
    ResponseEntity<Void> updateOrderStatus(@RequestBody UpdateStatusDTO updateStatusDTO);

    @PutMapping("/accept")
    ResponseEntity<Void> acceptOrder(@RequestBody AcceptOrderDTO acceptOrderDTO);
}
