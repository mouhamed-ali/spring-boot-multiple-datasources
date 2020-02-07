
-- insert a new customer
INSERT INTO CUSTOMER (id, first_name, last_name, email, phone_number, birth_place) VALUES (1, 'Hugo', 'POSTGRES', 'hugo@gmail.com', '00047561238', 'Nevada');

-- insert a new account
INSERT INTO ACCOUNT(id, balance, account_name, date_opened, customer_id) VALUES (1, 200000, 'Hugo-POSTGRES', '01021991', 1);