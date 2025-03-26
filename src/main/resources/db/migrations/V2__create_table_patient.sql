CREATE TABLE patient (
    id serial INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    cellphone VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    date_of_birth DATE NOT NULL
);