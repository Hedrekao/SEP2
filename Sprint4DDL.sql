SET SCHEMA 'food_waste';

ALTER TABLE product
    DROP category;

CREATE DOMAIN category_name_type AS varchar(25) CHECK ( VALUE IN ('Frozen Food', 'Meat & Seafood', 'Diary', 'Bakery',
                                                                  'Beverages', 'Snacks', 'Fruits', 'Vegetables'));

CREATE TABLE IF NOT EXISTS category
(
    product_number int,
    category_name  category_name_type,

    PRIMARY KEY (product_number, category_name),

    FOREIGN KEY (product_number)
        REFERENCES product (product_number)
);

ALTER TABLE orders
ALTER COLUMN email SET NOT NULL;