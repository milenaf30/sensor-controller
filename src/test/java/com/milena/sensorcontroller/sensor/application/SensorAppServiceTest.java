package com.milena.sensorcontroller.sensor.application;

import com.milena.sensorcontroller.sensor.application.impl.SensorAppServiceImpl;
import com.milena.sensorcontroller.sensor.service.SensorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SensorAppService.class, SensorAppServiceImpl.class})
public class SensorAppServiceTest {

    @MockBean
    private SensorService sensorService;

    @Autowired
    private SensorAppService sensorAppService;

    @Test
    public void When_InsertDependencies_ThenCorrect() {
        Assert.assertNotNull(sensorService);
        Assert.assertNotNull(sensorAppService);
    }
}
