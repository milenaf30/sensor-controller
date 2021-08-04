package com.milena.sensorcontroller.measurement.domain;

import com.milena.sensorcontroller.common.domain.BaseEntity;
import com.milena.sensorcontroller.sensor.domain.Sensor;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "measurements")
@EqualsAndHashCode(callSuper = true)
public class Measurement extends BaseEntity<Integer> {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    @Column
    private Date time;

    @Column(name = "co2_level")
    private Integer carbonDioxideLevel;

    @Override
    protected Integer getBusinessKey() {
        return id;
    }

    @Transient
    public boolean isHigh() {
        return carbonDioxideLevel > 2000;
    }
}
