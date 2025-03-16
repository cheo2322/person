CREATE DATABASE devsu_test;

CREATE TABLE IF NOT EXISTS person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    gender VARCHAR(10),
    age INTEGER,
    identification VARCHAR(100) UNIQUE,
    address VARCHAR(255),
    phone VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS client (
    id SERIAL PRIMARY KEY,
    client_id VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    status BOOLEAN,
    person_identification VARCHAR(100),
    FOREIGN KEY (person_identification) REFERENCES person (identification)
);

CREATE TABLE IF NOT EXISTS account (
    id SERIAL PRIMARY KEY,
    client_identification VARCHAR(10) NOT NULL,
    number VARCHAR(6) NOT NULL UNIQUE,
    type VARCHAR(10),
    initial_balance DOUBLE PRECISION NOT NULL,
    status BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS movement (
    id SERIAL PRIMARY KEY,
    movement_date TIMESTAMP NOT NULL,
    type VARCHAR(10),
    movement_value DOUBLE PRECISION NOT NULL,
    balance DOUBLE PRECISION NOT NULL,
    account_number VARCHAR(6),
    FOREIGN KEY (account_number) REFERENCES account (number)
);
