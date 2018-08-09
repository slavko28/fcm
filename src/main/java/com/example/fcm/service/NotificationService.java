package com.example.fcm.service;

import com.example.fcm.model.Device;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

import java.util.List;

public interface NotificationService {

    void subscribeToTopic(List<String> tokens, String topicName) throws FirebaseMessagingException;

    void unsubscribeFromTopic(List<String> tokens, String topicName) throws FirebaseMessagingException;

    String sendMessage(Message message) throws FirebaseMessagingException;

    String sendExternalIdToDevice(Device device) throws FirebaseMessagingException;
}
