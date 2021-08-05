package com.milena.sensorcontroller.sensor.application.impl;

import com.milena.sensorcontroller.measurement.dto.MeasurementDto;
import com.milena.sensorcontroller.sensor.application.SensorAppService;
import com.milena.sensorcontroller.sensor.domain.Sensor;
import com.milena.sensorcontroller.sensor.dto.SensorDto;
import com.milena.sensorcontroller.sensor.service.SensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SensorAppServiceImpl implements SensorAppService {

    Logger logger = LoggerFactory.getLogger(SensorAppServiceImpl.class);
    private final SensorService sensorService;

    public SensorAppServiceImpl(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public SensorDto getByUUID(String uuid) {
        logger.info(String.format("Retrieving sensor's status by uuid %s", uuid));
        try {
            Sensor sensor = sensorService.findByUUID(uuid);
            SensorDto sensorDto = SensorDto.builder()
                    .status(sensor.getStatus())
                    .build();
            logger.info(String.format("Sensor's status by uuid %s retrieved", uuid));
            return sensorDto;
        } catch (Exception ex) {
            logger.error(String.format("Error retrieving sensor's status by uuid %s", uuid));
            logger.error(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public void saveMeasurement(MeasurementDto measurementDto) {

    }
}
