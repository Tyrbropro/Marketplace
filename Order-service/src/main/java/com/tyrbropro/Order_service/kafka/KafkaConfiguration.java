package com.tyrbropro.Order_service.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic orderTopic() {
        return new NewTopic("order_topic", 1, (short) 1);
    }
}
