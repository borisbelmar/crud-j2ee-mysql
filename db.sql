DROP DATABASE IF EXISTS prueba;

CREATE DATABASE IF NOT EXISTS prueba CHARACTER SET UTF8MB4;
USE prueba;

CREATE TABLE IF NOT EXISTS tbl_categoria (
	cat_cod_categoria INT(11) AUTO_INCREMENT,
	cat_nom_categoria VARCHAR(30) NOT NULL,
	CONSTRAINT PK_cat PRIMARY KEY(cat_cod_categoria)
);

CREATE TABLE IF NOT EXISTS tbl_producto (
	prod_codigo INT(11) AUTO_INCREMENT,
	prod_nombre VARCHAR(30) NOT NULL,
	prod_precio INT(11) NOT NULL,
	prod_cod_categoria INT(11),
	CONSTRAINT PK_prod PRIMARY KEY(prod_codigo),
	CONSTRAINT FK_categoria FOREIGN KEY(prod_cod_categoria) 
	REFERENCES tbl_categoria(cat_cod_categoria) 
	ON DELETE SET NULL
);

INSERT INTO tbl_categoria(cat_nom_categoria) VALUES 
("Electrohogar"), 
("Gaming");

SELECT * FROM tbl_categoria;

INSERT INTO tbl_producto(prod_nombre, prod_precio, prod_cod_categoria) VALUES 
("Microondas", 50000, 1), 
("PlayStation 4", 220000, 2);

SELECT * FROM tbl_producto;