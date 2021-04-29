package com.example.messageconsumer;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
@Getter
public class MessagesConsumer {
    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "messages", groupId = "kafka-sandbox")
    public void listen(String message) {
        synchronized (messages) {
            log.info("Received message: {}", message);
            messages.add(message);
        }
    }
}
