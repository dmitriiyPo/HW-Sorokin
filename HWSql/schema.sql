CREATE TYPE genders AS ENUM ('MALE', 'FEMALE');

CREATE TABLE clients (
	client_id SERIAL PRIMARY KEY,
	client_first_name VARCHAR(15) NOT NULL,
	client_last_name VARCHAR(15) NOT NULL,
	age INT NOT NULL CHECK (age >= 18),
	gender genders NOT NULL,
	email TEXT NOT NULL UNIQUE,
	phone VARCHAR(20) NOT NULL UNIQUE,
	drivers_license VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE car_models (
	model_id SERIAL PRIMARY KEY,
	brand VARCHAR(10) NOT NULL UNIQUE,
	model VARCHAR(10) NOT NULL,
	year_start INT NOT NULL,
	seats INT NOT NULL,
	price_per_hour NUMERIC(9,2) NOT NULL CHECK (price_per_hour > 0)
);

CREATE TABLE cars (
	car_id SERIAL PRIMARY KEY,
	state_number VARCHAR(12) NOT NULL UNIQUE,
	vin VARCHAR(20) NOT NULL UNIQUE,
	color TEXT NOT NULL,
	fuel_type INT NOT NULL,
	fuel_quantity INT NOT NULL,
	model_id INT NOT NULL REFERENCES car_models (model_id)
);

CREATE TABLE orders (
	order_id SERIAL PRIMARY KEY,
	start_date TIMESTAMPTZ NOT NULL,
	end_date TIMESTAMPTZ NOT NULL,
	car_id INT NOT NULL REFERENCES cars (car_id)
);

CREATE TABLE clients_orders (
	client_id INT NOT NULL REFERENCES clients (client_id),
	order_id INT NOT NULL REFERENCES orders (order_id),
	PRIMARY KEY(client_id, order_id)
);


