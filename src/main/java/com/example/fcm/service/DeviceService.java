package com.example.fcm.service;

import com.example.fcm.model.Device;
import com.example.fcm.service.dto.DeviceDTO;
import com.example.fcm.service.dto.MessageRequest;

public interface DeviceService {

    Device getById(Long id);

    Device save(DeviceDTO deviceDTO);

    DeviceDTO update(DeviceDTO deviceDTO);

    void delete(DeviceDTO deviceDTO);

    Device findByExternalID(Long externalId);

    Device setExternalId(MessageRequest messageRequest);
}
