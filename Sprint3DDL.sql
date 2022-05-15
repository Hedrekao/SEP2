SET SCHEMA 'food_waste';

CREATE DOMAIN address_type AS varchar(50);

CREATE TABLE IF NOT EXISTS shop
(
    name    varchar(30) NOT NULL,
    address address_type PRIMARY KEY
);

ALTER TABLE item
    ADD COLUMN shop_address address_type NOT NULL;

ALTER TABLE item
    ADD CONSTRAINT fk_shop_address FOREIGN KEY (shop_address) REFERENCES shop (address);

-- DROP TABLE "users";

CREATE TABLE IF NOT EXISTS employee
(
    user_id       serial PRIMARY KEY,
    username      varchar(30) UNIQUE                     NOT NULL,
    hash_password int                                    NOT NULL,
    shop_address  address_type REFERENCES shop (address) NOT NULL
);

INSERT INTO shop(name, address)
VALUES ('Bilka', 'Høegh Guldbergs Gade 10, 8700 Horsens'),
       ('Netto', 'Vestergade 25-27-29, 8700 Horsens');

INSERT INTO employee(username, hash_password, shop_address)
VALUES ('hedrekao', 2025697707, 'Vestergade 25-27-29, 8700 Horsens');

INSERT INTO item(quantity_in_stock, price, expiration_date, product_number, shop_address)
VALUES (5, 11, '2022-05-25', 6969, 'Vestergade 25-27-29, 8700 Horsens'),
       (3, 15, '2022-05-28', 9876, 'Vestergade 25-27-29, 8700 Horsens'),
       (7, 9, '2022-05-22', 6969, 'Høegh Guldbergs Gade 10, 8700 Horsens'),
       (8, 13, '2022-05-21', 9876, 'Høegh Guldbergs Gade 10, 8700 Horsens');

ALTER TABLE "order"
    RENAME TO orders;

ALTER TABLE orders
    ADD COLUMN email            varchar(30 ) CHECK ( email like '%@%' ),
    ADD COLUMN delivery_type    varchar(10) CHECK ( delivery_type IN ('pick-up', 'delivery') ),
    ADD COLUMN delivery_details varchar(60),
    ADD COLUMN shop_address     address_type NOT NULL;

ALTER TABLE orders
    DROP COLUMN is_delivery CASCADE ;


    ALTER TABLE orders
    ADD CONSTRAINT fk_shop_address FOREIGN KEY (shop_address) REFERENCES shop (address);

ALTER TABLE orders
    ADD COLUMN is_completed boolean NOT NULL;

ALTER TABLE orders
    ADD COLUMN time char(8) NOT NULL;



