package com.tyrbropro.order_service.dto.order;

public record UpdateStatusDto(
        Long orderId,
        String newStatus
) {

}
