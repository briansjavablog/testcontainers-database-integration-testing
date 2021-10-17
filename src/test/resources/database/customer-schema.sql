
CREATE TABLE Customer (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     firstName VARCHAR(255),
     lastName VARCHAR(255),
     dateOfBirth VARCHAR(255),
     gender VARCHAR(255)
);

INSERT INTO Customer (id, firstName, lastName, dateOfBirth, gender) VALUES (1, 'Joe', 'Bloggs', '1983-05-17', 'male');
INSERT INTO Customer (id, firstName, lastName, dateOfBirth, gender) VALUES (2, 'Jill', 'Bloggs', '1984-08-19', 'female');