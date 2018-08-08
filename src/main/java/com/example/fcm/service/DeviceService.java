package com.example.fcm.service;

import com.example.fcm.model.Device;
import com.example.fcm.service.dto.DeviceDTO;

public interface DeviceService {

    DeviceDTO save(DeviceDTO deviceDTO);

    DeviceDTO update(DeviceDTO deviceDTO);

    void delete(DeviceDTO deviceDTO);

    Device findByExternalID(Long externalId);

}
