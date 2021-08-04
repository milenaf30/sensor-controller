package com.milena.sensorcontroller.sensor.repository;

import com.milena.sensorcontroller.sensor.domain.Sensor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SensorRepository extends PagingAndSortingRepository<Sensor, Long> {
    Sensor findByUuid(String uuid);
}
