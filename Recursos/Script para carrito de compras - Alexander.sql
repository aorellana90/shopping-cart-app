

-- Creacion de base de datos
create database control_compras
with owner = aorellana
encoding = 'UTF8'
connection limit = -1
IS_TEMPLATE = false;


--Creacion de esquema
create schema sch_control
authorization aorellana;

-- Asignacion de permisos al esquema
grant all on schema sch_control to aorellana;


-- Creacion de tabla donde se almacenaran las ordenes de compra
create table sch_control.orden( 
idOrden serial primary key not null,
fechaCreacion timestamp without time zone,
fechaModificacion timestamp without time zone,
cliente varchar(250),
estadoOrden varchar(15),
metodoPago varchar(15),
estadoPago varchar(15),
total numeric
);


-- Creacion de tabla donde se almacenaran los detalles de orden de la compra
create table sch_control.detalleOrden( 
idDetalleOrden serial primary key not null,
idOrden int not null,
idProducto int,
cantidad int,
precio numeric,
subtotal numeric,
constraint fkOrdenDetalleOrden foreign key(idOrden)
references sch_control.orden(idOrden) match simple on update no action on delete no action
);


-- Creacion de tabla donde se almacenaran los registros de pago
create table sch_control.pago(
idPago serial primary key not null,
idOrden int not null unique,
nombres varchar(50),
apellidos varchar(50),
correo varchar(100) unique,
telefono varchar(15) unique,
numeroTarjeta varchar(30) unique,
constraint fkOrdenPago foreign key(idOrden) 
references sch_control.orden(idOrden) match simple on update no action on delete no action
);

