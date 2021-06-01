CREATE DATABASE `rubrica`;
USE `rubrica`;

CREATE TABLE `contacts` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `surname` varchar(25) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
);

CREATE TABLE `emails` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `contact_id` bigint UNSIGNED,
  `email` varchar(40),
  
  UNIQUE (`contact_id`, `email`),
  PRIMARY KEY (`id`)
);

CREATE TABLE `phone_numbers` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `contact_id` bigint UNSIGNED,
  `phone` varchar(20),
  
  UNIQUE (`contact_id`, `phone`),
  PRIMARY KEY (`id`)
);


/* AGGIUNGO LE FOREIGN KEY */
ALTER TABLE `emails`
ADD CONSTRAINT `fk_emails_contacts` 
	FOREIGN KEY (`contact_id`) 
	REFERENCES `contacts`(`id`)
	ON DELETE CASCADE;
	
ALTER TABLE `phone_numbers`
ADD CONSTRAINT `fk_phone_numbers_contacts` 
	FOREIGN KEY (`contact_id`) 
	REFERENCES `contacts`(`id`)
	ON DELETE CASCADE;
