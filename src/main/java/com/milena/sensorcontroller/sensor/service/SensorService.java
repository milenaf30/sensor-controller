package com.milena.sensorcontroller.sensor.service;

import com.milena.sensorcontroller.sensor.domain.Sensor;

public interface SensorService {
    Sensor findByUUID(String uuid);

    Sensor save(Sensor sensor);
}
