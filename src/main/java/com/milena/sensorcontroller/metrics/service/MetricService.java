package com.milena.sensorcontroller.metrics.service;

import com.milena.sensorcontroller.measurement.domain.Measurement;

public interface MetricService {
    Integer getMaxLast30DaysFromSensorId(Integer sensorId);

    Integer getSumLast30DaysFromSensorId(Integer sensorId);

    Integer getTotalCountLast30DaysFromSensorId(Integer sensorId);

    void createOrUpdateDailyMetric(Measurement measurement);
}
