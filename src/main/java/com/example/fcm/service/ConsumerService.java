package com.example.fcm.service;

import com.example.fcm.service.dto.MessageRequest;
import org.springframework.messaging.MessageHeaders;

public interface ConsumerService {

    void receiveExternalId(MessageRequest messageRequest, MessageHeaders headers);
}
