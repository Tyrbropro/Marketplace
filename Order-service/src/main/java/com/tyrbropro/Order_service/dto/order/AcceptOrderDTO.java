package com.tyrbropro.Order_service.dto.order;

public record AcceptOrderDTO(
        Long orderId,
        Long executorId
) { }
