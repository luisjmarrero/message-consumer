package com.example.messageconsumer;

import lombok.Getter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class MessagesConsumer {
    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "messages", groupId = "kafka-sandbox")
    public void listen(String message) {
        synchronized (messages) {
            System.out.println("Received message: " + message);
            messages.add(message);
        }
    }
}
