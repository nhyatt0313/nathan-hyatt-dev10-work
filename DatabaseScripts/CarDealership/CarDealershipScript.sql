DROP DATABASE IF EXISTS CarDealership;

CREATE DATABASE IF NOT EXISTS CarDealership;
USE CarDealership;

CREATE TABLE IF NOT EXISTS `User` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userId`));

CREATE TABLE IF NOT EXISTS `Make` (
  `makeId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `dateAdded` DATE NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`makeId`),
  INDEX `fk_Make_User1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_Make_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `User` (`userId`));

CREATE TABLE IF NOT EXISTS `Model` (
  `modelId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `dateAdded` DATE NOT NULL,
  `userId` INT NOT NULL,
  `makeId` INT NOT NULL,
  PRIMARY KEY (`modelId`),
  INDEX `fk_Model_User1_idx` (`userId` ASC) VISIBLE,
  INDEX `fk_Model_Make1_idx` (`makeId` ASC) VISIBLE,
  CONSTRAINT `fk_Model_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `User` (`userId`),
  CONSTRAINT `fk_Model_Make1`
    FOREIGN KEY (`makeId`)
    REFERENCES `Make` (`makeId`));

CREATE TABLE IF NOT EXISTS `Vehicle` (
  `vehicleId` INT NOT NULL AUTO_INCREMENT,
  `year` INT NOT NULL,
  `mileage` INT NOT NULL,
  `isNew` TINYINT NOT NULL,
  `salePrice` DECIMAL(12,2) NOT NULL,
  `style` VARCHAR(45) NOT NULL,
  `interior` VARCHAR(45) NOT NULL,
  `trans` VARCHAR(45) NOT NULL,
  `msrp` DECIMAL(12,2) NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `vin` CHAR(17) NOT NULL,
  `description` MEDIUMTEXT NULL,
  `featured` TINYINT NULL DEFAULT 0,
  `sold` TINYINT NULL DEFAULT 0,
  `fileImg` VARCHAR(45) NULL,
  `makeId` INT NOT NULL,
  `modelId` INT NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`vehicleId`),
  INDEX `fk_Vehicle_Make_idx` (`makeId` ASC) VISIBLE,
  INDEX `fk_Vehicle_Model1_idx` (`modelId` ASC) VISIBLE,
  INDEX `fk_Vehicle_User1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_Vehicle_Make`
    FOREIGN KEY (`makeId`)
    REFERENCES `Make` (`makeId`),
  CONSTRAINT `fk_Vehicle_Model1`
    FOREIGN KEY (`modelId`)
    REFERENCES `Model` (`modelId`),
  CONSTRAINT `fk_Vehicle_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `User` (`userId`));

CREATE TABLE IF NOT EXISTS `Contact` (
  `contactId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `phone` CHAR(10) NULL,
  `message` MEDIUMTEXT NOT NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`contactId`)
);

CREATE TABLE IF NOT EXISTS `Purchase` (
  `purchaseId` INT NOT NULL AUTO_INCREMENT,
  `purchaseCost` DECIMAL(12,2) NOT NULL,
  `purchaseType` VARCHAR(45) NOT NULL,
  `purchaseDate` DATE NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `street2` VARCHAR(45) NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `zip` CHAR(5) NOT NULL,
  `phone` CHAR(10) NULL,
  `email` VARCHAR(45) NULL,
  `vehicleId` INT NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`purchaseId`),
  INDEX `fk_Purchases_Vehicle1_idx` (`vehicleId` ASC) VISIBLE,
  INDEX `fk_Purchases_User1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_Purchases_Vehicle1`
    FOREIGN KEY (`vehicleId`)
    REFERENCES `Vehicle` (`vehicleId`),
  CONSTRAINT `fk_Purchases_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `User` (`userId`));

CREATE TABLE IF NOT EXISTS `Specials` (
  `specialId` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` MEDIUMTEXT NOT NULL,
  PRIMARY KEY (`specialId`)
 );