package com.milena.sensorcontroller.metric.domain;

import com.milena.sensorcontroller.metrics.domain.Metric;
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
        Metric metric1 = Metric.builder().
                id(1)
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
        Metric metric = Metric.builder()
                .id(1)
                .max(10)
                .sum(10)
                .date(time)
                .totalRecords(1)
                .build();

        Assert.assertEquals(metric.getDate(), time);
    }

}
