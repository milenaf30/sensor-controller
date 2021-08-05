package com.milena.sensorcontroller.sensor.controller;

import com.milena.sensorcontroller.common.controller.BaseController;
import com.milena.sensorcontroller.measurement.dto.MeasurementDto;
import com.milena.sensorcontroller.sensor.application.SensorAppService;
import com.milena.sensorcontroller.sensor.dto.SensorDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sensors")
@Api(tags = "Sensor")
public class SensorController extends BaseController {

    private final SensorAppService sensorAppService;

    public SensorController(SensorAppService sensorAppService) {
        this.sensorAppService = sensorAppService;
    }

    @GetMapping("/{uuid}")
    @ApiOperation(value = "Get the status of the sensor by providing its uuid.")
    public SensorDto getByUUID(@PathVariable("uuid") String uuid) {
        return sensorAppService.getByUUID(uuid);
    }

    @PostMapping("/{uuid}/measurements")
    @ApiOperation(value = "Save the carbon dioxide level of the sensor with uuid.")
    public HttpStatus saveMeasurement(@PathVariable("uuid") String uuid,
                                      @RequestBody MeasurementDto measurementDto) {
        measurementDto.setUuid(uuid);
        sensorAppService.saveMeasurement(measurementDto);
        return HttpStatus.OK;
    }
}
