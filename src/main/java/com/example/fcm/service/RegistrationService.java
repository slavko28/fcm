package com.example.fcm.service;

import com.example.fcm.service.dto.DeviceDTO;

public interface RegistrationService {

    void registerDevice(DeviceDTO deviceDTO);

    void updateDeviceToken(DeviceDTO deviceDTO);

    void deleteDevice(DeviceDTO deviceDTO);

}
