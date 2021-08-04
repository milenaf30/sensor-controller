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
                id(1)
                .build();

        Measurement measurement2 = Measurement.builder()
                .id(1)
                .build();

        Assert.assertEquals(measurement1, measurement2);
        Assert.assertEquals(measurement1.hashCode(), measurement2.hashCode());
    }

    @org.junit.Test
    public void When_CreateDifferentEntities_ThenCorrect() {
        Measurement measurement1 = Measurement.builder().
                id(1)
                .build();

        Measurement measurement2 = Measurement.builder()
                .id(2)
                .build();

        Assert.assertNotEquals(measurement1, measurement2);
        Assert.assertNotEquals(measurement1.hashCode(), measurement2.hashCode());
    }

    @Test
    public void When_CreateEntity_ThenCorrect() {
        Integer carbonDioxideMeasure = 2000;
        Date time = new Date();
        Measurement measurement = Measurement.builder().
                id(1)
                .time(time)
                .carbonDioxideLevel(carbonDioxideMeasure)
                .build();

        Assert.assertEquals(measurement.getTime(), time);
        Assert.assertEquals(measurement.getCarbonDioxideLevel(), carbonDioxideMeasure);
    }
}
