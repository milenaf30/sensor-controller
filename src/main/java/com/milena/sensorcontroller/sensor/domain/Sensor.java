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

        if (status == SensorStatus.OK && measurement.isHigh()) {
            status = SensorStatus.WARM;
            measurements.add(measurement);
            return;
        }

        if (measurements.size() < 2) {
            //state shouldn't change
            measurements.add(measurement);
            return;
        }

        Measurement measurement1 = measurements.get(measurements.size() - 1);
        Measurement measurement2 = measurements.get(measurements.size() - 2);

        if (measurement.isHigh() &&
                measurement1.isHigh() &&
                measurement2.isHigh()
        ) {
            status = SensorStatus.ALERT;
        } else if (measurement.isLow() &&
                measurement1.isLow() &&
                measurement2.isLow()
        ) {
            status = SensorStatus.OK;
//            measurements.add(measurement);
        }
        measurements.add(measurement);
    }

    public enum SensorStatus {
        OK,
        WARM,
        ALERT
    }
}
