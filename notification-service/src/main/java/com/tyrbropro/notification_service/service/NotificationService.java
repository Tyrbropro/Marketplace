package com.tyrbropro.notification_service.service;

import com.tyrbropro.avro.OrderTopicDto;
import com.tyrbropro.notification_service.dto.NotificationResponseDto;
import java.util.List;

public interface NotificationService {
    void processNotification(OrderTopicDto dto);

    NotificationResponseDto markAsRead(Long id);

    List<NotificationResponseDto> markAllAsRead(Long userId);
}
