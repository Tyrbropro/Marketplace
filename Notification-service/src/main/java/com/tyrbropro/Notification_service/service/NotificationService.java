package com.tyrbropro.Notification_service.service;

import com.tyrbropro.Notification_service.dto.OrderTopicDTO;
import com.tyrbropro.Notification_service.dto.notification.NotificationResponseDTO;
import java.util.List;

public interface NotificationService {
    void processNotification(OrderTopicDTO dto);
    NotificationResponseDTO markAsRead(Long id);
    List<NotificationResponseDTO> markAllAsRead(Long userId);
}
