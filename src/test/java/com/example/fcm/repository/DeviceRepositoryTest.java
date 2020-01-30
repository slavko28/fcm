package com.example.fcm.repository;

import com.example.fcm.model.Device;
import com.example.fcm.model.enums.DevicePlatform;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class DeviceRepositoryTest {

    public static final long EXTERNAL_ID = 11L;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DeviceRepository repository;

    @Before
    public void storeDevice() {
        Device device = getDevice();
        entityManager.persist(device);
        entityManager.flush();
    }

    @Test
    public void whenFindByExternalId_thenReturnDevice() {
//        given
        Device device = getDevice();
//        when
        Device found = repository.findByExternalId(11L).orElseGet(Device::new);
//        then
        assertThat(found.getToken()).isEqualTo(device.getToken());
    }

    @Test
    public void whenDeleteDeviceByExternalId_thenDoNotFindDevice() {
//        when
        repository.deleteByExternalId(EXTERNAL_ID);
//        then
        Optional<Device> byExternalId = repository.findByExternalId(EXTERNAL_ID);
        assertThat(byExternalId.isPresent()).isFalse();
    }

    private Device getDevice() {
        return Device.builder()
                .enable(true)
                .externalId(EXTERNAL_ID)
                .platform(DevicePlatform.android)
                .token("device token")
                .build();
    }
}