-- Script SQL para crear las bases de datos en PostgreSQL con todas sus relaciones

-- Crear la base de datos cliente_db
CREATE DATABASE cliente_db;

-- Conectar a la base de datos cliente_db
\c cliente_db;

-- Crear tabla persona en cliente_db
CREATE TABLE persona (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(50),
    edad INT NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(50)
);

-- Crear tabla cliente en cliente_db que hereda de persona
CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    contrasena VARCHAR(255) NOT NULL,
    estado BOOLEAN NOT NULL,
    CONSTRAINT fk_persona FOREIGN KEY (id) REFERENCES persona (id)
);

-- Crear la base de datos movimientos_db
CREATE DATABASE movimientos_db;

-- Conectar a la base de datos movimientos_db
\c movimientos_db;

-- Crear tabla cuenta en movimientos_db
CREATE TABLE cuenta (
    id SERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(50) UNIQUE NOT NULL,
    tipo_cuenta VARCHAR(50) NOT NULL,
    saldo_inicial DECIMAL(15, 2) NOT NULL,
    estado BOOLEAN NOT NULL,
    cliente_id INT NOT NULL,
    nombre_cliente VARCHAR(255) NOT NULL
);

-- Crear tabla movimiento en movimientos_db
CREATE TABLE movimiento (
    id SERIAL PRIMARY KEY,
    fecha DATE NOT NULL,
    tipo_movimiento VARCHAR(50) NOT NULL,
    valor DECIMAL(15, 2) NOT NULL,
    saldo DECIMAL(15, 2) NOT NULL,
    estado BOOLEAN NOT NULL,
    cuenta_id INT NOT NULL,
    CONSTRAINT fk_cuenta FOREIGN KEY (cuenta_id) REFERENCES cuenta (id)
);

-- Índices para mejorar la búsqueda
CREATE INDEX idx_cliente_id ON cuenta (cliente_id);
