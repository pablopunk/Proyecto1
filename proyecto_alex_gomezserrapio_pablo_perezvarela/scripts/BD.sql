
-- create user 'dawa'@'localhost' identified by 'dawa';
-- create database proyecto1;
-- grant all privileges on proyecto1.* to 'dawa'@'localhost' with grant option;
use proyecto1; 

drop table if exists usuarios;
create table usuarios (
	username varchar(32) NOT NULL PRIMARY KEY,
	password varchar(32) NOT NULL,
	mail varchar(64) NOT NULL,
	admin boolean,
	vip boolean
);

drop table if exists productos;
create table productos (
	id integer NOT NULL PRIMARY KEY auto_increment,
	nombre varchar(32),
	descripcion varchar(512),
	precio float
);

drop table if exists productos_pedido;
create table productos_pedido (
	producto integer NOT NULL PRIMARY KEY auto_increment,
	cantidad integer,
	precioUnitario float,
	compra integer,
	FOREIGN KEY (producto) REFERENCES productos(id),
	FOREIGN KEY (compra) REFERENCES comprar(id)
);

drop table if exists productos_stock;
create table productos_stock (
	producto integer NOT NULL PRIMARY KEY,
	cantidad integer,
	FOREIGN KEY (producto) REFERENCES productos(id)
);

drop table if exists comprar;
create table comprar (
	id integer NOT NULL PRIMARY KEY auto_increment,
	fecha date,
	valoracion integer,
	comentarios varchar(1024),
	precio float,
	username varchar(32) NOT NULL,
	FOREIGN KEY (username) REFERENCES usuarios(username)
);
