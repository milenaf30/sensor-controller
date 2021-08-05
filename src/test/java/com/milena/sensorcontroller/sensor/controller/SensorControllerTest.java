package com.milena.sensorcontroller.sensor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.milena.sensorcontroller.common.uuid.UUIDFactory;
import com.milena.sensorcontroller.measurement.dto.MeasurementDto;
import com.milena.sensorcontroller.sensor.application.SensorAppService;
import com.milena.sensorcontroller.sensor.domain.Sensor;
import com.milena.sensorcontroller.sensor.dto.SensorDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    public void When_GetSensorByUUID_ThenCallToAppService() throws Exception {
        String uuid = UUIDFactory.create();
        when(sensorAppService.getByUUID(UUIDFactory.create())).thenReturn(SensorDto.builder().status(Sensor.SensorStatus.OK).build());
        mockMvc.perform(get("/api/v1/sensors/{uuid}", uuid))
                .andExpect(status().isOk());

        ArgumentCaptor<String> uuidCaptor = ArgumentCaptor.forClass(String.class);
        verify(sensorAppService, times(1))
                .getByUUID(uuidCaptor.capture());
        Assert.assertEquals(uuidCaptor.getValue(), uuid);
    }

    @Test
    public void When_GetSensorByUUID_ThenReturnSensorDto() throws Exception {
        String uuid = UUIDFactory.create();
        SensorDto sensorDto = SensorDto.builder().status(Sensor.SensorStatus.OK).build();
        when(sensorAppService.getByUUID(uuid)).thenReturn(sensorDto);
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/sensors/{uuid}", uuid))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(sensorDto));
    }

    @Test
    public void When_GetSensorByInexistentUUID_ThenNotFound() throws Exception {
        String uuid = UUIDFactory.create();
        when(sensorAppService.getByUUID(uuid)).thenThrow(new EntityNotFoundException());
        mockMvc.perform(get("/api/v1/sensors/{uuid}", uuid))
                .andExpect(status().isNotFound());
    }

    @Test
    public void When_PostSensorMeasurement_ThenOk() throws Exception {
        mockMvc.perform(post("/api/v1/sensors/uuid/measurements")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        MeasurementDto.builder()
                                .carbonDioxideLevel(100)
                                .time(new Date())
                                .build()
                )))
                .andExpect(status().isOk());
    }

    @Test
    public void When_PostSensorMeasurement_ThenCallToAppService() throws Exception {
        String uuid = UUIDFactory.create();
        MeasurementDto measurementDto = MeasurementDto.builder()
                .carbonDioxideLevel(100)
                .time(new Date())
                .uuid(uuid)
                .build();
        doNothing().when(sensorAppService).saveMeasurement(measurementDto);
        mockMvc.perform(post("/api/v1/sensors/{uuid}/measurements", uuid)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        MeasurementDto.builder()
                                .carbonDioxideLevel(100)
                                .time(new Date())
                                .build()
                )))
                .andExpect(status().isOk());

        ArgumentCaptor<MeasurementDto> measurementDtoCaptor = ArgumentCaptor.forClass(MeasurementDto.class);
        verify(sensorAppService, times(1))
                .saveMeasurement(measurementDtoCaptor.capture());
        Assert.assertEquals(measurementDtoCaptor.getValue().getUuid(), uuid);
    }
}