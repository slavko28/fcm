package com.example.fcm.web.controller;

import com.example.fcm.service.RegistrationService;
import com.example.fcm.service.dto.DeviceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/device")
@SuppressWarnings("unused")
public class DeviceController {

    private final RegistrationService registrationService;

    @Autowired
    public DeviceController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public void deviceManager(@RequestBody DeviceDTO deviceDTO) {
        registrationService.registerDevice(deviceDTO);
    }

}
