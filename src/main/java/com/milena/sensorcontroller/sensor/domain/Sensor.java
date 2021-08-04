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

    @Transient
    @Builder.Default
    private SensorStatus status = SensorStatus.OK;

    @Override
    protected Integer getBusinessKey() {
        return id;
    }

    public SensorStatus getStatus() {
        return status;
    }

    public void addMeasurement(Measurement measurement) {
        if (measurement.getCarbonDioxideLevel() > 2000) {
            status = SensorStatus.WARM;
        }
    }

    public enum SensorStatus {
        OK,
        WARM,
        ALERT
    }
}
