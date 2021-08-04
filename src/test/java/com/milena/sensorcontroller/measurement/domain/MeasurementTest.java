package com.milena.sensorcontroller.measurement.domain;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest(classes = Measurement.class)
public class MeasurementTest {

    @org.junit.Test
    public void When_CreateSameEntities_ThenCorrect() {
        Measurement measurement1 = Measurement.builder().
                id(1L)
                .build();

        Measurement measurement2 = Measurement.builder()
                .id(1L)
                .build();

        Assert.assertEquals(measurement1, measurement2);
        Assert.assertEquals(measurement1.hashCode(), measurement2.hashCode());
    }

    @org.junit.Test
    public void When_CreateDifferentEntities_ThenCorrect() {
        Measurement measurement1 = Measurement.builder().
                id(1L)
                .build();

        Measurement measurement2 = Measurement.builder()
                .id(2L)
                .build();

        Assert.assertNotEquals(measurement1, measurement2);
        Assert.assertNotEquals(measurement1.hashCode(), measurement2.hashCode());
    }

    @Test
    public void When_CreateEntity_ThenCorrect() {
        Integer carbonDioxideMeasure = 2000;
        Date time = new Date();
        Measurement measurement = Measurement.builder().
                id(1L)
                .time(time)
                .carbonDioxideLevel(carbonDioxideMeasure)
                .build();

        Assert.assertEquals(measurement.getTime(), time);
        Assert.assertEquals(measurement.getCarbonDioxideLevel(), carbonDioxideMeasure);
    }
}
