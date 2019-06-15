DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `roles`;

CREATE TABLE `users` (
  `id` VARCHAR(10) PRIMARY KEY,
  `first_name` VARCHAR(10) NOT NULL,
  `last_name` VARCHAR(40) NOT NULL,
  `email` VARCHAR(40) NOT NULL,
  `password` VARCHAR(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE `roles` (
  `id` VARCHAR(40) PRIMARY KEY,
  `name` VARCHAR(40) NOT NULL
  )ENGINE=InnoDB DEFAULT CHARSET=utf8;

SELECT 'LOADING Users' as 'INFO';
INSERT INTO `users` (`id`,`first_name`,`last_name`,`email`,`password`)   VALUES
("1","fantastic", "user", "user@user.com","password");

SELECT 'LOADING Roles' as 'INFO';
INSERT INTO `roles` (`id`,`name`)   VALUES
("1","ROLE_ADMIN"),
("2","ROLE_USER");
