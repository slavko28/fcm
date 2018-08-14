package com.example.fcm.service.dto;

import com.example.fcm.model.enums.DevicePlatform;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceDTO {

    private Long externalId;
    private String userLogin;
    private String token;
    private DevicePlatform platform;
    private Boolean enable;
}
