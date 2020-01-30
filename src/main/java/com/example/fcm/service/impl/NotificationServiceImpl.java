package com.example.fcm.service.impl;

import com.example.fcm.model.Device;
import com.example.fcm.service.NotificationService;
import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.firebase.messaging.FirebaseMessaging.getInstance;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    private static final String REGISTERING_TITLE = "assigned_id";

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
        log.info("Message send successfully. message Id: '{}'", messageId);
        return messageId;
    }

    @Override
    public String sendExternalIdToDevice(Device device) throws FirebaseMessagingException {
        // TODO: create message relative to the device platform
        Message message = getMessageForDevice(device);
        String messageId = getInstance().send(message);
        log.info("External device Id send successfully. message Id: '{}'", messageId);
        return messageId;
    }

    public Message getMessageForDevice(Device device) {
        return Message.builder()
                .setNotification(new Notification(REGISTERING_TITLE, device.getExternalId().toString()))
                .setToken(device.getToken())
                .setAndroidConfig(getAndroidConfig()).build();
    }

    private AndroidConfig getAndroidConfig() {
        return AndroidConfig.builder()
                .setNotification(AndroidNotification.builder()
                        .build())
                .build();
    }

}
