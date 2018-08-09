package com.example.fcm.service.impl;

import com.example.fcm.service.ProducerService;
import com.example.fcm.service.dto.MessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProducerServiceImpl implements ProducerService {

    private final KafkaTemplate<String, MessageRequest> kafkaTemplate;

    @Autowired
    public ProducerServiceImpl(KafkaTemplate<String, MessageRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(String topicName, MessageRequest request) {

        log.info("Sending message:\n request='{}'\n to topic='{}'", request, topicName);

        Message<MessageRequest> message = MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        kafkaTemplate.send(message);
    }
}
