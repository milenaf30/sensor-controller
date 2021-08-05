package com.milena.sensorcontroller.sensor.application;

import com.milena.sensorcontroller.measurement.dto.MeasurementDto;
import com.milena.sensorcontroller.sensor.dto.SensorDto;

public interface SensorAppService {
    SensorDto getByUUID(String uuid);

    void saveMeasurement(MeasurementDto measurementDto);
}
