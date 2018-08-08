package com.example.fcm.repository;

import com.example.fcm.model.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long>{
    Optional<Device> findByExternalId(Long externalId);

    void deleteByExternalId(Long externalId);
}
