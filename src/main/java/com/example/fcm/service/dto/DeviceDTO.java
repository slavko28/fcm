package com.example.fcm.service.dto;

import com.example.fcm.model.enums.DevicePlatform;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeviceDTO {

    private Long externalId;
    private String token;
    private DevicePlatform platform;
    private Boolean enable;
}
