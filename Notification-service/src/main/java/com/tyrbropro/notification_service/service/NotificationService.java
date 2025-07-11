package com.tyrbropro.notification_service.service;

import com.tyrbropro.notification_service.dto.OrderTopicDto;
import com.tyrbropro.notification_service.dto.notification.NotificationResponseDto;
import java.util.List;

public interface NotificationService {
    void processNotification(OrderTopicDto dto);

    NotificationResponseDto markAsRead(Long id);

    List<NotificationResponseDto> markAllAsRead(Long userId);
}
