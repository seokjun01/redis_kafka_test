package com.example.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {

    @KafkaListener(topics = "my-topic", groupId = "my-1st-group")
    public void listen(String message) {
        System.out.println("[kafka] 수신 : " + message);
    }
}
