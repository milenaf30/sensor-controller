CREATE TABLE `metrics_per_day`
(
    `id`            int unsigned NOT NULL AUTO_INCREMENT,
    `sensor_id`     int unsigned NOT NULL,
    `sum`           int unsigned NOT NULL,
    `total_records` int unsigned NOT NULL,
    `date`          date         NOT NULL,
    `max`           int unsigned NOT NULL,
    PRIMARY KEY (`id`),
    KEY `sensor_id` (`sensor_id`),
    UNIQUE KEY `uk_metrics_sensor_date` (`sensor_id`, `date`),
    CONSTRAINT `fk_metrics_sensor_id` FOREIGN KEY (`sensor_id`) REFERENCES `sensors` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci COMMENT = 'Resume of measurements per day of sensors';