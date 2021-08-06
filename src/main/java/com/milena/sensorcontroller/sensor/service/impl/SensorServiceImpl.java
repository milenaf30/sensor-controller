package com.milena.sensorcontroller.sensor.service.impl;

import com.milena.sensorcontroller.sensor.domain.Sensor;
import com.milena.sensorcontroller.sensor.repository.SensorRepository;
import com.milena.sensorcontroller.sensor.service.SensorService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public Sensor findByUUID(String uuid) {
        Sensor sensor = sensorRepository.findByUuid(uuid);
        if (sensor == null) {
            throw new EntityNotFoundException();
        }
        return sensor;
    }

    @Override
    public Sensor save(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public Sensor getOrCreateSensor(String uuid) {
        Sensor sensor = sensorRepository.findByUuid(uuid);
        if (sensor != null) {
            return sensor;
        }
        return sensorRepository.save(Sensor.builder().uuid(uuid).build());
    }
}
