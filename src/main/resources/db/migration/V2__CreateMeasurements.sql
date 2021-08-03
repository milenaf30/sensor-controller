CREATE TABLE `measurements` (
	`id` int unsigned NOT NULL AUTO_INCREMENT,
	`sensor_id` int unsigned NOT NULL,
	`co2_level` int unsigned NOT NULL,
	`time` date NOT NULL,
	PRIMARY KEY (`id`),
	KEY `sensor_id` (`sensor_id`),
	CONSTRAINT `fk_measurements_sensor_id` FOREIGN KEY (`sensor_id`) REFERENCES `sensors` (`id`)) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_unicode_ci COMMENT = 'Sensor\'s measurements';