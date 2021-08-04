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
public class Measurement extends BaseEntity<Long> {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long sensorId;

    @Column
    private Date time;

    @Column(name = "co2_level")
    private Integer carbonDioxideLevel;

    @Override
    protected Long getBusinessKey() {
        return id;
    }
}
