package com.milena.sensorcontroller.metrics.service.impl;

import com.milena.sensorcontroller.metrics.domain.Metric;
import com.milena.sensorcontroller.metrics.repository.MetricRepository;
import com.milena.sensorcontroller.metrics.service.MetricService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MetricServiceImpl implements MetricService {

    private final MetricRepository metricRepository;

    public MetricServiceImpl(MetricRepository metricRepository) {
        this.metricRepository = metricRepository;
    }

    @Override
    public Metric findByDateAndSensorId(Date date, Integer sensorId) {
        return metricRepository.findByDateAndSensorId(date, sensorId);
    }

    @Override
    public Metric save(Metric metric) {
        return metricRepository.save(metric);
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
}
