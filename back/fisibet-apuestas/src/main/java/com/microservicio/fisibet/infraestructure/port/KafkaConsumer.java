package com.microservicio.fisibet.infraestructure.port;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "topic1", groupId = "myId")
    public void consume(String message) {
        // Process the Kafka message
        System.out.println("Received message: " + message);
    }
}