
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
	id varchar(16) NOT NULL PRIMARY KEY,
	descripcion varchar(512)
);

drop table if exists productos_pedido;
create table productos_pedido (
	producto varchar(16) NOT NULL PRIMARY KEY,
	cantidad integer,
	precioUnitario float,
	compra varchar(16),
	FOREIGN KEY (producto) REFERENCES productos(id),
	FOREIGN KEY (compra) REFERENCES comprar(id)
);

drop table if exists productos_stock;
create table productos_stock (
	producto varchar(16) NOT NULL PRIMARY KEY,
	cantidad integer,
	FOREIGN KEY (producto) REFERENCES productos(id)
);

drop table if exists comprar;
create table comprar (
	id varchar(16) NOT NULL PRIMARY KEY,
	fecha date,
	valoracion integer,
	comentarios varchar(1024),
	precio float,
	username varchar(32) NOT NULL,
	FOREIGN KEY (username) REFERENCES usuarios(username)
);


-- Datos de prueba

insert into usuarios values ('admin','admin','pablovarela182@gmail.com',true,true);
insert into usuarios values ('pablo','1234','pablovarela182@gmail.com',false,true);

insert into productos values ('1','Dogs Eating Dogs | blink-182 | USA | 9.95');
insert into productos_stock values ('1', 10);

insert into productos values ('2','Get Nice | zebrahead | USA | 12.49');
insert into productos_stock values ('2', 5);

insert into productos values ('3','Age of Pamparius | Turbonegro | Norway | 8.95');
insert into productos_stock values ('3', 4);

insert into productos values ('4','Free Volume 4 | KDrew | USA | 4.95');
insert into productos_stock values ('4', 12);
