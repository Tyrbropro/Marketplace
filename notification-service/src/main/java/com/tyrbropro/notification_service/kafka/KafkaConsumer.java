package com.tyrbropro.notification_service.kafka;


import com.tyrbropro.avro.OrderTopicDto;
import com.tyrbropro.notification_service.service.NotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class KafkaConsumer {

    NotificationService notificationService;

    @KafkaListener(
            topics = {
                    "order_created_topic",
                    "user_not_customer_topic",
                    "user_not_executor_topic",
                    "order_accepted_topic"
            },
            groupId = "order_service"
    )
    public void listen(OrderTopicDto dto) {
        notificationService.processNotification(dto);
    }
}
