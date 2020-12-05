DROP DATABASE IF EXISTS HotelReservations;
CREATE DATABASE HotelReservations;

USE HotelReservations;

CREATE TABLE Guest (
	GuestId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(20) NOT NULL,
    LastName VARCHAR(20) NOT NULL,
    Phone VARCHAR(20) NOT NULL,
    Email VARCHAR(20) NOT NULL
);

CREATE TABLE RoomType (
	RoomTypeId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	RoomType VARCHAR(20) NOT NULL,
    RoomTypeBaseRate DECIMAL(8,2)
);

CREATE TABLE Room (
	RoomNumber INT NOT NULL PRIMARY KEY,
    RoomFloor INT NOT NULL,
    RoomTypeId INT NOT NULL,
    FOREIGN KEY fk_Room_RoomType (RoomTypeId)
		REFERENCES RoomType(RoomTypeId)
);

CREATE TABLE Amenity (
	AmenityId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    AmenityType VARCHAR(20) NOT NULL,
    AmenityBaseRate DECIMAL(8,2) NOT NULL
);

CREATE TABLE AddOn (
	AddOnId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    AddOnType VARCHAR(20) NOT NULL,
    AddOnPrice DECIMAL(8,2) NOT NULL
);

CREATE TABLE Reservation (
	ReservationId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    CheckIn DATE NOT NULL,
    CheckOut DATE NOT NULL,
    PromoCode VARCHAR(50),
    GuestId INT NOT NULL,
    FOREIGN KEY fk_Reservation_Guest (GuestId)
		REFERENCES Guest(GuestId)
);

CREATE TABLE ReservationAddOn (
	ReservationAddOnId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    AddOnId INT NOT NULL,
    ReservationId INT NOT NULL,
    FOREIGN KEY fk_ReservationAddOn_AddOn (AddOnId)
		REFERENCES AddOn(AddOnId),
	FOREIGN KEY fk_ReservationAddOn_Reservation (ReservationId)
		REFERENCES Reservation(ReservationId)
);

CREATE TABLE RoomReservation (
	RoomReservationId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ReservationId INT NOT NULL,
    RoomNumber INT NOT NULL,
    FOREIGN KEY fk_RoomReservation_Reservation (ReservationId)
		REFERENCES Reservation (ReservationId),
    FOREIGN KEY fk_RoomReservation_Room (RoomNumber)
		REFERENCES Room(RoomNumber)
);

CREATE TABLE GuestRoomReservation (
	GuestId INT NOT NULL,
    RoomReservationId INT NOT NULL,
    PRIMARY KEY (GuestId, RoomReservationId),
    FOREIGN KEY fk_GuestRoomReservation_Guest (GuestId)
		REFERENCES Guest(GuestId),
	FOREIGN KEY fk_GuestRoomReservation_RoomReservation (RoomReservationId)
		REFERENCES RoomReservation(RoomReservationId)
);

CREATE TABLE RoomAmenity (
	RoomAmenityId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    RoomNumber INT NOT NULL,
    AmenityId INT NOT NULL,
    FOREIGN KEY fk_RoomAmenity_Room (RoomNumber)
		REFERENCES Room(RoomNumber),
	FOREIGN KEY fk_RoomAmenity_Amenity (AmenityId)
		REFERENCES Amenity(AmenityId)
);

CREATE TABLE Prorate (
	ProrateId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    CurrentRate DECIMAL(6,2) NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NULL
);

CREATE TABLE Billing (
	BillingId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Tax DECIMAL(11,2),
    Total DECIMAL(12,2),
    ReservationId INT NOT NULL,
    ProrateId INT NULL,
    FOREIGN KEY fk_Billing_Reservation (ReservationId)
		REFERENCES Reservation (ReservationId),
	FOREIGN KEY fk_Billing_Prorate (Prorateid)
		REFERENCES Prorate (ProrateId)
);

CREATE TABLE BillingDetail (
	BillingDetailId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `Description` VARCHAR(30) NOT NULL,
    Cost DECIMAL(6,2),
    BillingId INT NOT NULL,
    FOREIGN KEY fk_BillingDetail_Billing (BillingId)
		REFERENCES Billing(BillingId)
);