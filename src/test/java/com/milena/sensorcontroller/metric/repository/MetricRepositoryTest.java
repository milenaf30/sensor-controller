package com.milena.sensorcontroller.metric.repository;

import com.milena.sensorcontroller.common.date.DateFactory;
import com.milena.sensorcontroller.common.uuid.UUIDFactory;
import com.milena.sensorcontroller.measurement.domain.Measurement;
import com.milena.sensorcontroller.metrics.domain.Metric;
import com.milena.sensorcontroller.metrics.repository.MetricRepository;
import com.milena.sensorcontroller.sensor.domain.Sensor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MetricRepositoryTest {

    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void When_InsertDependencies_ThenCorrect() {
        Assert.assertNotNull(metricRepository);
    }

    @Test
    public void When_InsertEntity_ThenCorrect() {
        Sensor sensor = Sensor.builder().uuid(UUIDFactory.create()).build();
        Sensor sensorRetrieved = testEntityManager.persist(sensor);
        Metric metric = Metric.builder()
                .max(200)
                .sensorId(sensorRetrieved.getId())
                .totalRecords(1)
                .sum(200)
                .date(new Date())
                .build();
        Metric metricRetrieved = metricRepository.save(metric);
        Assert.assertNotNull(metric.getId());
        Assert.assertEquals(metric.getMax(), metricRetrieved.getMax());
        Assert.assertEquals(metric.getDate(), metricRetrieved.getDate());
        Assert.assertEquals(metric.getSensorId(), sensorRetrieved.getId());
        Assert.assertEquals(metric.getSum(), metricRetrieved.getSum());
    }

    @Test
    public void When_FindMaxLast30Days_ThenCorrect() {
        Sensor sensor = Sensor.builder().uuid(UUIDFactory.create()).build();
        Date today = DateFactory.now();
        Date yesterday = DateFactory.yesterday();
        Integer carbonDioxideLevel = 2000;
        Integer maxCarbonDioxideLevel = 4000;
        Sensor sensorRetrieved = testEntityManager.persist(sensor);

        Measurement measurement = Measurement.builder()
                .time(today)
                .carbonDioxideLevel(carbonDioxideLevel)
                .sensor(sensorRetrieved)
                .build();
        Measurement measurement2 = Measurement.builder()
                .time(yesterday)
                .carbonDioxideLevel(maxCarbonDioxideLevel)
                .sensor(sensorRetrieved)
                .build();

        Metric metric = Metric.builder()
                .measurement(measurement)
                .build();
        metricRepository.save(metric);

        Metric metric2 = Metric.builder()
                .measurement(measurement2)
                .build();
        metricRepository.save(metric2);

        Integer max = metricRepository.findMaxLast30DaysFromSensorId(sensor.getId());

        Assert.assertEquals(maxCarbonDioxideLevel, max);
    }

    @Test
    public void When_FindSumLast30Days_ThenCorrect() {
        Sensor sensor = Sensor.builder().uuid(UUIDFactory.create()).build();
        Date today = DateFactory.now();
        Date yesterday = DateFactory.yesterday();
        Integer carbonDioxideLevel = 2000;
        Integer maxCarbonDioxideLevel = 4000;
        Sensor sensorRetrieved = testEntityManager.persist(sensor);

        Measurement measurement = Measurement.builder()
                .time(today)
                .carbonDioxideLevel(carbonDioxideLevel)
                .sensor(sensorRetrieved)
                .build();
        Measurement measurement2 = Measurement.builder()
                .time(yesterday)
                .carbonDioxideLevel(maxCarbonDioxideLevel)
                .sensor(sensorRetrieved)
                .build();

        Metric metric = Metric.builder()
                .measurement(measurement)
                .build();
        metricRepository.save(metric);

        Metric metric2 = Metric.builder()
                .measurement(measurement2)
                .build();
        metricRepository.save(metric2);

        Integer sum = metricRepository.findSumLast30DaysFromSensorId(sensor.getId());

        Assert.assertEquals(Integer.valueOf(maxCarbonDioxideLevel + carbonDioxideLevel), sum);
    }

    @Test
    public void When_FindTotalRecordsLast30Days_ThenCorrect() {
        Sensor sensor = Sensor.builder().uuid(UUIDFactory.create()).build();
        Date today = DateFactory.now();
        Date yesterday = DateFactory.yesterday();
        Integer carbonDioxideLevel = 2000;
        Integer maxCarbonDioxideLevel = 4000;
        Sensor sensorRetrieved = testEntityManager.persist(sensor);

        Measurement measurement = Measurement.builder()
                .time(today)
                .carbonDioxideLevel(carbonDioxideLevel)
                .sensor(sensorRetrieved)
                .build();
        Measurement measurement2 = Measurement.builder()
                .time(yesterday)
                .carbonDioxideLevel(maxCarbonDioxideLevel)
                .sensor(sensorRetrieved)
                .build();

        Metric metric = Metric.builder()
                .measurement(measurement)
                .build();
        metricRepository.save(metric);

        Metric metric2 = Metric.builder()
                .measurement(measurement2)
                .build();
        metricRepository.save(metric2);

        Integer totalRecords = metricRepository.findTotalRecordsLast30DaysFromSensorId(sensor.getId());

        Assert.assertEquals(Integer.valueOf(2), totalRecords);
    }
}
