package com.milena.sensorcontroller.sensor.service.impl;

import com.milena.sensorcontroller.sensor.repository.SensorRepository;
import com.milena.sensorcontroller.sensor.service.SensorService;
import org.springframework.stereotype.Service;

@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

}
