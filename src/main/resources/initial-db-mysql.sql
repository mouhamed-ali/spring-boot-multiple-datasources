
-- insert a new address
INSERT INTO ADDRESS(id, building, country, street) VALUES (1, 'Mount Eden Road', 'USA', '445 Mount Eden Road');

-- insert a new customer
INSERT INTO CUSTOMER (id, first_name, last_name, email, phone_number, birth_date, address_id) VALUES (1, 'Hugo', 'MYSQL', 'hugo@gmail.com', '00047561238', 'Nevada', 1);