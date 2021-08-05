package com.milena.sensorcontroller.metrics.domain;

import com.milena.sensorcontroller.common.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "metrics_per_day", uniqueConstraints = {
        @UniqueConstraint(name = "uk_metrics_sensor_date", columnNames = {"sensor_id", "date"})})
public class Metric extends BaseEntity<Integer> {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sensor_id")
    private Integer sensorId;

    @Column
    private Integer sum;

    @Column(name = "total_records")
    private Integer totalRecords;

    @Column
    private Integer max;

    @Column
    private Date date;

    @Override
    protected Integer getBusinessKey() {
        return id;
    }
}
