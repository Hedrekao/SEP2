CREATE SCHEMA food_waste;

SET SCHEMA 'food_waste';

CREATE DOMAIN address_type AS varchar(50);

CREATE DOMAIN category_name_type AS varchar(25) CHECK ( VALUE IN ('Frozen Food', 'Meat & Seafood', 'Diary', 'Bakery',
                                                                  'Beverages', 'Snacks', 'Fruits', 'Vegetables'));

CREATE TABLE IF NOT EXISTS shop
(
    name    varchar(30) NOT NULL,
    address address_type PRIMARY KEY
);


CREATE TABLE IF NOT EXISTS orders
(
    order_number     serial PRIMARY KEY,
    total_price      decimal(6, 2) CHECK ( total_price > 0 ) NOT NULL,
    email            varchar(30) CHECK ( email LIKE '%@%' )  NOT NULL,
    date             date                                    NOT NULL,
    delivery_type    varchar(10) CHECK ( delivery_type IN ('pick-up', 'delivery') ),
    delivery_details varchar(60)                             NOT NULL,
    shop_address     address_type REFERENCES shop (address)  NOT NULL,
    is_completed     boolean                                 NOT NULL,
    time             char(8)                                 NOT NULL
);

CREATE TABLE IF NOT EXISTS product
(
    product_number int PRIMARY KEY,
    name           varchar(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS item
(
    item_number       serial PRIMARY KEY,
    quantity_in_stock int                                    NOT NULL,
    price             decimal(6, 2) CHECK ( price > 0 )      NOT NULL,
    expiration_date   date                                   NOT NULL,
    product_number    int REFERENCES product (product_number),
    shop_address      address_type REFERENCES shop (address) NOT NULL
);

CREATE TABLE IF NOT EXISTS order_item
(
    order_number     int,
    item_number      int,
    quantity_of_item int NOT NULL,

    PRIMARY KEY (order_number, item_number),

    FOREIGN KEY (order_number)
        REFERENCES orders (order_number),

    FOREIGN KEY (item_number)
        REFERENCES item (item_number)
);

CREATE TABLE IF NOT EXISTS category
(
    product_number int,
    category_name  category_name_type,

    PRIMARY KEY (product_number, category_name),

    FOREIGN KEY (product_number)
        REFERENCES product (product_number)
);

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

CREATE FUNCTION update_stock_function() RETURNS trigger
    LANGUAGE plpgsql
AS
$$
BEGIN
    UPDATE food_waste.item
    SET quantity_in_stock = item.quantity_in_stock - NEW.quantity_of_item
    WHERE item.item_number = NEW.item_number;
    RETURN NEW;
END;
$$;



CREATE TRIGGER update_stock
    AFTER INSERT
    ON order_item
    FOR EACH ROW
EXECUTE PROCEDURE update_stock_function();

ALTER TABLE order_item
    ENABLE TRIGGER update_stock;