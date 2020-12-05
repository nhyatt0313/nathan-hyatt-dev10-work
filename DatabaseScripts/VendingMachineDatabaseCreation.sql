DROP DATABASE IF EXISTS VendingMachine;
CREATE DATABASE VendingMachine;

USE VendingMachine;

CREATE TABLE Snacks(
	snackId 			VARCHAR(3) 		PRIMARY KEY NOT NULL,
    snackType 			VARCHAR(20) 				NOT NULL,
    snackPrice 			DECIMAL(6,2) 				NOT NULL,
    snackNumInStock 	INT							NOT NULL);
    
INSERT INTO Snacks(snackId, snackType, snackPrice, snackNumInStock) VALUES
	('a1', 'Taco', 					2.50, 	20),
    ('a2', 'Hamburger',				6.00, 	20),
    ('a3', 'Salad', 				3.75, 	20),
    ('b1', 'Ice Cream', 			2.40, 	20),
    ('b2', 'Fudge', 				7.20, 	20),
    ('b3', 'Cake', 					16.00, 	20),
    ('c1', 'Tomato', 				1.10, 	20),
    ('c2', 'Potato', 				0.50, 	20),
    ('c3', 'Chocolate Fountain', 	20.00, 	20);