package com.milena.sensorcontroller.sensor.controller;

import com.milena.sensorcontroller.sensor.application.SensorAppService;
import com.milena.sensorcontroller.sensor.dto.SensorDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sensors")
@Api(tags = "Sensor")
public class SensorController {

    private final SensorAppService sensorAppService;

    public SensorController(SensorAppService sensorAppService) {
        this.sensorAppService = sensorAppService;
    }

    @GetMapping("/{uuid}")
    @ApiOperation(value = "Get the status of the sensor by providing its uuid.")
    public SensorDto getByUUID(@PathVariable("uuid") String uuid) {
        return sensorAppService.getByUUID(uuid);
    }
}
