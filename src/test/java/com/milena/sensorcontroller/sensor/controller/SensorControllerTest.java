package com.milena.sensorcontroller.sensor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.milena.sensorcontroller.common.uuid.UUIDFactory;
import com.milena.sensorcontroller.sensor.application.SensorAppService;
import com.milena.sensorcontroller.sensor.domain.Sensor;
import com.milena.sensorcontroller.sensor.dto.SensorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SensorController.class)
public class SensorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SensorAppService sensorAppService;

    @Test
    public void When_GetSensorByUUID_ThenOk() throws Exception {
        when(sensorAppService.getByUUID(UUIDFactory.create())).thenReturn(SensorDto.builder().status(Sensor.SensorStatus.OK).build());
        mockMvc.perform(get("/api/v1/sensors/uuid"))
                .andExpect(status().isOk());
    }
}