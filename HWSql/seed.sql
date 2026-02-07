INSERT INTO clients (client_first_name, client_last_name, age, gender, email, phone, drivers_license)
VALUES ('Dmitriy', 'Ponomarev', 22, 'MALE', 'Life272@mail.ru', '+79992723822', '123456789'),
	   ('Danila', 'Bulbakov', 21, 'MALE', 'bulba200@mail.ru', '+73928347139', '987654321'),
	   ('Anna', 'Vishny', 25, 'FEMALE', 'anna932@gmail.com', '+71233211122', '123321098'),
	   ('Artem', 'Makenov', 29, 'MALE', 'artikmali2@mail.ru', '+72315668338', '098123567'),
	   ('Masha', 'Lipova', 20, 'FEMALE', 'masha32@gmail.com', '+71203218120', '123921098'),
	   ('Andrey', 'Shelygin', 30, 'MALE', 'andrey00@mail.ru', '+312315668338', '02123567'),
	   ('Kate', 'Stepanova', 32, 'FEMALE', 'kate2@gmail.com', '+931203218120', '121321098'),
	   ('Margo', 'Lilikova', 19, 'FEMALE', 'margoo@gmail.com', '+831103518120', '199321098'),
	   ('Olga', 'Shulykova', 34, 'FEMALE', 'olga78@gmail.com', '+231203218120', '12132142098'),
	   ('Nikita', 'Semenenko', 30, 'MALE', 'nikitas@mail.ru', '+412f15668338', '9921235567');
		

INSERT INTO car_models (brand, model, year_start, seats, price_per_hour)
VALUES  ('BMW', 'M2', 2022, 2, 40000.53),
		('Audi', 'RS7', 2024, 5, 70000.13),
		('Mercedes', '312', 2023, 4, 55000.83),
		('Toyota', 'Premio', 2004, 5, 20000.41),
		('Nissan', 'Almera', 2012, 5, 18500.84),
		('Honda', 'Sivik', 2005, 5, 18800.77);


INSERT INTO cars (state_number, vin, color, fuel_type, fuel_quantity, model_id)
VALUES 	('A799KE155', '9872342', 'Silver', 95, 30, 4),
		('K417XO55', '1234342', 'Black', 98, 20, 4),
		('E333KP77', '9800123', 'Red', 92, 40, 1),
		('X111XE177', '277782', 'Blue', 95, 55, 1),
		('O341OO177', '827773', 'White', 95, 60, 2),
		('M868KT777', '662784', 'Orange', 100, 15, 2),
		('A712CC97', '8372884', 'Black', 95, 22, 3),
		('T594TE50', '0033432', 'Green', 92, 35, 3),
		('C119KK550', '199848', 'Silver', 95, 70, 5),
		('C129CM70', '4773929', 'Yellow', 98, 12, 5),
		('E709OX70', '098124', 'White', 95, 10, 6),
		('B381BO75', '837267', 'Grey', 98, 41, 6);


INSERT INTO orders (start_date, end_date, car_id)
VALUES 	('2026-01-10 10:00:00', '2026-01-10 19:00:00', 1),
		('2026-01-12 09:00:00', '2026-01-13 12:00:00', 12),
		('2026-01-13 12:00:00', '2026-01-13 18:00:00', 10),
		('2026-01-15 14:00:00', '2026-01-15 21:00:00', 8),
		('2026-01-17 18:00:00', '2026-01-17 22:00:00', 9),
		('2026-01-20 06:00:00', '2026-01-20 09:00:00', 4),
		('2026-01-23 09:00:00', '2026-01-25 12:00:00', 5), 
		('2026-01-26 16:00:00', '2026-01-26 18:00:00', 3);



INSERT INTO clients_orders (client_id, order_id)
VALUES 	(1, 2),
		(3, 1),
		(5, 8),
		(6, 5),
		(7, 4),
		(8, 3),
		(4, 7),
		(10, 7);



