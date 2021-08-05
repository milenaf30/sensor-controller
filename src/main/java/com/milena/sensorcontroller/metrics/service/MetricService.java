package com.milena.sensorcontroller.metrics.service;

import com.milena.sensorcontroller.metrics.domain.Metric;

import java.util.Date;

public interface MetricService {
    Metric findByDateAndSensorId(Date date, Integer sensorId);

    Metric save(Metric metric);
}
