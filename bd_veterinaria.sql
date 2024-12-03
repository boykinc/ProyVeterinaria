drop database bd_veterinaria;

create database bd_veterinaria;

use bd_veterinaria;

create table tipo_usuario(
id_tipo_usu int auto_increment primary key not null ,
descrip_tipo varchar(50) not null
);

select * from tipo_usuario;
insert into tipo_usuario (id_tipo_usu , descrip_tipo)  values (1 ,'USER');
insert into tipo_usuario (id_tipo_usu , descrip_tipo)  values (2 ,'ADMIN');

create table tb_usuario(
id_usuario int auto_increment primary key not null , 
nom_usu varchar(50) not null ,
ape_usu varchar(50) not null ,
email_usu varchar(50) not null unique ,
contra_usu varchar(50) not null ,
fono_usu	char(9) not null ,
direc_usu	varchar(50) not null ,
id_tipo_usu int not null ,
foreign key (id_tipo_usu) references tipo_usuario(id_tipo_usu)
);

/*creen un usuario por persona, por el momento se realizara de esta manera
despues tendremos que modificar lo que es el encriptamiento del pwd */

select * from tb_usuario;

insert into tb_usuario (nom_usu, ape_usu, email_usu, contra_usu, fono_usu, direc_usu, id_tipo_usu)  
				values ( 'Mariana','Wisman','Mariana@gmail.com','holamundo123','123456789', 'Ate',2);
insert into tb_usuario (nom_usu, ape_usu, email_usu, contra_usu, fono_usu, direc_usu, id_tipo_usu)  
				values ( 'Katerina','Garay','kate@gmail.com','123','987456123', 'San Miguel',1);
insert into tb_usuario (nom_usu, ape_usu, email_usu, contra_usu, fono_usu, direc_usu, id_tipo_usu)  
				values ( 'Piero','Caro','piero@gmail.com','Piero123','999888777', 'Rimac',2);
                

create table tb_especie(
id_especie int auto_increment primary key not null,
descrip_espe varchar(100) not null
);


create table tb_mascota(
id_mascota  int auto_increment primary key not null ,
nom_masco   varchar(50) not null ,
id_especie  int not null,
raza		varchar(40), 
sexo		char(1),
fec_nac		datetime not null,
peso_masco	decimal(10,2) not null,
id_usuario  int not null ,
foreign key (id_usuario) references tb_usuario(id_usuario),
foreign key (id_especie) references tb_especie(id_especie)
);

create table tb_especialidad(
id_especialidad int auto_increment primary key not null,
des_especialidad varchar(100) not null
);

create table tb_veterinario(
id_veterinario int auto_increment primary key not null ,
id_especialidad int not null,
id_usuario  int not null ,
hora_dis	datetime,
foreign key (id_especialidad) references tb_especialidad(id_especialidad),
foreign key (id_usuario) references tb_usuario(id_usuario)
);

create table tb_servicios(
id_servicio int auto_increment primary key not null ,
nombre_servi varchar(100) not null,
des_servi varchar(50) not null ,
precio		decimal(10,2) not null 
);

create table tb_cita(
id_cita int auto_increment primary key not null,
id_mascota int not null,
id_veterinario int not null,
fec_cita datetime not null,
fec_registrado TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
motivo varchar(200) null ,
estado char(3) not null ,
id_servicio int not null,
foreign key (id_servicio) references tb_servicios(id_servicio),
foreign key (id_mascota) references tb_mascota(id_mascota),
foreign key (id_veterinario) references tb_veterinario(id_veterinario)
);

create table tb_historial(
id_historial int auto_increment primary key not null ,
fec_histo	datetime not null,
diagnos_histo varchar(100) not null,
trata_histo   varchar(100) not null,
obser_histo   varchar(100)  null,
id_mascota int not null,
id_veterinario int not null,
foreign key (id_mascota) references tb_mascota(id_mascota),
foreign key (id_veterinario) references tb_veterinario(id_veterinario)
);

select * from tb_usuario;