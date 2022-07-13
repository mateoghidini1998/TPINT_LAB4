drop schema bdturnos;
create schema bdTurnos;

use bdTurnos;

create table Especialidades
(
ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
descripcion VARCHAR(25)
);

INSERT into Especialidades (descripcion) VALUES ("Guardia");
INSERT into Especialidades (descripcion) VALUES ("Traumatologia");
INSERT into Especialidades (descripcion) VALUES ("Cardiologia");
INSERT into Especialidades (descripcion) VALUES ("Ecografia");
INSERT into Especialidades (descripcion) VALUES ("Dermatologia");
INSERT into Especialidades (descripcion) VALUES ("Nutricion");
INSERT into Especialidades (descripcion) VALUES ("Alergista");
INSERT into Especialidades (descripcion) VALUES ("Psiquiatria");
INSERT into Especialidades (descripcion) VALUES ("Neurologia");
INSERT into Especialidades (descripcion) VALUES ("Pediatria");
INSERT into Especialidades (descripcion) VALUES ("Kinesiologia");

create table Medicos
(
dni VARCHAR(11) NOT NULL PRIMARY KEY,
nombre VARCHAR(25),
apellido VARCHAR(25),
sexo VARCHAR(25) ,
nacionalidad VARCHAR(25),
fechaNac DATE,
direccion VARCHAR(50),
localidad VARCHAR(50),
provincia VARCHAR(50),
telefono VARCHAR(25),
celular VARCHAR(25),
correo VARCHAR(25),
estado INT,
id_especialidad INT,
FOREIGN KEY(id_especialidad) REFERENCES Especialidades(ID)
);


INSERT INTO Medicos (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo,id_especialidad, estado) VALUES ("41215514", "Mateo", "Ghidini", "Masculino", "Argentina", "1998-10-02", "Av Santa María de las Conchas 7300", "Tigre", "Buenos Aires", "1136176018", "1136176018","ghidinimateo1@gmail.com",1, 1);
INSERT INTO Medicos (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo,id_especialidad, estado) VALUES ("4121242", "Facundo", "Jannello", "Masculino", "Argentina", "1998-09-21", "Viamonte 200", "CABA", "Buenos Aires", "1136122222", "11333338","fjannello@gmail.com",2, 1);
INSERT INTO Medicos (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo,id_especialidad, estado) VALUES ("40676447", "Tomas", "Rodriguez", "Masculino", "Argentina", "1997-02-11", "Av Cazon 190", "Tigre", "Buenos Aires", "1136173338", "1136173333","rodriguezt@gmail.com",2, 1);
INSERT INTO Medicos (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo,id_especialidad, estado) VALUES ("42666533", "Gonzalo", "Perez", "Masculino", "Argentina", "1997-02-11", "Hipolito Yrigoyen 210", "Pacheco", "Buenos Aires", "112345433", "1236173333","perezgonzalo@hotmail.com",5, 1);
INSERT INTO Medicos (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo,id_especialidad, estado) VALUES ("40676442", "Tomas", "Rodriguez", "Masculino", "Argentina", "1997-02-11", "Av Cazon 190", "Tigre", "Buenos Aires", "1136173338", "1136173333","rodriguezt@gmail.com",1, 1);
INSERT INTO Medicos (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo,id_especialidad, estado) VALUES ("40216447", "Alejandro", "Herrera", "Masculino", "Argentina", "1996-03-01", "Zavalia 300", "Tigre", "Buenos Aires", "12345678", "12345678","aherrera@gmail.com",4, 1);
INSERT INTO Medicos (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo,id_especialidad, estado) VALUES ("41213434", "Valentina", "Gimenez", "Femenino", "Argentina", "1997-02-11", "Superi 4303", "Saavedra", "Buenos Aires", "12345678", "1133445566","vgimenez@gmail.com",4, 1);
INSERT INTO Medicos (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo,id_especialidad, estado) VALUES ("41213444", "Sofia", "Supacha", "Femenino", "Argentina", "1999-02-17", "Av Cazon 190", "Tigre", "Buenos Aires", "1133887755", "50321144","ssupacha@gmail.com",4, 1);
INSERT INTO Medicos (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo,id_especialidad, estado) VALUES ("27009999", "Martina", "Rodriguez", "Femenino", "Argentina", "1980-02-10", "Av Alvear 290", "Martinez", "Buenos Aires", "115548748", "4732-4444","mrodriguez123@gmail.com",7, 1);
INSERT INTO Medicos (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo,id_especialidad, estado) VALUES ("24000998", "Agustin", "Coronel", "Masculino", "Argentina", "1973-02-12", "Av Cazon 347", "Tigre", "Buenos Aires", "1236175538", "44224731","agustincoronela@gmail.com",7, 1);
INSERT INTO Medicos (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo,id_especialidad, estado) VALUES ("26443346", "Milagros", "Insua", "Femenino", "Argentina", "1985-02-11", "Av Cabildo 5010", "Belgrano", "Buenos Aires", "113132666", "43325000","minsua@gmail.com",7,1);
INSERT INTO Medicos (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo,id_especialidad, estado) VALUES ("41666777", "Francisco", "Gimenez", "Masculino", "Argentina", "1997-05-30", "Superi 4322", "Saavedra", "Buenos Aires", "12345338", "1133465566","fgimenez@gmail.com",7, 1);
INSERT INTO Medicos (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo,id_especialidad, estado) VALUES ("41213322", "Guido", "Rodriguez", "Masculino", "Argentina", "1999-04-26", "Av Cazon 1978", "Tigre", "Buenos Aires", "1133883355", "50322244","grodriguez@gmail.com",7,1);
INSERT INTO Medicos (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo,id_especialidad, estado) VALUES ("27009888", "Felicitas", "Pereira", "Femenino", "Argentina", "1980-02-08", "Av Alvear 2910", "Martinez", "Buenos Aires", "115668748", "4777-4444","fpereira@gmail.com",8, 1);
INSERT INTO Medicos (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo,id_especialidad, estado) VALUES ("24004322", "Martin", "Pugliese", "Masculino", "Argentina", "1973-05-12", "Av Cazon 3000", "Tigre", "Buenos Aires", "1236175778", "445524731","mpugliese@gmail.com",9, 1);
INSERT INTO Medicos (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo,id_especialidad, estado) VALUES ("26443333", "Joaquin", "Forster", "Femenino", "Argentina", "1985-03-09", "Av Cabildo 4200", "Belgrano", "Buenos Aires", "113138866", "42325000","jforster@gmail.com",10, 1);


create table Pacientes
(
dni VARCHAR(11) NOT NULL PRIMARY KEY,
nombre VARCHAR(25),
apellido VARCHAR(25),
sexo VARCHAR(25) ,
nacionalidad VARCHAR(25),
fechaNac DATE,
direccion VARCHAR(50),
localidad VARCHAR(50),
provincia VARCHAR(50),
telefono VARCHAR(25),
celular VARCHAR(25),
correo VARCHAR(25),
estado INT
);

INSERT INTO Pacientes (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado) VALUES ("32215514", "Pedro", "Ghidini", "Masculino", "Argentina", "1986-10-02", "Av Santa María de las Conchas 7300", "San Miguel", "Buenos Aires", "1136176018", "1136176018","ghidinipedro@gmail.com", 1);
INSERT INTO Pacientes (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado) VALUES ("3121242", "Juan", "Jannello", "Masculino", "Argentina", "1984-09-21", "Viamonte 200", "CABA", "Buenos Aires", "1136122222", "11333338","fjannello@gmail.com", 1);
INSERT INTO Pacientes (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado) VALUES ("27676447", "Gonzalo", "Rodriguez", "Masculino", "Argentina", "1979-02-11", "Av Cazon 190", "Tigre", "Buenos Aires", "1136173338", "1136173333","rodriguezt@gmail.com", 1);
INSERT INTO Pacientes (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado) VALUES ("22666533", "Gaston", "Perez", "Masculino", "Argentina", "1971-02-11", "Hipolito Yrigoyen 210", "Pacheco", "Buenos Aires", "112345433", "1236173333","perezgaston@hotmail.com", 1);
INSERT INTO Pacientes (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado) VALUES ("33676442", "Ramiro", "Rodriguez", "Masculino", "Argentina", "1987-02-11", "Av Cazon 190", "Tigre", "Buenos Aires", "1136173338", "1136173333","rodriguezt@gmail.com", 1);
INSERT INTO Pacientes (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado) VALUES ("20216447", "Marcos", "Herrera", "Masculino", "Argentina", "1920-03-01", "Zavalia 300", "Torcuato", "Buenos Aires", "12345678", "12345678","mherrera@gmail.com", 1);
INSERT INTO Pacientes (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado) VALUES ("47213434", "Valeria", "Gimenez", "Femenino", "Argentina", "2005-02-11", "Superi 4303", "Saavedra", "Buenos Aires", "12345678", "1133445566","vgimenez@gmail.com", 1);
INSERT INTO Pacientes (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado) VALUES ("33213444", "Sonia", "Supacha", "Femenino", "Argentina", "1987-02-17", "Av Cazon 190", "Tigre", "Buenos Aires", "1133887755", "50321144","ssupacha@gmail.com", 1);
INSERT INTO Pacientes (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado) VALUES ("25009999", "Marta", "Rodriguez", "Femenino", "Argentina", "1921-02-10", "Av Alvear 290", "Martinez", "Buenos Aires", "115548748", "4732-4444","marrodriguez123@gmail.com", 1);
INSERT INTO Pacientes (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado) VALUES ("28000998", "Alberto", "Coronel", "Masculino", "Argentina", "1979-02-12", "Av Cazon 347", "Los polvorines", "Buenos Aires", "1236175538", "44224731","allbertcoronela@gmail.com", 1);
INSERT INTO Pacientes (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado) VALUES ("37443346", "Mirtha", "Insua", "Femenino", "Argentina", "1992-02-11", "Av Cabildo 5010", "Belgrano", "Buenos Aires", "113132666", "43325000","minsua@gmail.com", 1);
INSERT INTO Pacientes (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado) VALUES ("24666777", "Facundo", "Gimenez", "Masculino", "Argentina", "1974-05-30", "Superi 4322", "Saavedra", "Buenos Aires", "12345338", "1133465566","fgimenez@gmail.com", 1);
INSERT INTO Pacientes (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado) VALUES ("27213322", "Guillermo", "Rodriguez", "Masculino", "Argentina", "1978-04-26", "Av Cazon 1978", "Pablo Nogues", "Buenos Aires", "1133883355", "50322244","grodriguez@gmail.com", 1);
INSERT INTO Pacientes (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado) VALUES ("21009888", "Flavia", "Pereira", "Femenino", "Argentina", "1969-02-08", "Av Alvear 2910", "Martinez", "Buenos Aires", "115668748", "4777-4444","fpereira@gmail.com", 1);
INSERT INTO Pacientes (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado) VALUES ("28004322", "Mario", "Pugliese", "Masculino", "Argentina", "1979-05-12", "Av Cazon 3000", "Tigre", "Buenos Aires", "1236175778", "445524731","mpugliese@gmail.com", 1);
INSERT INTO Pacientes (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado) VALUES ("20443333", "Javier", "Forster", "Femenino", "Argentina", "1920-03-09", "Av Cabildo 4200", "Belgrano", "Buenos Aires", "113138866", "42325000","jforster@gmail.com", 1);

create table Estados
(
ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
descripcion VARCHAR(25)
);

INSERT into Estados (Descripcion) VALUES ("LIBRE");
INSERT into Estados (Descripcion) VALUES ("OCUPADO");
INSERT into Estados (Descripcion) VALUES ("AUSENTE");
INSERT into Estados (Descripcion) VALUES ("PRESENTE");

create table DisponibilidadxMedico
(
ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
DNI_Medico VARCHAR(25) NOT NULL,
FOREIGN KEY(DNI_Medico) REFERENCES Medicos(dni),
dia INT
);

/*ESTA TABLA CREO QUE QUEDA AL PEDO */
create table EspecialidadesxMedico
(
ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
DNI_Medico VARCHAR(25) NOT NULL,
ID_Especialidad INT NOT NULL,
FOREIGN KEY(DNI_Medico) REFERENCES Medicos(dni),
FOREIGN KEY(ID_Especialidad) REFERENCES  Especialidades(ID)
);

INSERT into EspecialidadesxMedico (DNI_Medico,ID_Especialidad) VALUES ("41215514",1);
INSERT into EspecialidadesxMedico (DNI_Medico,ID_Especialidad) VALUES ("4121242",1); 
INSERT into EspecialidadesxMedico (DNI_Medico,ID_Especialidad) VALUES ("40676447",4);
INSERT into EspecialidadesxMedico (DNI_Medico,ID_Especialidad) VALUES ("42666533",5);
INSERT into EspecialidadesxMedico (DNI_Medico,ID_Especialidad) VALUES ("40676442",2);
INSERT into EspecialidadesxMedico (DNI_Medico,ID_Especialidad) VALUES ("40216447",1);
INSERT into EspecialidadesxMedico (DNI_Medico,ID_Especialidad) VALUES ("41213434",6); 
INSERT into EspecialidadesxMedico (DNI_Medico,ID_Especialidad) VALUES ("41213444",2); 
INSERT into EspecialidadesxMedico (DNI_Medico,ID_Especialidad) VALUES ("27009999",1); 
INSERT into EspecialidadesxMedico (DNI_Medico,ID_Especialidad) VALUES ("24000998",5); 
INSERT into EspecialidadesxMedico (DNI_Medico,ID_Especialidad) VALUES ("26443346",3); 
INSERT into EspecialidadesxMedico (DNI_Medico,ID_Especialidad) VALUES ("41666777",7); 
INSERT into EspecialidadesxMedico (DNI_Medico,ID_Especialidad) VALUES ("41213322",8); 
INSERT into EspecialidadesxMedico (DNI_Medico,ID_Especialidad) VALUES ("27009888",9); 
INSERT into EspecialidadesxMedico (DNI_Medico,ID_Especialidad) VALUES ("24004322",10); 
INSERT into EspecialidadesxMedico (DNI_Medico,ID_Especialidad) VALUES ("26443333",11); 


create table Turnos
(
ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
DNI_Paciente VARCHAR(25) NOT NULL,
DNI_Medico VARCHAR(25) NOT NULL,
ID_Estado INT NOT NULL,
ID_Especialidad INT NOT NULL,
FOREIGN KEY(DNI_Paciente) REFERENCES Pacientes(dni),
FOREIGN KEY(DNI_Medico) REFERENCES Medicos(dni),
FOREIGN KEY(ID_Estado) REFERENCES  Estados(ID),
FOREIGN KEY(ID_Especialidad) REFERENCES  Especialidades(ID),
fecha DATE,
hora time,
observacion VARCHAR(200)
);
INSERT INTO TURNOS (dni_paciente, dni_medico, id_estado, id_especialidad, fecha, hora, observacion) VALUES ("3121242", "4121242", 1, 2, "2022-07-04", "11:45:30", "TODO FLAMA");

create table TiposUsuario
(
ID INT NOT NULL PRIMARY KEY auto_increment,
DESCRIPCION VARCHAR(25)
);
insert into TiposUsuario (Descripcion) values ("Administrativo");
insert into TiposUsuario (Descripcion) values ("Medico");

create table Usuarios
(
ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
NOMBRE_USUARIO VARCHAR(200) NOT NULL,
CONTRASENA VARCHAR(30) NOT NULL,
estado boolean,
TIPO_USUARIO INT NOT NULL,
FOREIGN KEY (TIPO_USUARIO) REFERENCES TiposUsuario(ID),
dni varchar(11) NULL,
FOREIGN KEY (dni) REFERENCES Medicos(DNI)
);

insert into Usuarios (NOMBRE_USUARIO, CONTRASENA, TIPO_USUARIO, dni) values ("FacuJannello", "1234", 2, "4121242");
insert into Usuarios (NOMBRE_USUARIO, CONTRASENA, TIPO_USUARIO, dni) values ("AleHerrera", "1234", 2, "40216447");
insert into Usuarios (NOMBRE_USUARIO, CONTRASENA, TIPO_USUARIO) values ("MateoGhidini", "1234", 1);
insert into Usuarios (NOMBRE_USUARIO, CONTRASENA, TIPO_USUARIO) values ("PabloCaero", "1234", 1);
insert into Usuarios (NOMBRE_USUARIO, CONTRASENA, TIPO_USUARIO) values ("DiameDevia", "1234", 1);

INSERT INTO DisponibilidadxMedico (DNI_medico, dia) VALUES ('24000998',1);
INSERT INTO DisponibilidadxMedico (DNI_medico, dia) VALUES ('24004322',2);
INSERT INTO DisponibilidadxMedico (DNI_medico, dia) VALUES ('26443333',3);
INSERT INTO DisponibilidadxMedico (DNI_medico, dia) VALUES ('26443346',4);
INSERT INTO DisponibilidadxMedico (DNI_medico, dia) VALUES ('27009888',5);
INSERT INTO DisponibilidadxMedico (DNI_medico, dia) VALUES ('27009999',5);
INSERT INTO DisponibilidadxMedico (DNI_medico, dia) VALUES ('40216447',6);
INSERT INTO DisponibilidadxMedico (DNI_medico, dia) VALUES ('40676442',0);
INSERT INTO DisponibilidadxMedico (DNI_medico, dia) VALUES ('40676447',6);
INSERT INTO DisponibilidadxMedico (DNI_medico, dia) VALUES ('4121242',4);
INSERT INTO DisponibilidadxMedico (DNI_medico, dia) VALUES ('41213322',2);
INSERT INTO DisponibilidadxMedico (DNI_medico, dia) VALUES ('41213434',1);
INSERT INTO DisponibilidadxMedico (DNI_medico, dia) VALUES ('41213444',4);
INSERT INTO DisponibilidadxMedico (DNI_medico, dia) VALUES ('41215514',6);
INSERT INTO DisponibilidadxMedico (DNI_medico, dia) VALUES ('41666777',1);
INSERT INTO DisponibilidadxMedico (DNI_medico, dia) VALUES ('42666533',4);

INSERT INTO TURNOS (dni_paciente, dni_medico, id_estado, id_especialidad, fecha, hora, observacion) VALUES ('25009999','40676442','2', '1', '2022-07-17', '11:00', 'sin observacion');
INSERT INTO TURNOS (dni_paciente, dni_medico, id_estado, id_especialidad, fecha, hora, observacion) VALUES ('25009999','24004322','2', '9', '2022-07-19', '13:00', 'sin observacion');
INSERT INTO TURNOS (dni_paciente, dni_medico, id_estado, id_especialidad, fecha, hora, observacion) VALUES ('33213444','26443333','2', '10', '2022-07-27', '15:00', 'sin observacion');
INSERT INTO TURNOS (dni_paciente, dni_medico, id_estado, id_especialidad, fecha, hora, observacion) VALUES ('33676442','26443346','2', '7', '2022-07-21', '09:00', 'sin observacion');
INSERT INTO TURNOS (dni_paciente, dni_medico, id_estado, id_especialidad, fecha, hora, observacion) VALUES ('22666533','27009888','2', '8', '2022-08-19', '11:00', 'sin observacion');
INSERT INTO TURNOS (dni_paciente, dni_medico, id_estado, id_especialidad, fecha, hora, observacion) VALUES ('3121242','27009999','2', '7', '2022-07-29', '08:00', 'sin observacion');
INSERT INTO TURNOS (dni_paciente, dni_medico, id_estado, id_especialidad, fecha, hora, observacion) VALUES ('27676447','40216447','2', '4', '2022-07-30', '11:00', 'sin observacion');
INSERT INTO TURNOS (dni_paciente, dni_medico, id_estado, id_especialidad, fecha, hora, observacion) VALUES ('37443346','40676442','2', '1', '2022-07-17', '08:00', 'sin observacion');
INSERT INTO TURNOS (dni_paciente, dni_medico, id_estado, id_especialidad, fecha, hora, observacion) VALUES ('27213322','40676447','2', '2', '2022-09-17', '13:00', 'sin observacion');
INSERT INTO TURNOS (dni_paciente, dni_medico, id_estado, id_especialidad, fecha, hora, observacion) VALUES ('47213434','4121242','2', '2', '2022-08-18', '11:00', 'sin observacion');
INSERT INTO TURNOS (dni_paciente, dni_medico, id_estado, id_especialidad, fecha, hora, observacion) VALUES ('25009999','41213322','2', '7', '2022-09-06', '16:00', 'sin observacion');
INSERT INTO TURNOS (dni_paciente, dni_medico, id_estado, id_especialidad, fecha, hora, observacion) VALUES ('47213434','41213434','2', '4', '2022-07-25', '10:00', 'sin observacion');
INSERT INTO TURNOS (dni_paciente, dni_medico, id_estado, id_especialidad, fecha, hora, observacion) VALUES ('33213444','41213444','2', '4', '2022-09-22', '07:00', 'sin observacion');
INSERT INTO TURNOS (dni_paciente, dni_medico, id_estado, id_especialidad, fecha, hora, observacion) VALUES ('24666777','41215514','2', '1', '2022-09-10', '10:00', 'sin observacion');
INSERT INTO TURNOS (dni_paciente, dni_medico, id_estado, id_especialidad, fecha, hora, observacion) VALUES ('27676447','42666533','2', '5', '2022-10-20', '12:00', 'sin observacion');


