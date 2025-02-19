DROP DATABASE aviation_2025;

CREATE DATABASE aviation_2025;

USE aviation_2025;

CREATE TABLE IF NOT EXISTS airport (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS aircraft (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(100) NOT NULL,
    airline_name VARCHAR(100),
    number_of_passengers INT
);
