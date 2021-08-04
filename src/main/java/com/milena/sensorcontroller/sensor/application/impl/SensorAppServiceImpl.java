package com.milena.sensorcontroller.sensor.application.impl;

import com.milena.sensorcontroller.sensor.application.SensorAppService;
import com.milena.sensorcontroller.sensor.domain.Sensor;
import com.milena.sensorcontroller.sensor.dto.SensorDto;
import com.milena.sensorcontroller.sensor.service.SensorService;
import org.springframework.stereotype.Service;

@Service
public class SensorAppServiceImpl implements SensorAppService {

    private final SensorService sensorService;

    public SensorAppServiceImpl(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public SensorDto getByUUID(String uuid) {
        Sensor sensor = sensorService.findByUUID(uuid);
        return SensorDto.builder()
                .status(Sensor.SensorStatus.OK)
                .build();
    }
}
