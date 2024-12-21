drop database bd_veterinaria;

create database bd_veterinaria;

use bd_veterinaria;

create table tb_usuario(
id_usuario int auto_increment primary key not null , 
nom_usu varchar(50) not null ,
ape_usu varchar(50) not null ,
fono_usu	char(9) not null ,
direc_usu	varchar(50) not null ,
estado		char(1) not null
);

/*creen un usuario por persona, por el momento se realizara de esta manera
despues tendremos que modificar lo que es el encriptamiento del pwd */



insert into tb_usuario (nom_usu, ape_usu,fono_usu, direc_usu, estado)  
				values ( 'Mariana','Wisman','123456789', 'Ate','A');
                
insert into tb_usuario (nom_usu, ape_usu,fono_usu, direc_usu, estado)  
				values ( 'Piero','Jara','123456789', 'Rimac','A');

select * from tb_usuario;          

create table tb_especie(
 id_especie int auto_increment primary key not null,
 descrip_espe varchar(100)
);



INSERT INTO tb_especie ( descrip_espe) VALUES
( 'Gato'),
( 'Perro'),
( 'Loro'),
( 'Puerco');

select * from tb_especie;


/*drop table tb_usuario;
*/

CREATE TABLE tb_mascota (
    id_mascota INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nom_masco VARCHAR(50) NOT NULL,
    raza VARCHAR(40),
    sexo CHAR(1) ,
    edad int NOT NULL,
    peso_masco decimal(10,2),
	propietario VARCHAR(100) NOT NULL,
    id_especie int not null,
    estado char(1) not null,
    foreign key (id_especie) references tb_especie(id_especie)
);

INSERT INTO tb_mascota (nom_masco, raza, sexo, edad, peso_masco, propietario, id_especie, estado) VALUES
('Firulais', 'Pastor Alemán', 'M', 5, 20.50, 'Juan Pérez', 2, 'A'),
('Whiskers', 'Siames', 'F', 3, 4.25, 'Ana Gómez', 1, 'A'),
('Chester', 'Pitbul', 'M', 2, 25.25, 'Pedro Suarez', 2, 'I');

select * from tb_mascota;
/*
create table tb_cita(
id_cita int auto_increment primary key not null,
id_mascota int not null,
fec_cita datetime not null,
fec_registrado TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
motivo varchar(200) null ,
estado char(3) not null ,
foreign key (id_mascota) references tb_mascota(id_mascota)
);



INSERT INTO tb_cita (id_mascota, fec_cita, motivo, estado) 
VALUES 
(1, '2024-12-20 10:00:00', 'Vacunación anual', 'PEN'), 
(2, '2024-12-22 15:30:00', 'Revisión general', 'PEN'),
(3, '2024-12-25 09:00:00', 'Desparasitación', 'PEN'),
(4, '2024-12-28 14:00:00', 'Consulta por comportamiento', 'PEN');

*/

CREATE TABLE IF NOT EXISTS tb_especialidad (
  id_especialidad int(11) NOT NULL AUTO_INCREMENT,
  des_especialidad varchar(100) NOT NULL,
  PRIMARY KEY (id_especialidad)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*drop table tb_especialidad;*/
--
-- Volcado de datos para la tabla tb_especialidad
--

INSERT INTO tb_especialidad ( des_especialidad) VALUES
('Oftalmologia'),
('Quimioterapia'),
('Radiologia'),
( 'Traumatologia');

select * from tb_especialidad;




create table tb_veterinario(
id_veterinario int auto_increment primary key not null ,
id_especialidad int not null,
id_usuario  int not null ,
estado char(1) not null,
foreign key (id_especialidad) references tb_especialidad(id_especialidad),
foreign key (id_usuario) references tb_usuario(id_usuario)
);

insert into tb_veterinario (id_especialidad,id_usuario,estado) values
	(2,1,'A'),
    (1,2,'A');
    
select * from tb_veterinario ;