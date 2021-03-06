package com.milena.sensorcontroller.measurement.domain;

import com.milena.sensorcontroller.common.date.DateFactory;
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
        Date time = DateFactory.now();
        Measurement measurement = Measurement.builder().
                id(1)
                .time(time)
                .carbonDioxideLevel(carbonDioxideMeasure)
                .build();

        Assert.assertEquals(measurement.getTime(), time);
        Assert.assertEquals(measurement.getCarbonDioxideLevel(), carbonDioxideMeasure);
    }

    @Test
    public void When_CarbonDioxideHigh_ThenHigh() {
        Integer carbonDioxideMeasure = 2001;
        Date time = DateFactory.now();
        Measurement measurement = Measurement.builder().
                id(1)
                .time(time)
                .carbonDioxideLevel(carbonDioxideMeasure)
                .build();

        Assert.assertTrue(measurement.isHigh());
    }

    @Test
    public void When_CarbonDioxideLow_ThenLow() {
        Integer carbonDioxideMeasure = 1000;
        Date time = DateFactory.now();
        Measurement measurement = Measurement.builder().
                id(1)
                .time(time)
                .carbonDioxideLevel(carbonDioxideMeasure)
                .build();

        Assert.assertTrue(measurement.isLow());
    }
}
