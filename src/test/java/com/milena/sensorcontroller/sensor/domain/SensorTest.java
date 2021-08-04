package com.milena.sensorcontroller.sensor.domain;

import com.milena.sensorcontroller.common.uuid.UUIDFactory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Sensor.class)
public class SensorTest {

    @Test
    public void When_CreateSameEntities_ThenCorrect() {
        Sensor sensor1 = Sensor.builder().
                id(1L)
                .build();

        Sensor sensor2 = Sensor.builder()
                .id(1L)
                .build();

        Assert.assertEquals(sensor1, sensor2);
        Assert.assertEquals(sensor1.hashCode(), sensor2.hashCode());
    }

    @Test
    public void When_CreateDifferentEntities_ThenCorrect() {
        Sensor sensor1 = Sensor.builder().
                id(1L)
                .build();

        Sensor sensor2 = Sensor.builder()
                .id(2L)
                .build();

        Assert.assertNotEquals(sensor1, sensor2);
        Assert.assertNotEquals(sensor1.hashCode(), sensor2.hashCode());
    }

    @Test
    public void When_CreateEntity_ThenCorrect() {
        String uuid = UUIDFactory.create();
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();

        Assert.assertEquals(sensor.getUuid(), uuid);
    }
}
