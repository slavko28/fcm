package com.example.fcm.service.impl;

import com.example.fcm.model.Device;
import com.example.fcm.service.DeviceService;
import com.example.fcm.service.ProducerService;
import com.example.fcm.service.RegistrationService;
import com.example.fcm.service.dto.DeviceDTO;
import com.example.fcm.service.dto.MessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Value("${spring.kafka.topic.new_device}")
    private String newDeviceTopic;

    private final DeviceService deviceService;

    private final ProducerService producerService;

    @Autowired
    public RegistrationServiceImpl(DeviceService deviceService, ProducerService producerService) {
        this.deviceService = deviceService;
        this.producerService = producerService;
    }

    @Override
    public void registerDevice(DeviceDTO deviceDTO) {
        log.info("Registering new device for user with login: '{}'", deviceDTO.getUserLogin());
        Device device = deviceService.save(deviceDTO);
        externalIdRequest(device.getId(), deviceDTO.getUserLogin());
    }

    private void externalIdRequest(Long deviceId, String userLogin) {
        MessageRequest messageRequest = MessageRequest.builder()
                .deviceId(deviceId)
                .userLogin(userLogin)
                .build();
        producerService.publish(newDeviceTopic, messageRequest);
    }

    @Override
    public void updateDeviceToken(DeviceDTO deviceDTO) {

    }

    @Override
    public void deleteDevice(DeviceDTO deviceDTO) {

    }
}
