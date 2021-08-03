CREATE TABLE  IF NOT EXISTS `sensors` (
        `id` int unsigned NOT NULL AUTO_INCREMENT,
        `uuid` VARCHAR(255) NOT NULL,
        PRIMARY KEY (`id`),
        UNIQUE KEY `uuid` (`uuid`)) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_unicode_ci COMMENT = 'Sensors';