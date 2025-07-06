package com.tyrbropro.Order_service.service;

import com.tyrbropro.Order_service.client.UserClient;
import com.tyrbropro.Order_service.dto.UserResponseDTO;
import com.tyrbropro.Order_service.dto.order.*;
import com.tyrbropro.Order_service.entity.Order;
import com.tyrbropro.Order_service.kafka.KafkaProducer;
import com.tyrbropro.Order_service.mapper.OrderMapper;
import com.tyrbropro.Order_service.mapper.OrderRedisMapper;
import com.tyrbropro.Order_service.repository.OrderRedisRepository;
import com.tyrbropro.Order_service.repository.OrderRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserClient userClient;
    private final OrderRedisRepository orderRedisRepository;
    private final OrderRepository orderRepository;
    private final OrderRedisMapper orderRedisMapper;
    private final OrderMapper orderMapper;
    private final KafkaProducer kafkaProducer;

    public OrderServiceImpl(UserClient userClient, OrderRepository orderRepository, OrderRedisRepository orderRedisRepository,
                            OrderRedisMapper orderRedisMapper, OrderMapper orderMapper, KafkaProducer kafkaProducer) {
        this.userClient = userClient;
        this.orderRepository = orderRepository;
        this.orderRedisRepository = orderRedisRepository;
        this.orderRedisMapper = orderRedisMapper;
        this.orderMapper = orderMapper;
        this.kafkaProducer = kafkaProducer;
    }


    @Override
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        UserResponseDTO user = userClient.getUserById(orderRequestDTO.customerId());

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!user.role().equals("CUSTOMER")) {
            kafkaProducer.sendTopic(new OrderTopicDTO(orderRequestDTO.customerId(),
                    "User is not a customer", "ERROR", LocalDateTime.now()));
            throw new RuntimeException("User is not a customer");
        }

        Order order = orderMapper.toEntity(orderRequestDTO);
        order.setStatus(Order.Status.NEW.toString());
        Order saved = orderRepository.save(order);
        orderRedisRepository.save(orderRedisMapper.toEntity(orderMapper.toDTO(saved)));
        kafkaProducer.sendTopic(new OrderTopicDTO(orderRequestDTO.customerId(),
                "Order created", "INFO", LocalDateTime.now()));

        return orderMapper.toDTO(saved);
    }

    @Override
    public OrderResponseDTO getOrderById(Long orderId) {
        return orderRedisRepository.findById(orderId).map(orderRedisMapper::toDTO).
                orElseGet(() -> orderRepository.findById(orderId)
                        .map(entity -> {
                            OrderResponseDTO orderResponseDTO = orderMapper.toDTO(entity);
                            orderRedisRepository.save(orderRedisMapper.toEntity(orderResponseDTO));
                            return orderResponseDTO;
                        }).orElseThrow(() -> new RuntimeException("Order not found")));
    }

    @Override
    public List<OrderResponseDTO> getAllOrdersByUserId(Long userId) {
        return orderRepository.findByCustomerId(userId).stream().map(orderMapper::toDTO).toList();
    }

    @Override
    @Transactional
    public void updateStatus(UpdateStatusDTO updateStatusDTO) {
        Order order = orderRepository.findById(updateStatusDTO.orderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(updateStatusDTO.newStatus());
        orderRepository.save(order);
        orderRedisRepository.save(orderRedisMapper.toEntity(orderMapper.toDTO(order)));
    }

    @Override
    @Transactional
    public void acceptOrder(AcceptOrderDTO acceptOrderDTO) {
        Order order = orderRepository.findById(acceptOrderDTO.orderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        UserResponseDTO executor = userClient.getUserById(acceptOrderDTO.executorId());

        if (executor == null) {
            throw new RuntimeException("Executor not found");
        }

        if (!executor.role().equals("EXECUTOR")) {
            kafkaProducer.sendTopic(new OrderTopicDTO(acceptOrderDTO.orderId(),
                    "User is not a executor", "ERROR", LocalDateTime.now()));
            throw new RuntimeException("User is not a executor");
        }

        order.setExecutorId(acceptOrderDTO.executorId());
        order.setStatus(Order.Status.ACCEPTED.toString());

        kafkaProducer.sendTopic(new OrderTopicDTO(acceptOrderDTO.orderId(),
                "Accepted Order", "INFO", LocalDateTime.now()));

        orderRedisRepository.save(orderRedisMapper.toEntity(orderMapper.toDTO(order)));
    }
}