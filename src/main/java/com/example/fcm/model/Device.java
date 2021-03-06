package com.example.fcm.model;

import com.example.fcm.model.enums.DevicePlatform;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long externalId;
    @Enumerated(EnumType.STRING)
    private DevicePlatform platform;
    @ManyToOne
    private Topic topics;
    private String token;
    private Boolean enable;

}