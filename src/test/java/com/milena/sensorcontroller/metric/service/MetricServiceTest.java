package com.milena.sensorcontroller.metric.service;


import com.milena.sensorcontroller.common.date.DateFactory;
import com.milena.sensorcontroller.common.uuid.UUIDFactory;
import com.milena.sensorcontroller.measurement.domain.Measurement;
import com.milena.sensorcontroller.metrics.domain.Metric;
import com.milena.sensorcontroller.metrics.repository.MetricRepository;
import com.milena.sensorcontroller.metrics.service.MetricService;
import com.milena.sensorcontroller.metrics.service.impl.MetricServiceImpl;
import com.milena.sensorcontroller.sensor.domain.Sensor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MetricService.class, MetricServiceImpl.class})
public class MetricServiceTest {
    @MockBean
    private MetricRepository metricRepository;

    @Autowired
    private MetricService metricService;

    @Test
    public void When_InsertDependencies_ThenCorrect() {
        Assert.assertNotNull(metricRepository);
        Assert.assertNotNull(metricService);
    }

    @Test
    public void When_updateMetric_ThenCreateCorrect() {
        String uuid = UUIDFactory.create();
        Date today = DateFactory.now();
        Sensor sensor = Sensor.builder()
                .id(1)
                .uuid(uuid)
                .build();

        Measurement measurement = Measurement.builder()
                .sensor(sensor)
                .time(today)
                .carbonDioxideLevel(2000)
                .build();


        Metric metric = Metric.builder()
                .id(1)
                .measurement(measurement)
                .build();

        when(metricRepository.findByDateAndSensorId(today, sensor.getId())).thenReturn(metric);

        metricService.createOrUpdateDailyMetric(measurement);

        ArgumentCaptor<Metric> metricCaptor = ArgumentCaptor.forClass(Metric.class);

        verify(metricRepository, times(1)).save(metricCaptor.capture());

        Assert.assertEquals(Integer.valueOf(measurement.getCarbonDioxideLevel() + measurement.getCarbonDioxideLevel()),
                metricCaptor.getValue().getSum());
    }

    @Test
    public void When_createMetric_ThenCreateCorrect() {
        String uuid = UUIDFactory.create();
        Date today = DateFactory.now();
        Sensor sensor = Sensor.builder()
                .id(1)
                .uuid(uuid)
                .build();

        Measurement measurement = Measurement.builder()
                .sensor(sensor)
                .time(today)
                .carbonDioxideLevel(2000)
                .build();

        when(metricRepository.findByDateAndSensorId(today, sensor.getId())).thenReturn(null);

        metricService.createOrUpdateDailyMetric(measurement);

        ArgumentCaptor<Metric> metricCaptor = ArgumentCaptor.forClass(Metric.class);

        verify(metricRepository, times(1)).save(metricCaptor.capture());

        Assert.assertEquals(measurement.getCarbonDioxideLevel(), metricCaptor.getValue().getSum());
    }
}
