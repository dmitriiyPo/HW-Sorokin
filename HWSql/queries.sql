-- a. SELECT с сортировкой (ORDER BY) и фильтрацией (WHERE).
SELECT client_first_name, client_last_name, age, phone, drivers_license
FROM clients
WHERE gender = 'FEMALE'
ORDER BY age ASC;


-- b. Группировка (GROUP BY) с агрегатными функциями.
SELECT cm.brand, cm.model, cm.year_start, c.state_number, 
	ROUND(SUM(EXTRACT(EPOCH FROM (o.end_date - o.start_date)) / 3600.0), 2) AS total_hours,
    ROUND(SUM(EXTRACT(EPOCH FROM (o.end_date - o.start_date)) / 3600.0 * cm.price_per_hour), 2) AS total_revenue
FROM cars AS c
JOIN car_models AS cm ON c.model_id = cm.model_id
JOIN orders AS o ON c.car_id = o.car_id
GROUP BY cm.brand, cm.model, cm.year_start, c.state_number
HAVING SUM( EXTRACT(EPOCH FROM (o.end_date - o.start_date)) / 3600.0 * cm.price_per_hour ) > 80000
ORDER BY total_revenue ASC;


-- c. Несколько JOIN
SELECT c.client_first_name, c.client_last_name, c.age, c.phone, cm.brand, cm.model, cm.year_start
FROM clients AS c
JOIN clients_orders AS co ON c.client_id = co.client_id
JOIN orders AS o ON co.order_id = o.order_id
JOIN cars AS ca ON o.car_id = ca.car_id
JOIN car_models AS cm ON ca.model_id = cm.model_id;


-- d. Обновление (UPDATE)
UPDATE clients
SET age = 50
WHERE client_first_name = 'Margo';


-- e. Удаление (DELETE)
DELETE FROM clients_orders
WHERE client_id = 3;

DELETE FROM orders
WHERE order_id = 1;

DELETE FROM cars
WHERE state_number = 'A799KE155';

