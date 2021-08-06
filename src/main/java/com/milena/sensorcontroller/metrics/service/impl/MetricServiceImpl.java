package com.milena.sensorcontroller.metrics.service.impl;

import com.milena.sensorcontroller.measurement.domain.Measurement;
import com.milena.sensorcontroller.metrics.domain.Metric;
import com.milena.sensorcontroller.metrics.repository.MetricRepository;
import com.milena.sensorcontroller.metrics.service.MetricService;
import org.springframework.stereotype.Service;

@Service
public class MetricServiceImpl implements MetricService {

    private final MetricRepository metricRepository;

    public MetricServiceImpl(MetricRepository metricRepository) {
        this.metricRepository = metricRepository;
    }

    @Override
    public Integer getMaxLast30DaysFromSensorId(Integer sensorId) {
        return metricRepository.findMaxLast30DaysFromSensorId(sensorId);
    }

    @Override
    public Integer getSumLast30DaysFromSensorId(Integer sensorId) {
        return metricRepository.findSumLast30DaysFromSensorId(sensorId);
    }

    @Override
    public Integer getTotalCountLast30DaysFromSensorId(Integer sensorId) {
        return metricRepository.findTotalRecordsLast30DaysFromSensorId(sensorId);
    }

    @Override
    public void createOrUpdateDailyMetric(Measurement measurement) {
        Metric metric = metricRepository.findByDateAndSensorId(measurement.getTime(), measurement.getSensorIdFromSensor());
        if (metric != null) {
            metric.addMeasurement(measurement);
        } else {
            metric = Metric.builder()
                    .measurement(measurement)
                    .build();
        }
        metricRepository.save(metric);
    }
}
