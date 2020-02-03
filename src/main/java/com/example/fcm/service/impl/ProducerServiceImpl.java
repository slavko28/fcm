package com.example.fcm.service.impl;

import com.example.fcm.service.ProducerService;
import com.example.fcm.service.dto.MessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

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
        Message<MessageRequest> message = getMessage(topicName, request);
        sentMessage(request, message);
    }

    public void sentMessage(MessageRequest request, Message<MessageRequest> message) {
        ListenableFuture<SendResult<String, MessageRequest>> resultListenableFuture = kafkaTemplate.send(message);
        resultListenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, MessageRequest>>() {
            @Override
            public void onSuccess(SendResult<String, MessageRequest> result) {
                log.info("Sent request=[{}] with offset=[{}]"+ request.toString(), result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable throwable) {
                log.error("Unable to send message=[{}] due to : {}", request.toString(), throwable.getMessage());
            }
        });
    }

    public Message<MessageRequest> getMessage(String topicName, MessageRequest request) {
        return MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
    }
}
