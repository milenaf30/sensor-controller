package com.milena.sensorcontroller.sensor.controller;

import com.milena.sensorcontroller.sensor.application.SensorAppService;
import com.milena.sensorcontroller.sensor.domain.Sensor;
import com.milena.sensorcontroller.sensor.dto.SensorDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sensors")
public class SensorController {

    private final SensorAppService sensorAppService;

    public SensorController(SensorAppService sensorAppService) {
        this.sensorAppService = sensorAppService;
    }

    @GetMapping("/{uuid}")
    public SensorDto getByUUID(@PathVariable("uuid") String uuid) {
        return SensorDto.builder()
                .status(Sensor.SensorStatus.OK)
                .build();
    }
}
