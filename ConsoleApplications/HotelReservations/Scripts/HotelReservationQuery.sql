

USE HotelReservations;

-- update guest name based on date range
UPDATE Guest
	SET FirstName = 'changedNmae'
WHERE GuestId = 1;


-- show booked rooms on a date (left outer joins)
SELECT 
	Reservation.CheckIn,
    Reservation.CheckOut,
    Room.RoomNumber
FROM reservation
INNER JOIN RoomReservation ON Reservation.ReservationId = RoomReservation.ReservationId
INNER JOIN Room ON RoomReservation.RoomNumber = Room.RoomNumber
ORDER BY Room.RoomNumber;

	
-- delete a guest with a reservation


DELETE FROM BillingDetail
WHERE BillingId = 2;
    
DELETE FROM Billing
WHERE ReservationId = 2;
  
DELETE FROM GuestRoomReservation
WHERE RoomReservationId = 2;
      
DELETE FROM RoomReservation
WHERE ReservationId = 2;

DELETE FROM reservation
WHERE GuestId = 2;

DELETE FROM guest
WHERE GuestId = 2;


