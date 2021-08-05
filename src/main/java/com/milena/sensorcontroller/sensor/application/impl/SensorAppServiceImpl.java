package com.milena.sensorcontroller.sensor.application.impl;

import com.milena.sensorcontroller.measurement.domain.Measurement;
import com.milena.sensorcontroller.measurement.dto.MeasurementDto;
import com.milena.sensorcontroller.sensor.application.SensorAppService;
import com.milena.sensorcontroller.sensor.domain.Sensor;
import com.milena.sensorcontroller.sensor.dto.MetricsDto;
import com.milena.sensorcontroller.sensor.dto.SensorDto;
import com.milena.sensorcontroller.sensor.service.SensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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
        logger.info(String.format("Saving measurement of sensor with uuid %s", measurementDto.getUuid()));
        try {
            Sensor sensor = getOrCreateAndSaveSensorByUUID(measurementDto.getUuid());
            Measurement measurement = Measurement.builder()
                    .carbonDioxideLevel(measurementDto.getCarbonDioxideLevel())
                    .time(measurementDto.getTime())
                    .build();
            sensor.addMeasurement(measurement);
            sensorService.save(sensor);
            logger.info(String.format("Measurement of sensor with uuid %s saved", measurementDto.getUuid()));
        } catch (Exception ex) {
            logger.error(String.format("Error saving measurement of sensor with uuid %s", measurementDto.getUuid()));
            logger.error(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public MetricsDto getMetrics(String uuid) {
        return MetricsDto.builder().build();
    }

    private Sensor getOrCreateAndSaveSensorByUUID(String uuid) {
        try {
            return sensorService.findByUUID(uuid);
        } catch (EntityNotFoundException ex) {
            Sensor sensor = Sensor.builder()
                    .uuid(uuid)
                    .build();
            return sensorService.save(sensor);
        }
    }
}
