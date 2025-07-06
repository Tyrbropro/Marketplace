package com.tyrbropro.Order_service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyrbropro.Order_service.dto.order.OrderTopicDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendTopic(OrderTopicDTO orderTopicDTO) {
        try {
            String json = objectMapper.writeValueAsString(orderTopicDTO);
            kafkaTemplate.send("order_topic", json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
