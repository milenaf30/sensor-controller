package com.milena.sensorcontroller.metrics.domain;

import com.milena.sensorcontroller.common.domain.BaseEntity;
import com.milena.sensorcontroller.measurement.domain.Measurement;
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

    @Builder.Default
    @Column(name = "total_records")
    private Integer totalRecords = 1;

    @Column
    private Integer max;

    @Temporal(TemporalType.DATE)
    @Column
    private Date date;

    @Override
    protected Integer getBusinessKey() {
        return id;
    }

    public void addMeasurement(Measurement measurement) {
        Integer carbonDioxideLevel = measurement.getCarbonDioxideLevel();
        setMax(carbonDioxideLevel);
        setSum(carbonDioxideLevel);
        setTotalRecords(carbonDioxideLevel);
    }

    public void setMax(Integer carbonDioxideLevel) {
        if (carbonDioxideLevel > max) {
            max = carbonDioxideLevel;
        }
    }

    public void setSum(Integer carbonDioxideLevel) {
        sum += sum;
    }

    public void setTotalRecords(Integer carbonDioxideLevel) {
        totalRecords += 1;
    }
}
