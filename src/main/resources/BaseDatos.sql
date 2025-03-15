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



