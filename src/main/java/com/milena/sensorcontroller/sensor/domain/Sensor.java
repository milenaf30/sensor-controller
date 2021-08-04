package com.milena.sensorcontroller.sensor.domain;

import com.milena.sensorcontroller.common.domain.BaseEntity;
import com.milena.sensorcontroller.measurement.domain.Measurement;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Builder.Default
    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Measurement> measurements = new ArrayList<>();

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

        //measurement 3

        if (status == SensorStatus.OK && measurement.getCarbonDioxideLevel() > 2000) {
            status = SensorStatus.WARM;
            measurements.add(measurement);
            return;
        }

        if (measurements.size() < 2) {
            //state shouldn't change
            measurements.add(measurement);
            return;
        }

        Measurement measurement1 = measurements.get(1);
        Measurement measurement2 = measurements.get(0);

        if (measurement.getCarbonDioxideLevel() > 2000 &&
                measurement1.getCarbonDioxideLevel() > 2000 &&
                measurement2.getCarbonDioxideLevel() > 2000
        ) {
            status = SensorStatus.ALERT;
            measurements.add(measurement);
        }
    }

    public enum SensorStatus {
        OK,
        WARM,
        ALERT
    }
}
