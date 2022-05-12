CREATE SCHEMA `Bilabonnement`;

USE `Bilabonnement`;

CREATE TABLE `Customers` (
                             `first_name` varchar(45) NOT NULL,
                             `last_name` varchar(45) NOT NULL,
                             `adress` varchar(45) NOT NULL,
                             `mobile` varchar(45) NOT NULL,
                             `cpr_nr` varchar(45) NOT NULL,
                             `reg_nr` varchar(45) NOT NULL,
                             `account_nr` varchar(45) NOT NULL,
                             PRIMARY KEY (`cpr_nr`));

CREATE TABLE `payments` (
                            `payment_id` varchar(45) NOT NULL,
                            `reg_nr` varchar(45) NOT NULL,
                            `account_nr` varchar(45) NOT NULL,
                            `price` double NOT NULL,
                            PRIMARY KEY (`payment_id`));

CREATE TABLE `Subscriptions` (
                                 `subscription_id` varchar(45) NOT NULL,
                                 `model` varchar(45) NOT NULL,
                                 `color` varchar(45) NOT NULL,
                                 `afleveringsforsikring` tinyint(1) DEFAULT NULL,
                                 `selvrisiko` tinyint(1) DEFAULT NULL,
                                 `location` varchar(45) NOT NULL,
                                 PRIMARY KEY (`subscription_id`));

CREATE TABLE `Users` (
                         `username` varchar(45) NOT NULL,
                         `password` varchar(45) NOT NULL,
                         PRIMARY KEY (`username`),
                         UNIQUE KEY `username_UNIQUE` (`username`));
