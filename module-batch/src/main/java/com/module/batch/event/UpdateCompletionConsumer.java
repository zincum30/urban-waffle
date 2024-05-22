package com.module.batch.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UpdateCompletionConsumer {


    private static final String COMPLEITION = "complition";

    @KafkaListener(topics = "${custom.topic.name}")
    public void recerveMessage(String message) {
        if (COMPLEITION.equals(message)) {

        }
    }
}
