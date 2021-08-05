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

    @Column
    @Builder.Default
    private SensorStatus status = SensorStatus.OK;

    @Override
    protected Integer getBusinessKey() {
        return id;
    }

    public void addMeasurement(Measurement measurement) {
        measurement.setSensor(this);
        measurements.add(measurement);

        calculateStatus();
    }

    private void calculateStatus() {
        Measurement measurement = measurements.get(measurements.size() - 1);
        if (isOk() && measurement.isHigh()) {
            status = SensorStatus.WARM;
            return;
        }
        if (isAlert() && measurement.isHigh()) return;
        if (isOk() && measurement.isLow()) return;
        if (!haveEnoughData()) return;

        Measurement measurement3 = measurements.get(measurements.size() - 1);
        Measurement measurement2 = measurements.get(measurements.size() - 2);
        Measurement measurement1 = measurements.get(measurements.size() - 3);

        if (measurement3.isHigh() &&
                measurement2.isHigh() &&
                measurement1.isHigh()) {
            status = SensorStatus.ALERT;
            return;
        }

        if (measurement3.isLow() &&
                measurement2.isLow() &&
                measurement1.isLow()) {
            status = SensorStatus.OK;
        }
    }

    private boolean haveEnoughData() {
        return measurements.size() >= 3;
    }

    private boolean isAlert() {
        return status == SensorStatus.ALERT;
    }

    private boolean isOk() {
        return status == SensorStatus.OK;
    }

    public enum SensorStatus {
        OK,
        WARM,
        ALERT
    }
}
