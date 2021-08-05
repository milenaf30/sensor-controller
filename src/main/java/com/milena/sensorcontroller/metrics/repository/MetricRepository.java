package com.milena.sensorcontroller.metrics.repository;

import com.milena.sensorcontroller.metrics.domain.Metric;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface MetricRepository extends PagingAndSortingRepository<Metric, Integer> {
    Metric findByDateAndSensorId(Date date, Integer sensorId);
}

