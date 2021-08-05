package com.milena.sensorcontroller.metric.repository;

import com.milena.sensorcontroller.common.uuid.UUIDFactory;
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
                .sum(10)
                .date(new Date())
                .build();
        Metric metricRetrieved = metricRepository.save(metric);
        Assert.assertEquals(metric.getMax(), metricRetrieved.getMax());
        Assert.assertEquals(metric.getDate(), metricRetrieved.getDate());
    }
}
