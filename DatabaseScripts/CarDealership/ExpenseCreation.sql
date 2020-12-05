DROP DATABASE IF EXISTS Expenses;

CREATE DATABASE IF NOT EXISTS Expenses;
USE Expenses;

CREATE TABLE IF NOT EXISTS December2018 (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  expenseDate DATE NOT NULL,
  expenseType VARCHAR(100) NOT NULL,
  expenseCost DECIMAL(8,2) NOT NULL
);
  