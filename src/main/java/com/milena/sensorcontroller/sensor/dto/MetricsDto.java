package com.milena.sensorcontroller.sensor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetricsDto {

    @JsonProperty("maxLast30Days")
    @Builder.Default
    private Integer maxLast30Days = 0;

    @JsonProperty("avgLast30Days")
    @Builder.Default
    private Integer avgLast30Days = 0;

}
