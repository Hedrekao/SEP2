CREATE SCHEMA food_waste;

SET SCHEMA 'food_waste';

CREATE TABLE IF NOT EXISTS "order"
(
    order_number serial PRIMARY KEY,
    total_price  decimal(6, 2) CHECK ( total_price > 0 ) NOT NULL,
    is_delivery  boolean                                 NOT NULL,
    date         date                                    NOT NULL
);

CREATE TABLE IF NOT EXISTS product
(
    product_number int PRIMARY KEY,
    category       varchar(30),
    name           varchar(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS item
(
    item_number       serial PRIMARY KEY,
    quantity_in_stock int                               NOT NULL,
    price             decimal(6, 2) CHECK ( price > 0 ) NOT NULL,
    expiration_date   date                              NOT NULL,
    product_number    int REFERENCES product (product_number)
);

CREATE TABLE IF NOT EXISTS order_item
(
    order_number     int,
    item_number      int,
    quantity_of_item int NOT NULL,

    PRIMARY KEY (order_number, item_number),

    FOREIGN KEY (order_number)
        REFERENCES "order" (order_number),

    FOREIGN KEY (item_number)
        REFERENCES item (item_number)
);

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


INSERT INTO product (product_number, category, name)
VALUES (5455, 'Beverages', 'Soda'),
       (4224, 'Bakery', 'Bread'),
       (7645, 'Diary', 'Milk');

INSERT INTO item (quantity_in_stock, price, expiration_date, product_number)
VALUES (3, 25.22, '2022-04-05', 5455),
       (4, 53.33, '2022-07-22', 7645);


TRUNCATE "order" RESTART IDENTITY CASCADE;