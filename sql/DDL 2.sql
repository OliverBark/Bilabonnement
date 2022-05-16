CREATE SCHEMA `Bilabonnement`;

USE `Bilabonnement`;

CREATE TABLE `Customers` (
                             `first_name` varchar(45) NOT NULL,
                             `last_name` varchar(45) NOT NULL,
                             `address` varchar(45) NOT NULL,
                             `mobile` varchar(45) NOT NULL,
                             `cpr_nr` varchar(45) NOT NULL,
                             `reg_nr` varchar(45) NOT NULL,
                             `account_nr` varchar(45) NOT NULL,
                             PRIMARY KEY (`cpr_nr`));

CREATE TABLE `Payments` (
                            `payment_id` varchar(45) NOT NULL,
                            `price` double NOT NULL,
                            `date` varchar(45) NOT NULL,
                            PRIMARY KEY (`payment_id`));

CREATE TABLE `Subscriptions` (
                                 `subscription_id` varchar(45) NOT NULL,
                                 `holder` varchar(45) NOT NULL,
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

CREATE TABLE `SalesRecords` (
                         `payment_id` varchar(45) NOT NULL,
                         `amount` double NOT NULL,
                         `type` varchar(45),
                         `date` varchar(45),
                         PRIMARY KEY (`payment_id`));

CREATE TABLE `ActiveSubscriptions` (
                         `active_subscription_id` varchar(45) NOT NULL,
                         `subscription_id` varchar(45) NOT NULL,
                         `price_pr_km` double NOT NULL,
                         `damage_prices` varchar(45) NOT NULL,
                         `start_date` varchar(45) NOT NULL,
                         `end_date` varchar(45) NOT NULL,
                         `monthly_fee` double NOT NULL,
                         PRIMARY KEY (`active_subscription_id`));