package com.milena.sensorcontroller.metric.repository;

import com.milena.sensorcontroller.metrics.domain.Metric;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends PagingAndSortingRepository<Metric, Integer> {
}

