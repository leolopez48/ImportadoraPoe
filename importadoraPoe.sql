DROP DATABASE IF EXISTS importadoraPoe;
create database importadoraPoe;
use importadoraPoe;

create table usuario
(
id_usuario INT AUTO_INCREMENT PRIMARY KEY,
nombre_usuario varchar(200) not null,
correo_usuario varchar(200) not null,
contra varchar(128) not null,
tipo_usuario int not null,
foto varchar(150) not null
);

create table cliente 
(
id_cliente INT AUTO_INCREMENT PRIMARY KEY,
id_usuario int not null,
nombre_cliente varchar(200) not null,
telefono_cliente varchar(20) not null,
direccion_cliente varchar(200) not null
);

create table proveedor
(
id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
id_usuario int not null,
nombre_proveedor varchar(200) not null,
direccion_proveedor varchar(200) not null,
telefono_proveedor varchar(20) not null
);

create table vehiculo
(
id_vehiculo int not null,
id_categoria int not null,
foto varchar(150) not null,
nombre varchar(200) not null,
color varchar(25),
marca varchar(30),
modelo varchar(30),
num_puertas int,
id_unidad_medida int not null,
precio double not null
);

create table detalle_oferta 
(
id_detalle int not null,
id_usuario int not null,
id_vehiculo int not null,
cantidad int not null,
fecha_oferta date
);

create table impuesto
(
id_impuesto int not null,
nombre varchar(30) not null,
valor float
);

create table categoria
(
id_categoria int not null,
nombre_categoria varchar(200) not null
);

create table unidad_medida
(
id_unidad_medida int not null,
nombre varchar(100) not null
);

/*Llaves primarias*/

alter table vehiculo add constraint pk_id_vehiculo primary key(id_vehiculo);
alter table detalle_oferta add constraint pk_id_detalle_vehiculo primary key(id_detalle, id_vehiculo);
alter table impuesto add constraint pk_id_impuesto primary key(id_impuesto);
alter table categoria add constraint pk_id_categoria primary key(id_categoria);
alter table unidad_medida add constraint pk_id_unidad_medida primary key(id_unidad_medida);

/*Llaves foraneas*/

alter table cliente add constraint fk_id_usuario foreign key(id_usuario)
references usuario(id_usuario) on delete cascade;
alter table proveedor add constraint fk_id_usuario_proveedor foreign key(id_usuario)
references usuario(id_usuario);
alter table detalle_oferta add constraint fk_id_vehiculo foreign key(id_vehiculo)
references vehiculo(id_vehiculo);
alter table detalle_oferta add constraint fk_id_usuario_detalle foreign key(id_usuario)
references usuario(id_usuario);
alter table vehiculo add constraint fk_id_categoria foreign key(id_categoria)
references categoria(id_categoria);
alter table vehiculo add constraint fk_id_unidad_medida foreign key(id_unidad_medida)
references unidad_medida(id_unidad_medida);

/*Datos de prueba*/

insert into usuario values
(1,'pedroM35', 'pedrom35@gmail.com', 'b4WE4bXVBj0=', 1, 'foto'),
(2,'juan36', 'juan36@gmail.com', 'b4WE4bXVBj0=', 2, 'foto'),
(3,'robertoP37', 'robertoP37@gmail.com', 'b4WE4bXVBj0=', 3, 'foto'),
(4,'marcosLS', 'marcosLS@gmail.com', 'b4WE4bXVBj0=', 4, 'foto'),
(5,'segioR', 'segioR@gmail.com', 'b4WE4bXVBj0=', 1, 'foto'),
(6,'marlonSF', 'marlonSF@gmail.com', 'b4WE4bXVBj0=', 2, 'foto'),
(7,'miguel67', 'miguel67@gmail.com', 'b4WE4bXVBj0=', 3, 'foto'),
(8,'martaL35', 'martaL35@gmail.com', 'b4WE4bXVBj0=', 4, 'foto'),
(9,'Franco123', 'Franco123@gmail.com', 'b4WE4bXVBj0=', 1, 'foto'),
(10,'Ronaldo7', 'Ronaldo7@gmail.com', 'b4WE4bXVBj0=', 2, 'foto');	

insert into cliente values 
(1, 1, 'Pedro Martinez', '1234-5678', 'Santa Tecla'),
(2, 2, 'Juan Perez', '1234-5678', 'Sonsonate'),
(3,3, 'Roberto Palermo', '1234-5678', 'San Salvador'),
(4,4, 'Marcos Lara', '1234-5678', 'Santa Ana'),
(5,5, 'Sergio Ramirez', '1234-5678', 'Zacatecoluca');

insert into proveedor values
(1,6, 'Marlon Saravia', '1234-5678', 'La palma'),
(2,7, 'Miguel López', '1234-5678', 'Ciudad merliot'),
(3,8, 'Martha López', '1234-5678', 'El congo'),
(4,9, 'Franco Sosa', '1234-5678', 'Ahuachapan'),
(5,10, 'Ronaldo Escalante', '1234-5678', 'Apopa');

insert into categoria values
(1,'Sedan'),
(2,'4WD'),
(3,'Mini Truck'),
(4,'Eléctrico'),
(5,'Pickup'),
(6, 'Van');

insert into unidad_medida values
(1,'Toneladas'),
(2,'Libras'),
(3,'Kilogramos');

insert into vehiculo values
(1,1,'C:\Program Files (x86)\Importadora\Carros\Untitled_design_11_large.jpg','Honda Civic 2017','Negro','Honda','Honda Civic Type R',4,1, 34775),
(2,1,'C:\Program Files (x86)\Importadora\Carros\Untitled_design_11_large.jpg','Hyundai Elantra','Rojo','Hyundai','Elantra 2016',4,2, 6850),
(3,1,'C:\Program Files (x86)\Importadora\Carros\Untitled_design_11_large.jpg','Mazda','Rojo','Mazda','3 Sedan 2019',4,3, 20745),
(4,2,'C:\Program Files (x86)\Importadora\Carros\Untitled_design_15_large.jpg','Toyota','Rojo','Toyota','Toyota Hilux',4,1, 23500),
(5,3,'C:\Program Files (x86)\Importadora\Carros\Untitled_design_13_large.jpg','Isuzu','Blanco','Isuzu','Isuzu Serie Reward',4,2, 22500),
(6,4,'C:\Program Files (x86)\Importadora\Carros\Untitled_design_17_large.jpg','Smart Fortwo','Blanco','Smart Fortwo','smart EQ ForTwo',2,3, 11149),
(7,5,'C:\Program Files (x86)\Importadora\Carros\Untitled_design_15_large.jpg','Nissan','Rojo','Nissan','Pick Up Nissan Navara 2012',4,1, 15500),
(8,6,'C:\Program Files (x86)\Importadora\Carros\Untitled_design_16_large.jpg','Ford','Rojo','Ford','VAN Ford Transit 2016',4,3, 32700);
					 
insert into impuesto values
(1, 'IVA', 0.13),
(2, 'DAI - Sedan', 0.25),
(3, 'DAI - 4WD', 0.25),
(4, 'DAI - Mini Truck', 0.01),
(5, 'DAI - Eléctrico', 0.30),
(6, 'DAI - Pickup', 0.05),
(7, 'DAI - Van', 0.05),
(8, 'CESC', 0.05);

insert into detalle_oferta values 
(1,1,1,2,'2020-11-10'),
(1,1,6,1,'2020-11-10'),
(2,2,2,1,'2020-11-11'),
(2,2,4,1,'2020-11-11'),
(3,3,3,1,'2020-11-9'),
(4,4,5,1,'2020-11-12');

/*Verificando los datos prueba*/


/*select * from cliente;
select * from usuario;
select * from detalle_oferta;
select * from proveedor;
select * from categoria;
select * from unidad_medida;
select * from vehiculo;
select * from impuesto;
select * from detalle_oferta;*/