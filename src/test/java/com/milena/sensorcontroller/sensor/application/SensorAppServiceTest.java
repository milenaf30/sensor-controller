package com.milena.sensorcontroller.sensor.application;

import com.milena.sensorcontroller.common.uuid.UUIDFactory;
import com.milena.sensorcontroller.sensor.application.impl.SensorAppServiceImpl;
import com.milena.sensorcontroller.sensor.domain.Sensor;
import com.milena.sensorcontroller.sensor.dto.SensorDto;
import com.milena.sensorcontroller.sensor.service.SensorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

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

    @Test
    public void When_GetByUUID_ThenCorrect() {
        String uuid = UUIDFactory.create();
        Sensor sensor = Sensor.builder()
                .uuid(uuid)
                .id(1)
                .build();
        when(sensorService.findByUUID(uuid)).thenReturn(sensor);
        SensorDto sensorDto = sensorAppService.getByUUID(uuid);
        Assert.assertEquals(Sensor.SensorStatus.OK, sensorDto.getStatus());
    }
}
