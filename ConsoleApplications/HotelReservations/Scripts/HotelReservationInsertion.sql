USE HotelReservations;

INSERT INTO Guest (
	FirstName, 
	LastName, 
    Phone, 
    Email) VALUES 
		('Bill', 'Joe', '555-123-4567', 'billjoe@gmail.com'),				-- 1
		('Bobby', 'Jenkins', '444-873-8872', 'bjenkins@hotmail.com'),		-- 2
		('Sarah', 'Smith', '747-023-5567','sjsmith@gmail.com'),				-- 3
		('Jane', 'Fredrickson', '654-543-8237', 'blahblah@outlook.com'),	-- 4
		('Sally', 'Smith', '668-827-7643','smsmith@gmail.com'),				-- 5
        ('Sally', 'Joy', '668-827-7642','sallysmith@gmail.com');			-- 6

INSERT INTO RoomType (
	RoomType, 
	RoomTypeBaseRate) VALUES
		('Single', 100.00),     	-- 1
		('Double', 150.00),     	-- 2
		('Suite', 175.00),      	-- 3
		('SuperSuite', 200.00); 	-- 4
    
INSERT INTO Room (
	RoomNumber, 
    RoomFloor, 
    RoomTypeId) VALUES
		(101, 1, 1),
		(102, 1, 1),
		(103, 1, 1),
		(104, 1, 1),
		(105, 1, 1),
		(106, 1, 2),
		(107, 1, 2),
		(108, 1, 2),
		(109, 1, 2),
		(110, 1, 2),
		(201, 2, 3),
		(202, 2, 3),
		(203, 2, 3),
		(204, 2, 3),
		(205, 2, 3),
		(206, 2, 4),
		(207, 2, 4),
		(208, 2, 4),
		(209, 2, 4),
		(210, 2, 4);
    
INSERT INTO Amenity (
	AmenityType, 
    AmenityBaseRate) VALUES
		('HotTub', 50.00),	-- 1
		('Balcony', 25.00),	-- 2
		('Kitchen', 20.00),	-- 3
		('TV', 5.00),		-- 4
		('Cable', 2.50),	-- 5
		('MiniBar', 15.00);	-- 6
	
INSERT INTO AddOn (
	AddOnType, 
    AddOnPrice) VALUES
		('CandyBar', 5.00),			-- 1
		('Chips', 2.50),			-- 2
		('Pretzels', 2.00),			-- 3
		('Beer', 6.00),				-- 4
		('Wine', 7.00),				-- 5
		('BottledWater', 1.00);		-- 6
    
INSERT INTO Reservation (
	CheckIn, 
	CheckOut, 
    PromoCode, 
    GuestId) VALUES
		('2018-01-01','2018-01-02',NULL,1),				-- 1
        ('2018-03-13','2018-03-16','STPADDYS',2),		-- 2
        ('2018-12-07','2018-12-09',NULL,3),				-- 3
        ('2018-12-23','2018-12-26','CHRISTMAS',4),		-- 4
        ('2018-12-22','2018-12-23','HOLIDAYTRAVEL',5);	-- 5
    
INSERT INTO ReservationAddOn (
	AddOnId, 
    ReservationId) VALUES
		(1, 1),
        (2, 1),
        (5, 4),
        (5, 4);
    
INSERT INTO RoomReservation (
    ReservationId,
    RoomNumber) VALUES
		(1, 101),
        (2, 102),
        (3, 201),
        (4, 210),
        (5, 103);

INSERT INTO GuestRoomReservation (
	GuestId,
    RoomReservationId) VALUES
        (1,1),
        (2,2),
        (3,3),
        (4,4),
        (5,5);

INSERT INTO RoomAmenity (
    RoomNumber,
    AmenityId) VALUES
    -- HotTob (1)
		(210, 1),
        (209, 1),
    -- Balcony (2)
		(210, 2),
        (201, 2),
        (202, 2),
    -- Kitchen (3)
        (101, 3),
        (102, 3),
    -- TV (4)
		(101, 4),
        (102, 4),
        (103, 4),
        (104, 4),
        (106, 4),
        (108, 4),
        (202, 4),
        (208, 4),
        (209, 4),
    -- Cable (5)
        (102, 5),
        (104, 5),
        (106, 5),
        (108, 5),
        (202, 5),
        (208, 5),
    -- MiniBar (6)
        (201, 6),
        (210, 6);

INSERT INTO Prorate (
    CurrentRate,
    StartDate,
    EndDate) VALUES
		(1.25, '2018-12-23', '2018-12-30');

INSERT INTO Billing (
    Tax,
    Total,
    ReservationId,
    ProrateId) VALUES

-- RoomNumber		RoomType			RoomTypeBaseRate		RoomAmenities												RoomReservations		date in			date out		promo 			guestId						AddOns
-- 101 				'Single' 			100.00    				('Kitchen', 20.00),('TV', 5.00)								1						'2018-01-01'	'2018-01-02'	NULL			1							('CandyBar', 5.00),('Chips', 2.50)
-- 102 				'Single'  			100.00    				('TV', 5.00),('Cable', 2.50)								2						'2018-03-13'	'2018-03-16'	'STPADDYS'		2
-- 103 				'Single'  			100.00    				('TV', 5.00)												5						'2018-12-07'	'2018-12-09'	NULL			3
	
-- 201 				'Suite'  			175.00    				('MiniBar', 15.00),('Balcony', 25.00)						3						'2018-12-23'	'2018-12-26'	'CHRISTMAS'		4 this one has prorate		('Wine', 7.00),('Wine', 7.00)
	
-- 210 				'SuperSuite'		200.00 					('HotTub', 50.00),('MiniBar', 15.00),('Balcony', 25.00)		4						'2018-12-22'	'2018-12-23'	'HOLIDAYTRAVEL'	5
		(13.25, 132.50, 1, NULL),
        (32.25, 322.50, 2, NULL),
        (65.00, 650.00, 3, 1),
        (29.00, 290.00, 4, NULL),
        (21.00, 210.00, 5, NULL);
        
INSERT INTO BillingDetail (
    `Description`,
    Cost,
    BillingId) VALUES
		('Snickers', 5.00, 1);
    
    
    
    
    
    