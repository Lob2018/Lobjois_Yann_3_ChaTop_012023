DROP TABLE IF EXISTS `MESSAGES`;
DROP TABLE IF EXISTS `RENTALS`;
DROP TABLE IF EXISTS `USERS`;

CREATE TABLE `USERS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255),
  `name` varchar(255),
  `password` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `RENTALS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `surface` numeric(19,2),
  `price` numeric(19,2),
  `picture` varchar(255),
  `description` varchar(2000),
  `owner_id` integer NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp,
  FOREIGN KEY (`owner_id`) REFERENCES `USERS`(`id`) ON DELETE CASCADE
);

CREATE TABLE `MESSAGES` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `rental_id` integer,
  `user_id` integer,
  `message` varchar(2000),
  `created_at` timestamp,
  `updated_at` timestamp,
  FOREIGN KEY (`user_id`) REFERENCES `USERS`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`rental_id`) REFERENCES `RENTALS`(`id`) ON DELETE CASCADE
);

CREATE UNIQUE INDEX `USERS_index` ON `USERS` (`email`);