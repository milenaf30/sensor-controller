package com.milena.sensorcontroller.sensor.domain;

import com.milena.sensorcontroller.common.uuid.UUIDFactory;
import com.milena.sensorcontroller.measurement.domain.Measurement;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest(classes = Sensor.class)
public class SensorTest {

    @Test
    public void When_CreateSameEntities_ThenCorrect() {
        Sensor sensor1 = Sensor.builder().
                id(1)
                .build();

        Sensor sensor2 = Sensor.builder()
                .id(1)
                .build();

        Assert.assertEquals(sensor1, sensor2);
        Assert.assertEquals(sensor1.hashCode(), sensor2.hashCode());
    }

    @Test
    public void When_CreateDifferentEntities_ThenCorrect() {
        Sensor sensor1 = Sensor.builder().
                id(1)
                .build();

        Sensor sensor2 = Sensor.builder()
                .id(2)
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

    @Test
    public void When_GetStatus_ThenCorrect() {
        String uuid = UUIDFactory.create();
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();

        Assert.assertEquals(Sensor.SensorStatus.OK, sensor.getStatus());
    }

    @Test
    public void When_AddLowMeasurement_ThenStatusOk() {
        String uuid = UUIDFactory.create();
        Date now = new Date();
        Measurement measurement = Measurement.builder()
                .carbonDioxideLevel(1000)
                .time(now)
                .build();
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();

        sensor.addMeasurement(measurement);
        Assert.assertEquals(Sensor.SensorStatus.OK, sensor.getStatus());
    }

    @Test
    public void When_AddHighMeasurement_ThenStatusWarm() {
        String uuid = UUIDFactory.create();
        Date now = new Date();
        Measurement measurement = Measurement.builder()
                .carbonDioxideLevel(3000)
                .time(now)
                .build();
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();

        sensor.addMeasurement(measurement);
        Assert.assertEquals(Sensor.SensorStatus.WARM, sensor.getStatus());
    }

    @Test
    public void When_AddTwoHighMeasurement_ThenStatusWarm() {
        String uuid = UUIDFactory.create();
        Date now = new Date();
        Measurement measurement = Measurement.builder()
                .carbonDioxideLevel(3000)
                .time(now)
                .build();
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();

        sensor.addMeasurement(measurement);
        sensor.addMeasurement(measurement);
        Assert.assertEquals(Sensor.SensorStatus.WARM, sensor.getStatus());
    }

    @Test
    public void When_AddThreeHighMeasurement_ThenStatusALERT() {
        String uuid = UUIDFactory.create();
        Date now = new Date();
        Measurement measurement = Measurement.builder()
                .carbonDioxideLevel(3000)
                .time(now)
                .build();
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();

        sensor.addMeasurement(measurement);
        sensor.addMeasurement(measurement);
        sensor.addMeasurement(measurement);
        Assert.assertEquals(Sensor.SensorStatus.ALERT, sensor.getStatus());
    }
}
