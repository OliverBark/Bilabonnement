USE `Bilabonnement`;

INSERT INTO Customers (`first_name`,`last_name`, `address`, `mobile`, `cpr_nr`, `reg_nr`, `account_nr`)
VALUES('Jack', 'Jack', 'test1', 'test1', 'test1', 1234, 12345678);

INSERT INTO Customers (`first_name`,`last_name`, `address`, `mobile`, `cpr_nr`, `reg_nr`, `account_nr`)
VALUES('James', 'James', 'test2', 'test2', 'test2', 5678, 56789123);

INSERT INTO Customers (`first_name`,`last_name`, `address`, `mobile`, `cpr_nr`, `reg_nr`, `account_nr`)
VALUES('Adam', 'Adam', 'test3', 'test3', 'test3', 9123, 45678912);

INSERT INTO pending_rentals(`customer_cpr`,`model`,`color`,`afleveringsforsikring`,`selvrisiko`,`location`,`monthly_fee`)
VALUES('test1', 'test1', 'test1', 1, 1, 'test1', 1000);

INSERT INTO pending_rentals(`customer_cpr`,`model`,`color`,`afleveringsforsikring`,`selvrisiko`,`location`,`monthly_fee`)
VALUES('test2', 'test2', 'test2', 0, 0, 'test2', 100);

INSERT INTO pending_rentals(`customer_cpr`,`model`,`color`,`afleveringsforsikring`,`selvrisiko`,`location`,`monthly_fee`)
VALUES('test3', 'test3', 'test3', 1, 0, 'test1', 500);