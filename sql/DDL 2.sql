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

CREATE TABLE `Payments` (
                            `payment_id` INT NOT NULL AUTO_INCREMENT,
                            `amount` double NOT NULL,
                            `date` DATETIME NOT NULL,
                            `rental_id` INT NOT NULL,
                            PRIMARY KEY (`payment_id`));

CREATE TABLE `Rentals` (
                                 `rental_id` INT NOT NULL AUTO_INCREMENT,
                                 `customer_cpr` varchar(15) NOT NULL,
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
                                 PRIMARY KEY (`rental_id`));

CREATE TABLE `Users` (
                         `username` varchar(45) NOT NULL,
                         `password` varchar(45) NOT NULL,
                         PRIMARY KEY (`username`),
                         UNIQUE KEY `username_UNIQUE` (`username`));

CREATE TABLE `Sale_records` (
                         `payment_id` INT NOT NULL AUTO_INCREMENT,
                         `amount` double NOT NULL,
                         `type` varchar(45),
                         `date` date,
                         PRIMARY KEY (`payment_id`));

CREATE TABLE `Pending_rentals`(
                        `pending_rental_id` INT NOT NULL AUTO_INCREMENT ,
                        `customer_cpr` varchar(15) NOT NULL,
                        `model` varchar(45) NOT NULL,
                        `color` varchar(45) NOT NULL,
                        `afleveringsforsikring` BOOLEAN DEFAULT NULL,
                        `selvrisiko` BOOLEAN DEFAULT NULL,
                        `location` varchar(45) NOT NULL,
                        `monthly_fee` DOUBLE NOT NULL,
                        PRIMARY KEY (`pending_rental_id`));

CREATE TABLE `Damage_reports` (
                        `report_id` INT NOT NULL AUTO_INCREMENT,
                        `rental_id` INT NOT NULL,
                        `description` varchar(1000),
                        PRIMARY KEY (`report_id`));

CREATE TABLE `Damages`(
                        `damage_id` INT NOT NULL AUTO_INCREMENT,
                        `report_id` INT NOT NULL,
                        `damage` VARCHAR(100),
                        `price` DOUBLE,
                        PRIMARY KEY (damage_id));

INSERT INTO Users (`username`,`password`)

VALUES ('Oliver',
        'admin');
INSERT INTO Users (`username`,`password`)
VALUES ('Shaun',
        'admin');

INSERT INTO Users (`username`,`password`)
VALUES ('Battal',
        'admin');

INSERT INTO Rentals (customer_cpr, model, color, afleveringsforsikring, selvrisiko, location, price_pr_km, start_date, end_date, monthly_fee, active)
VALUES('testCPR','test','test',1,1,'test',100,'2010-01-01','2015-01-01', 150, 1);