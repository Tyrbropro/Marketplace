package com.tyrbropro.Order_service.dto.order;

public record UpdateStatusDTO(
        Long orderId,
        String newStatus
) {

}
