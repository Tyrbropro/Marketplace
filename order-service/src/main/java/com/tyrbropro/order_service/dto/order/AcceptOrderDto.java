package com.tyrbropro.order_service.dto.order;

public record AcceptOrderDto(
        Long orderId,
        Long executorId
) { }
