CREATE TABLE `users` (
	`user_id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` CHAR(50) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`email` CHAR(50) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`password` int(50) NULL DEFAULT NULL,
	PRIMARY KEY (`user_id`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;
