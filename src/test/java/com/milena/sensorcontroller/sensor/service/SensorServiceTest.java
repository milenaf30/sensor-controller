package com.milena.sensorcontroller.sensor.service;


import com.milena.sensorcontroller.SensorControllerApplication;
import com.milena.sensorcontroller.sensor.repository.SensorRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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
}
