package com.milena.sensorcontroller.measurement.domain;

import com.milena.sensorcontroller.common.domain.BaseEntity;
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

    @Column
    private Integer sensorId;

    @Column
    private Date time;

    @Column(name = "co2_level")
    private Integer carbonDioxideLevel;

    @Override
    protected Integer getBusinessKey() {
        return id;
    }
}
