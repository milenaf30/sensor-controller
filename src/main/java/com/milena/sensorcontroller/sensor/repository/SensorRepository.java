package com.milena.sensorcontroller.sensor.repository;

import com.milena.sensorcontroller.sensor.domain.Sensor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends PagingAndSortingRepository<Sensor, Long> {
    Sensor findByUuid(String uuid);
}
