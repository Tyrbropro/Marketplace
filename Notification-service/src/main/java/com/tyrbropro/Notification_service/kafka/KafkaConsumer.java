package com.tyrbropro.Notification_service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyrbropro.Notification_service.dto.OrderTopicDTO;
import com.tyrbropro.Notification_service.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final ObjectMapper objectMapper;
    private final NotificationService notificationService;

    public KafkaConsumer(ObjectMapper objectMapper, NotificationService notificationService) {
        this.objectMapper = objectMapper;
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "order_topic", groupId = "order_group")
    public void listen(String message) {
        try {
            OrderTopicDTO dto = objectMapper.readValue(message, OrderTopicDTO.class);
            notificationService.processNotification(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing Kafka message: {}" + e);
        }

    }

}
