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
    public static final String DEVICE_TOKEN = "device token";

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DeviceRepository repository;

    @Before
    public void setUp() {
        entityManager.persist(getDevice());
        entityManager.flush();
    }

    private Device getDevice() {
        return Device.builder()
                .enable(true)
                .externalId(EXTERNAL_ID)
                .platform(DevicePlatform.android)
                .token(DEVICE_TOKEN)
                .build();
    }

    @Test
    public void whenFindByExternalId_thenReturnDevice() {
//        when
        Optional<Device> found = repository.findByExternalId(EXTERNAL_ID);
//        then
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getToken()).isEqualTo(DEVICE_TOKEN);
    }

    @Test
    public void whenDeleteDeviceByExternalId_thenDoNotFindDevice() {
//        when
        repository.deleteByExternalId(EXTERNAL_ID);
//        then
        Optional<Device> byExternalId = repository.findByExternalId(EXTERNAL_ID);
        assertThat(byExternalId.isPresent()).isFalse();
    }


}