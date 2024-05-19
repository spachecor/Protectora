drop database if exists protectora;
create database protectora;
use protectora;

create table animal(
id varchar(10),
nombre varchar(25),
tipo varchar(25),
color varchar(25),
sexo varchar(25),
fechaNacimiento date,
fechaEntradaProtectora date,
castrado boolean,
chip long,
raza varchar(50),
tamanio varchar(25),
img longblob,
constraint animal_id_pk primary key(id)
);

create table estadoAnimal(
animal varchar(10),
cambioEstado datetime,
estado varchar(25),
constraint estadoAnimal_multiple_pk primary key(animal, cambioEstado),
constraint estadoAnimal_animal_fk foreign key(animal) references animal(id) on delete cascade on update cascade
);

create table usuario(
id varchar(10),
email varchar(50),
telefono int,
nombre varchar(100),
fechaNacimiento date,
dni char(9),
ocupacion varchar(100),
direccion varchar(200),
localidad varchar(100),
provincia varchar(100),
codigoPostal int,
constraint usuario_id_pk primary key(id)
);

create table solicitudAdopcion (
id varchar(10),
animal varchar(10),
adoptante varchar(10),
constraint solicitudAdopcion_id_pk primary key(id),
constraint solicitudAdopcion_animal_fk foreign key(animal) references animal(id) on delete cascade on update cascade,
constraint solicitudAdopcion_adoptante_fk foreign key(adoptante) references usuario(id) on delete cascade on update cascade
);

create table estadoAdopcion (
adopcion varchar(10),
cambioEstado datetime,
estado varchar(25),
constraint estadoAdopcion_multiple_pk primary key(adopcion, cambioEstado),
constraint estadoAnimal_adopcion_fk foreign key(adopcion) references solicitudAdopcion(id) on delete cascade on update cascade
);

set sql_safe_updates = 0;
delete from animal;
delete from usuario;
delete from solicitudAdopcion;

insert into usuario values ("usu-1", "usuario1@gmail.com", 987456123, "Francisco Protos", "19850612", "12345678A", null, "Calle Fake, 3", "Sevilla", "Sevilla", 41015);
insert into usuario values ("usu-2", "usuario2@gmail.com", 654789321, "Francisca Protas", "19950218", "98765432F", null, "Calle AntiFake, 8", "Sevilla", "Sevilla", 41015);
insert into solicitudAdopcion values ("sol-1", "ani-1", "usu-1"), ("sol-2", "ani-3", "usu-2");

select * from animal;
select * from usuario;
select * from solicitudAdopcion;