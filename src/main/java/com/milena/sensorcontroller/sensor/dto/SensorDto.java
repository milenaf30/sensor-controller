package com.milena.sensorcontroller.sensor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.milena.sensorcontroller.sensor.domain.Sensor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorDto {

    @JsonProperty("status")
    private Sensor.SensorStatus status;
}
