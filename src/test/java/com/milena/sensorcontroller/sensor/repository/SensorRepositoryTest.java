package com.milena.sensorcontroller.sensor.repository;

import com.milena.sensorcontroller.common.uuid.UUIDFactory;
import com.milena.sensorcontroller.sensor.domain.Sensor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SensorRepositoryTest {

    @Autowired
    private SensorRepository sensorRepository;


    @Test
    public void When_InsertDependencies_ThenCorrect() {
        Assert.assertNotNull(sensorRepository);
    }

    @Test
    public void When_InsertEntity_ThenCorrect() {
        String uuid = UUIDFactory.create();
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();
        Sensor sensorRetrieved = sensorRepository.save(sensor);
        Assert.assertEquals(sensor.getUuid(), sensorRetrieved.getUuid());
        Assert.assertNotNull(sensor.getId());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void When_InsertSameEntity_ThenFail() {
        String uuid = UUIDFactory.create();
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();
        sensorRepository.save(sensor);
        Sensor sensor2 = Sensor.builder()
                .uuid(uuid)
                .build();
        sensorRepository.save(sensor2);
    }

}
