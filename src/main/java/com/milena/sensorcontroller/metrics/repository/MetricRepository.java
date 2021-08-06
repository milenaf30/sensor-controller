package com.milena.sensorcontroller.metrics.repository;

import com.milena.sensorcontroller.metrics.domain.Metric;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface MetricRepository extends PagingAndSortingRepository<Metric, Integer> {
    Metric findByDateAndSensorId(Date date, Integer sensorId);

    @Query(nativeQuery = true,
            value = "SELECT MAX(max) FROM  metrics_per_day \n " +
                    "WHERE date BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE() \n " +
                    "and sensor_id = (:sensorId)")
    Integer findMaxLast30DaysFromSensorId(@Param("sensorId") Integer sensorId);

    @Query(nativeQuery = true,
            value = "SELECT sum(sum) FROM  metrics_per_day \n " +
                    "WHERE date BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE() \n " +
                    "and sensor_id = (:sensorId)")
    Integer findSumLast30DaysFromSensorId(@Param("sensorId") Integer sensorId);

    @Query(nativeQuery = true,
            value = "SELECT sum(total_records) FROM  metrics_per_day \n " +
                    "WHERE date BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE() \n " +
                    "and sensor_id = (:sensorId)")
    Integer findTotalRecordsLast30DaysFromSensorId(@Param("sensorId") Integer sensorId);
}

