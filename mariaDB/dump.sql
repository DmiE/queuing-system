BEGIN;

DROP TABLE IF EXISTS `user_roles`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `queues`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(40) NOT NULL,
  `last_name` varchar(15) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_users_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_roles_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_roles_role_id` (`role_id`),
  CONSTRAINT `fk_user_roles_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_user_roles_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `queues` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quque_name` varchar(20) NOT NULL,
  `user_id` bigint(20) NOT NULL, 
  `finished` boolean,
  `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_queues_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


SELECT 'LOADING Roles' as 'INFO';
INSERT IGNORE INTO roles(id,`name`) VALUES(1,'ROLE_ADMIN');
INSERT IGNORE INTO roles(id,`name`) VALUES(2,'ROLE_USER');

SELECT 'LOADING Users' as 'INFO';
INSERT INTO users (`first_name`,`last_name`,`email`,`password`) VALUES
("admin", "admin", "admin@admin.com","$2a$10$zVRbsmuxar7PibSddr8a8e1IbyzfjSXgn5N1HtqRxdy24kCuhuVdy"),
("admin1", "admin1", "admin@admin.com1","$2a$10$zVRbsmuxar7PibSddr8a8e1IbyzfjSXgn5N1HtqRxdy24kCuhuVdy");
;

SELECT 'LOADING User Roles' as 'INFO';
INSERT INTO user_roles (`user_id`,`role_id`)  VALUES(1,1),
INSERT INTO user_roles (`user_id`,`role_id`)  VALUES(2,1)
;


