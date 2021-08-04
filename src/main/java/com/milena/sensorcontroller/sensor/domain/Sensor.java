package com.milena.sensorcontroller.sensor.domain;

import com.milena.sensorcontroller.common.domain.BaseEntity;
import com.milena.sensorcontroller.measurement.domain.Measurement;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sensors")
@EqualsAndHashCode(callSuper = true)
public class Sensor extends BaseEntity<Integer> {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String uuid;

    @Override
    protected Integer getBusinessKey() {
        return id;
    }

    public SensorStatus getStatus() {
        return SensorStatus.OK;
    }

    public void addMeasurement(Measurement measurement) {

    }

    public enum SensorStatus {
        OK,
        WARM,
        ALERT
    }
}
