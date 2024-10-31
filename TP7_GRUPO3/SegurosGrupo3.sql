-- Crear la base de datos
CREATE DATABASE segurosGroup;

-- Usar la base de datos
USE segurosGroup;

-- Crear la tabla tipoSeguros
CREATE TABLE tipoSeguros (
    idTipo INT AUTO_INCREMENT,
    descripcion VARCHAR(255) NOT NULL,
    PRIMARY KEY (idTipo)
);

-- Crear la tabla seguros
CREATE TABLE seguros (
    idSeguro INT AUTO_INCREMENT,
    descripcion VARCHAR(255) NOT NULL,
    idTipo INT NOT NULL,
    costoContratacion FLOAT NOT NULL,
    costoAsegurado FLOAT NOT NULL,
    PRIMARY KEY (idSeguro),
    FOREIGN KEY (idTipo) REFERENCES tipoSeguros(idTipo)
);

-- Insertar datos en tipoSeguros
INSERT INTO tipoSeguros (descripcion) VALUES ('Salud');
INSERT INTO tipoSeguros (descripcion) VALUES ('Vida');
INSERT INTO tipoSeguros (descripcion) VALUES ('Automóvil');
INSERT INTO tipoSeguros (descripcion) VALUES ('Hogar');

-- Insertar datos en seguros
INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoAsegurado) VALUES 
('Seguro de Salud Básico', 1, 200.0, 1000.0),
('Seguro de Vida Familiar', 2, 300.0, 5000.0),
('Seguro de Auto Completo', 3, 150.0, 2000.0),
('Seguro de Hogar Premium', 4, 400.0, 10000.0);

SELECT * FROM seguros
