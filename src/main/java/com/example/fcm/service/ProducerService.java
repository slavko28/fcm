package com.example.fcm.service;

import com.example.fcm.service.dto.MessageRequest;

public interface ProducerService {

    void publish(String topicName, MessageRequest request);

}
