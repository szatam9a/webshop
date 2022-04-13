CREATE TABLE `users` (
	`user_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`name` CHAR(50) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`email` CHAR(50) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`password` int(50) NULL DEFAULT NULL,
	PRIMARY KEY (`user_id`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;

CREATE TABLE product (
    product_id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name CHAR(50),
    price INT(12),
);
