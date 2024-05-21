package com.module.api.event;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateCompletionProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${custom.topic.name}")
    private String topicName;

    public static final String COMPLEITION = "complition";

    @GetMapping("/publish")
    public void publish() {
        this.kafkaTemplate.send(topicName, COMPLEITION);
    }
}
