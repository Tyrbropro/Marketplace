package com.tyrbropro.order_service.controller;

import com.tyrbropro.order_service.dto.order.AcceptOrderDto;
import com.tyrbropro.order_service.dto.order.OrderRequestDto;
import com.tyrbropro.order_service.dto.order.OrderResponseDto;
import com.tyrbropro.order_service.dto.order.UpdateStatusDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Orders", description = "APIs for orders")
@RequestMapping("/api/v1/orders")
public interface OrderController {

    @Operation(summary = "Create a new order", description = "Creates a new order")
    @PostMapping("/create")
    ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDTO);

    @Operation(summary = "Get order by ID", description = "Returns the order with the specified ID")
    @GetMapping("/{id}")
    ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id);

    @Operation(summary = "Get all orders", description = "Returns a list of all available orders")
    @GetMapping("/user/{id}")
    ResponseEntity<List<OrderResponseDto>> getAllOrderByUserId(@PathVariable Long id);

    @Operation(summary = "Update a order", description = "Update existing orders")
    @PutMapping("/status")
    ResponseEntity<Void> updateOrderStatus(@RequestBody UpdateStatusDto updateStatusDTO);

    @Operation(summary = "Accept order", description = "Only executor accepted order")
    @PutMapping("/accept")
    ResponseEntity<Void> acceptOrder(@RequestBody AcceptOrderDto acceptOrderDTO);
}
