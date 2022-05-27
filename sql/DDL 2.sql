DROP SCHEMA Bilabonnement;

CREATE SCHEMA `Bilabonnement`;

USE `Bilabonnement`;

CREATE TABLE `Customers` (
                             `first_name` varchar(45) NOT NULL,
                             `last_name` varchar(45) NOT NULL,
                             `address` varchar(45) NOT NULL,
                             `mobile` varchar(20) NOT NULL,
                             `cpr_nr` varchar(15) NOT NULL,
                             `reg_nr` INT NOT NULL,
                             `account_nr` INT NOT NULL,
                             PRIMARY KEY (`cpr_nr`));

CREATE TABLE `Rentals` (
                           `rental_id` INT NOT NULL AUTO_INCREMENT,
                           `cpr_nr` varchar(15) NOT NULL,
                           `model` varchar(45) NOT NULL,
                           `color` varchar(45) NOT NULL,
                           `afleveringsforsikring` BOOLEAN DEFAULT NULL,
                           `selvrisiko` BOOLEAN DEFAULT NULL,
                           `location` varchar(45) NOT NULL,
                           `price_pr_km` DOUBLE NOT NULL,
                           `start_date` DATETIME NOT NULL,
                           `end_date` DATETIME NOT NULL,
                           `monthly_fee` DOUBLE NOT NULL,
                           `active` BOOLEAN NOT NULL,
                           PRIMARY KEY (`rental_id`),
                           FOREIGN KEY(cpr_nr) REFERENCES Customers(`cpr_nr`));

CREATE TABLE `Pending_rentals`(
                                  `pending_rental_id` INT NOT NULL AUTO_INCREMENT ,
                                  `cpr_nr` varchar(15) NOT NULL,
                                  `model` varchar(45) NOT NULL,
                                  `color` varchar(45) NOT NULL,
                                  `afleveringsforsikring` BOOLEAN DEFAULT NULL,
                                  `selvrisiko` BOOLEAN DEFAULT NULL,
                                  `location` varchar(45) NOT NULL,
                                  `monthly_fee` DOUBLE NOT NULL,
                                  PRIMARY KEY (`pending_rental_id`),
                                  FOREIGN KEY (cpr_nr) REFERENCES Customers(`cpr_nr`));

CREATE TABLE `Damage_reports` (
                                  `report_id` INT NOT NULL AUTO_INCREMENT,
                                  `rental_id` INT NOT NULL,
                                  `description` varchar(1000),
                                  PRIMARY KEY (`report_id`),
                                  FOREIGN KEY (`rental_id`) REFERENCES Rentals(`rental_id`));

CREATE TABLE `Damages`(
                          `damage_id` INT NOT NULL AUTO_INCREMENT,
                          `report_id` INT NOT NULL,
                          `damage` VARCHAR(100),
                          `price` DOUBLE,
                          PRIMARY KEY (damage_id),
                          FOREIGN KEY (`report_id`) REFERENCES Damage_reports(`report_id`));

CREATE TABLE `Sale_records` (
                         `payment_id` INT NOT NULL AUTO_INCREMENT,
                         `amount` double NOT NULL,
                         `type` varchar(45),
                         `date` date,
                         `rental_id` INT NOT NULL,
                         PRIMARY KEY (`payment_id`),
                         FOREIGN KEY (`rental_id`) REFERENCES Rentals(`rental_id`));

CREATE TABLE `Payments` (
                            `payment_id` INT NOT NULL AUTO_INCREMENT,
                            `amount` double NOT NULL,
                            `date` DATETIME NOT NULL,
                            `type` varchar(45) NOT NULL,
                            `rental_id` INT NOT NULL,
                            PRIMARY KEY (`payment_id`),
                            FOREIGN KEY (`rental_id`) REFERENCES Rentals(`rental_id`));



CREATE TABLE `Users` (
                         `username` varchar(45) NOT NULL,
                         `password` varchar(45) NOT NULL,
                         PRIMARY KEY (`username`),
                         UNIQUE KEY `username_UNIQUE` (`username`));