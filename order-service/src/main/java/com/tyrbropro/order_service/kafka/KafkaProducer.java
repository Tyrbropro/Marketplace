package com.tyrbropro.order_service.kafka;

import com.tyrbropro.avro.OrderTopicDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class KafkaProducer {

    KafkaTemplate<String, OrderTopicDto> kafkaTemplate;

    public void sendTopic(String topic, OrderTopicDto dto) {
            kafkaTemplate.send(topic, dto);
    }
}
