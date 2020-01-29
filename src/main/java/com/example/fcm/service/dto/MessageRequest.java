package com.example.fcm.service.dto;

import com.example.fcm.model.enums.NotificationAction;
import com.example.fcm.model.enums.NotificationType;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class MessageRequest {

    private Long deviceId;
    private Long externalId;
    private String userLogin;
    private String topicName;
    private String messageBody;
    private NotificationType notificationType;
    private NotificationAction notificationAction;
}
