package com.milena.sensorcontroller.sensor.domain;

import com.milena.sensorcontroller.common.date.DateFactory;
import com.milena.sensorcontroller.common.uuid.UUIDFactory;
import com.milena.sensorcontroller.measurement.domain.Measurement;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest(classes = Sensor.class)
public class SensorTest {

    private Measurement highMeasurement;
    private Measurement lowMeasurement;

    @BeforeEach
    public void setUp() {
        Date now = DateFactory.now();
        highMeasurement = Measurement.builder()
                .carbonDioxideLevel(3000)
                .time(now)
                .build();

        lowMeasurement = Measurement.builder()
                .carbonDioxideLevel(1000)
                .time(now)
                .build();
    }

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
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();

        sensor.addMeasurement(lowMeasurement);
        Assert.assertEquals(Sensor.SensorStatus.OK, sensor.getStatus());
    }

    @Test
    public void When_AddHighMeasurement_ThenStatusWarm() {
        String uuid = UUIDFactory.create();
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();

        sensor.addMeasurement(highMeasurement);
        Assert.assertEquals(Sensor.SensorStatus.WARM, sensor.getStatus());
    }

    @Test
    public void When_AddTwoHighMeasurement_ThenStatusWarm() {
        String uuid = UUIDFactory.create();
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();

        sensor.addMeasurement(highMeasurement);
        sensor.addMeasurement(highMeasurement);
        Assert.assertEquals(Sensor.SensorStatus.WARM, sensor.getStatus());
    }

    @Test
    public void When_AddThreeHighMeasurement_ThenStatusALERT() {
        String uuid = UUIDFactory.create();
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();

        sensor.addMeasurement(highMeasurement);
        sensor.addMeasurement(highMeasurement);
        sensor.addMeasurement(highMeasurement);
        Assert.assertEquals(Sensor.SensorStatus.ALERT, sensor.getStatus());
    }

    @Test
    public void When_AddLowMeasurementAfterALERT_ThenStatusALERT() {
        String uuid = UUIDFactory.create();
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();

        sensor.addMeasurement(highMeasurement);
        sensor.addMeasurement(highMeasurement);
        sensor.addMeasurement(highMeasurement);
        sensor.addMeasurement(lowMeasurement);

        Assert.assertEquals(Sensor.SensorStatus.ALERT, sensor.getStatus());
    }

    @Test
    public void When_AddTwoLowMeasurementAfterALERT_ThenStatusALERT() {
        String uuid = UUIDFactory.create();
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();

        sensor.addMeasurement(highMeasurement);
        sensor.addMeasurement(highMeasurement);
        sensor.addMeasurement(highMeasurement);
        sensor.addMeasurement(lowMeasurement);
        sensor.addMeasurement(lowMeasurement);

        Assert.assertEquals(Sensor.SensorStatus.ALERT, sensor.getStatus());
    }

    @Test
    public void When_AddThreeLowMeasurementAfterALERT_ThenStatusOK() {
        String uuid = UUIDFactory.create();

        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();

        sensor.addMeasurement(highMeasurement);
        sensor.addMeasurement(highMeasurement);
        sensor.addMeasurement(highMeasurement);
        sensor.addMeasurement(lowMeasurement);
        sensor.addMeasurement(lowMeasurement);
        sensor.addMeasurement(lowMeasurement);

        Assert.assertEquals(Sensor.SensorStatus.OK, sensor.getStatus());
    }

    @Test
    public void When_AddThreeLowMeasurementAfterALERTStatus_ThenStatusOK() {
        String uuid = UUIDFactory.create();

        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .status(Sensor.SensorStatus.ALERT)
                .build();

        sensor.addMeasurement(lowMeasurement);
        sensor.addMeasurement(lowMeasurement);
        sensor.addMeasurement(lowMeasurement);

        Assert.assertEquals(Sensor.SensorStatus.OK, sensor.getStatus());
    }

    @Test
    public void When_AddTwoLowMeasurementAfterALERTStatus_ThenStatusOK() {
        String uuid = UUIDFactory.create();

        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .status(Sensor.SensorStatus.ALERT)
                .build();

        sensor.addMeasurement(lowMeasurement);
        sensor.addMeasurement(lowMeasurement);

        Assert.assertEquals(Sensor.SensorStatus.ALERT, sensor.getStatus());
    }
}
