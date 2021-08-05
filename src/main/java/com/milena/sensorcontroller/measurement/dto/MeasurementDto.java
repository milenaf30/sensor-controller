package com.milena.sensorcontroller.measurement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDto {

    @JsonProperty("co2")
    private Integer carbonDioxideLevel;

    @JsonProperty("time")
    private Date time;

    @JsonIgnore
    private String uuid;
}
