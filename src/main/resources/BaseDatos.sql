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

INSERT INTO person (name, gender, age, identification, address, phone)
VALUES ('Jose Lema', 'MALE', 30, '1234567890', 'Otavalo y sn principal', '098254785'),
       ('Marianela Montalvo', 'FEMALE', 41, '1234567891', 'Amazonas y NNUU', '097548965'),
       ('Juan Osorio', 'MALE', 29, '1234567893', '13 de junio y Equinoccial', '098874587');

INSERT INTO client (client_id, password, status, person_identification)
VALUES ('jlema', '1234', true, '1234567890'),
       ('mmontalvo', '5678', true, '1234567891'),
       ('josorio', '5678', true, '1234567893');

