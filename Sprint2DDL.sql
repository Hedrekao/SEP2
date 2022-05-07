SET SCHEMA 'food_waste';

CREATE TABLE IF NOT EXISTS users
(
    userId       serial PRIMARY KEY,
    username     varchar(30) UNIQUE NOT NULL,
    hashPassword int                NOT NULL,
    isEmployee   bool               NOT NULL
);

INSERT INTO users(username, hashPassword, isEmployee)
VALUES ('hedrekao', 2025697707, TRUE);
