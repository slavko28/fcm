package com.example.fcm.service.impl;

import com.example.fcm.service.NotificationService;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.TopicManagementResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.firebase.messaging.FirebaseMessaging.getInstance;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void subscribeToTopic(List<String> tokens, String topicName) throws FirebaseMessagingException {
        TopicManagementResponse response = getInstance().subscribeToTopic(tokens, topicName);
        log.info("{} devices successfully subscribed on topic '{}'", response.getSuccessCount(), topicName);
    }

    @Override
    public void unsubscribeFromTopic(List<String> tokens, String topicName) throws FirebaseMessagingException {
        TopicManagementResponse response = getInstance().unsubscribeFromTopic(tokens, topicName);
        log.info("{} devices successfully unsubscribed from topic '{}'", response.getSuccessCount(), topicName);
    }

    @Override
    public String sendMessage(Message message) throws FirebaseMessagingException {
        String messageId = getInstance().send(message);
        log.info("Message send successfully. message id: '{}'", messageId);
        return messageId;
    }

}
