CREATE TABLE `users` (
	`user_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`name` CHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
	`email` CHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
	`password` BIGINT(20) NOT NULL,
	PRIMARY KEY (`user_id`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;
