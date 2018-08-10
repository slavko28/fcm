package com.example.fcm.service.impl;

import com.example.fcm.model.Device;
import com.example.fcm.service.ConsumerService;
import com.example.fcm.service.DeviceService;
import com.example.fcm.service.NotificationService;
import com.example.fcm.service.dto.MessageRequest;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerServiceImpl implements ConsumerService {

    private final DeviceService deviceService;

    private final NotificationService notificationService;

    @Autowired
    public ConsumerServiceImpl(DeviceService deviceService, NotificationService notificationService) {
        this.deviceService = deviceService;
        this.notificationService = notificationService;
    }

    @Override
    @KafkaListener(topics = "${spring.kafka.topic.external_id}")
    public void receiveExternalId(@Payload MessageRequest messageRequest,
                                  @Headers MessageHeaders headers) {
        log.info("Received message: \n {}", messageRequest);
        Device device = deviceService.setExternalId(messageRequest);
        log.info("Device updated. Assigned external Id: {}", device.getExternalId());
        try {
            notificationService.sendExternalIdToDevice(device);
        } catch (FirebaseMessagingException e) {
            log.info("Can not send assigned device external id to device. Token: {}", device.getToken());
            e.printStackTrace();
        }
    }
}
