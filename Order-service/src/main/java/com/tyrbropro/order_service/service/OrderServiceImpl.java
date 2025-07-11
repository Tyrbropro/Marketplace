package com.tyrbropro.order_service.service;

import com.tyrbropro.order_service.client.UserClient;
import com.tyrbropro.order_service.dto.order.*;
import com.tyrbropro.order_service.entity.Order;
import com.tyrbropro.order_service.exception.OrderNotFoundException;
import com.tyrbropro.order_service.kafka.KafkaProducer;
import com.tyrbropro.order_service.mapper.OrderMapper;
import com.tyrbropro.order_service.mapper.OrderRedisMapper;
import com.tyrbropro.order_service.repository.OrderRedisRepository;
import com.tyrbropro.order_service.repository.OrderRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    UserClient userClient;
    OrderRedisRepository orderRedisRepository;
    OrderRepository orderRepository;
    OrderRedisMapper orderRedisMapper;
    OrderMapper orderMapper;
    KafkaProducer kafkaProducer;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        var orderDto = this.userClient.getUserById(orderRequestDto.customerId());

        if (orderDto == null || !"CUSTOMER".equals(orderDto.role())) {
            this.kafkaProducer.sendTopic("user_not_customer_topic",
                    new OrderTopicDto(
                    orderRequestDto.customerId(),
                    "User not found or is not a CUSTOMER",
                    "ERROR",
                    LocalDateTime.now()
            ));

            throw new RuntimeException("User not found or is not a CUSTOMER");
        }

        var order = this.orderMapper.toEntity(orderRequestDto);
        order.setStatus(Order.Status.NEW.toString());

        var saved = this.orderRepository.save(order);
        this.orderRedisRepository.save(orderRedisMapper.toEntity(saved));

        this.kafkaProducer.sendTopic("order_created_topic",
                new OrderTopicDto(
                        orderRequestDto.customerId(),
                        "Order created",
                        "INFO",
                        LocalDateTime.now()
                ));

        return this.orderMapper.toDTO(saved);
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        return this.orderRedisRepository.findById(orderId).map(orderRedisMapper::toDTO).
                orElseGet(() -> this.orderRepository.findById(orderId)
                        .map(entity -> {
                            var orderResponseDTO = orderMapper.toDTO(entity);
                            this.orderRedisRepository.save(orderRedisMapper.toEntity(orderResponseDTO));
                            return orderResponseDTO;
                        }).orElseThrow(() -> new OrderNotFoundException("Order not found")));
    }

    @Override
    public List<OrderResponseDto> getAllOrdersByUserId(Long userId) {
        return this.orderRepository.findByCustomerId(userId).stream().map(orderMapper::toDTO).toList();
    }

    @Override
    public void updateStatus(UpdateStatusDto updateStatusDTO) {
        var order = this.orderRepository.findById(updateStatusDTO.orderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        order.setStatus(updateStatusDTO.newStatus());

        this.orderRepository.save(order);
        this.orderRedisRepository.save(orderRedisMapper.toEntity(order));
    }

    @Override
    public void acceptOrder(AcceptOrderDto acceptOrderDTO) {
        var order = this.orderRedisRepository
                .findById(acceptOrderDTO.orderId()).map(orderMapper::toEntity)
                .orElseGet(() -> this.orderRepository.findById(acceptOrderDTO.orderId())
                        .orElseThrow(() -> new OrderNotFoundException("Order not found")));


        var executorDto = this.userClient.getUserById(acceptOrderDTO.executorId());

        if (executorDto == null || !"EXECUTOR".equals(executorDto.role())) {
            this.kafkaProducer.sendTopic("user_not_executor_topic",
                    new OrderTopicDto(
                            acceptOrderDTO.orderId(),
                            "User not found or is not a EXECUTOR",
                            "ERROR",
                            LocalDateTime.now()
                    ));

            throw new RuntimeException("User not found or is not  a EXECUTOR");
        }

        order.setExecutorId(acceptOrderDTO.executorId());
        order.setStatus(Order.Status.ACCEPTED.toString());

        this.kafkaProducer.sendTopic("order_accepted_topic",
                new OrderTopicDto(
                        acceptOrderDTO.orderId(),
                        "Accepted Order",
                        "INFO",
                        LocalDateTime.now()
                ));

        this.orderRepository.save(order);
        this.orderRedisRepository.save(orderRedisMapper.toEntity(order));
    }
}