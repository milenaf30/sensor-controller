package com.milena.sensorcontroller.metrics.domain;

import com.milena.sensorcontroller.common.domain.BaseEntity;
import com.milena.sensorcontroller.measurement.domain.Measurement;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
    @Builder.Default
    private Integer sum = 0;

    @Builder.Default
    @Column(name = "total_records")
    private Integer totalRecords = 0;

    @Column
    @Builder.Default
    private Integer max = 0;

    @Temporal(TemporalType.DATE)
    @Column
    private Date date;

    public Metric(MetricBuilder metricBuilder) {
        id = metricBuilder.id;
        max = metricBuilder.max;
        sum = metricBuilder.sum;
        totalRecords = metricBuilder.totalRecords;
        sensorId = metricBuilder.sensorId;
        date = metricBuilder.date;
    }

    @Override
    protected Integer getBusinessKey() {
        return id;
    }

    public static MetricBuilder builder() {
        return new MetricBuilder();
    }

    public void addMeasurement(Measurement measurement) {
        Integer carbonDioxideLevel = measurement.getCarbonDioxideLevel();
        updateMax(carbonDioxideLevel);
        updateSum(carbonDioxideLevel);
        increaseTotalRecords();
    }

    public void updateMax(Integer carbonDioxideLevel) {
        if (carbonDioxideLevel > max) {
            max = carbonDioxideLevel;
        }
    }

    public void updateSum(Integer carbonDioxideLevel) {
        sum += carbonDioxideLevel;
    }

    public void increaseTotalRecords() {
        totalRecords++;
    }

    public static class MetricBuilder {

        protected Integer max = 0;
        protected Integer sum = 0;
        protected Integer totalRecords = 0;
        protected Integer sensorId;
        protected Date date;
        protected Integer id;

        public MetricBuilder measurement(Measurement measurement) {
            this.max = measurement.getCarbonDioxideLevel();
            this.sum = measurement.getCarbonDioxideLevel();
            this.date = measurement.getTime();
            this.sensorId = measurement.getSensorIdFromSensor();
            this.totalRecords++;
            return this;
        }

        public MetricBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public Metric build() {
            return new Metric(this);
        }
    }
}
