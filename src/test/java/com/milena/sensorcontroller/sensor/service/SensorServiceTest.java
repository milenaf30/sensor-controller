package com.milena.sensorcontroller.sensor.service;


import com.milena.sensorcontroller.SensorControllerApplication;
import com.milena.sensorcontroller.common.uuid.UUIDFactory;
import com.milena.sensorcontroller.sensor.domain.Sensor;
import com.milena.sensorcontroller.sensor.repository.SensorRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SensorControllerApplication.class)
public class SensorServiceTest {
    @MockBean
    private SensorRepository sensorRepository;

    @Autowired
    private SensorService sensorService;

    @Test
    public void When_InsertDependencies_ThenCorrect() {
        Assert.assertNotNull(sensorRepository);
        Assert.assertNotNull(sensorService);
    }

    @Test
    public void When_findByUUID_ThenValidResponse() {
        String uuid = UUIDFactory.create();
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();
        when(sensorRepository.findByUuid(uuid)).thenReturn(sensor);
        Sensor sensorRetrieved = sensorService.findByUUID(uuid);
        Assert.assertEquals(sensor.getUuid(), sensorRetrieved.getUuid());
    }

    @Test
    public void When_findByUUID_ThenValidParameters() {
        String uuid = UUIDFactory.create();
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();
        when(sensorRepository.findByUuid(uuid)).thenReturn(sensor);

        sensorService.findByUUID(uuid);

        ArgumentCaptor<String> uuidCaptor = ArgumentCaptor.forClass(String.class);
        verify(sensorRepository, times(1))
                .findByUuid(uuidCaptor.capture());
        Assert.assertEquals(uuidCaptor.getValue(), uuid);
    }

    @Test(expected = EntityNotFoundException.class)
    public void When_findByUUID_ThenError() {
        String uuid = UUIDFactory.create();
        String anotherUuid = UUIDFactory.create();
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .build();
        when(sensorRepository.findByUuid(uuid)).thenReturn(sensor);
        sensorService.findByUUID(anotherUuid);
    }
}
