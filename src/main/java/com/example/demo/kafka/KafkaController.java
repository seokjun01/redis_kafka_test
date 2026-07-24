package com.example.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/kafka/publish")
    public String publish(@RequestParam String message) {
        kafkaTemplate.send("my-topic", message);
        return "발행 완료: " + message;
    }

    @PostMapping("/kafka/publish-partitioned")
    public String publishPartitioned(@RequestParam String key, @RequestParam String message) {
        kafkaTemplate.send("my-topic-partitioned", key, message);
        return "발행 완료: key=" + key + ", message=" + message;
    }
}
