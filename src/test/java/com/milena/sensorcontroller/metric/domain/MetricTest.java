package com.milena.sensorcontroller.metric.domain;

import com.milena.sensorcontroller.measurement.domain.Measurement;
import com.milena.sensorcontroller.metrics.domain.Metric;
import com.milena.sensorcontroller.sensor.domain.Sensor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest(classes = Metric.class)
public class MetricTest {

    @Test
    public void When_CreateSameEntities_ThenCorrect() {
        Metric metric1 = Metric.builder().
                id(1)
                .build();

        Metric metric2 = Metric.builder()
                .id(1)
                .build();

        Assert.assertEquals(metric1, metric2);
        Assert.assertEquals(metric1.hashCode(), metric2.hashCode());
    }

    @Test
    public void When_CreateDifferentEntities_ThenCorrect() {
        Metric metric1 = Metric.builder()
                .id(1)
                .build();

        Metric metric2 = Metric.builder()
                .id(2)
                .build();

        Assert.assertNotEquals(metric1, metric2);
        Assert.assertNotEquals(metric1.hashCode(), metric2.hashCode());
    }

    @Test
    public void When_CreateEntity_ThenCorrect() {
        Date time = new Date();
        Integer carbonDioxideLevel = 2000;
        Sensor sensor = Sensor.builder()
                .uuid("uuid")
                .id(1)
                .build();
        Measurement measurement = Measurement.builder()
                .time(time)
                .carbonDioxideLevel(carbonDioxideLevel)
                .sensor(sensor)
                .build();

        Metric metric = Metric.builder()
                .measurement(measurement)
                .build();

        Assert.assertEquals(metric.getDate(), time);
        Assert.assertEquals(metric.getSum(), carbonDioxideLevel);
        Assert.assertEquals(metric.getMax(), carbonDioxideLevel);
        Assert.assertEquals(metric.getTotalRecords(), Integer.valueOf(1));
        Assert.assertEquals(metric.getSensorId(), sensor.getId());
    }

    @Test
    public void When_AddMeasurementInNonInitializedMetric_ThenCorrect() {
        Date time = new Date();
        Integer carbonDioxideLevel = 2000;
        Sensor sensor = Sensor.builder()
                .uuid("uuid")
                .id(1)
                .build();
        Measurement measurement = Measurement.builder()
                .time(time)
                .carbonDioxideLevel(carbonDioxideLevel)
                .sensor(sensor)
                .build();

        Metric metric = Metric.builder()
                .sensorId(sensor.getId())
                .date(time)
                .build();

        metric.addMeasurement(measurement);

        Assert.assertEquals(metric.getDate(), time);
        Assert.assertEquals(metric.getTotalRecords(), Integer.valueOf(1));
        Assert.assertEquals(metric.getMax(), carbonDioxideLevel);
        Assert.assertEquals(metric.getSum(), carbonDioxideLevel);
    }
}