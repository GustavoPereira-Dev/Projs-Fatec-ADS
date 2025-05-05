CREATE DATABASE Clinica;

USE Clinica;

CREATE TABLE Paciente (
  Num_Beneficiario integer,
  Nome varchar(100),
  Lograouro varchar(200),
  Numero integer,
  CEP char(8),
  Complemento varchar(255),
  Telefone varchar(11)
  primary key(Num_Beneficiario)
);
GO
CREATE TABLE Especialidade(
  ID integer,
  Especialidade varchar(100)
  primary key(ID)
);
GO
CREATE TABLE Medico(
  Codigo integer,
  Nome varchar(100),
  Lograouro varchar(200),
  Numero integer,
  CEP char(8),
  Complemento varchar(255)
  Contato varchar(11),
  EspecialidadeID integer(10)
  primary key(Codigo)
  foreign key(EspecialidadeID) references Especialidade(ID)
);
GO
CREATE TABLE Consulta(
  PacienteNum_Beneficiario integer,
  MedicoCodigo integer,
  Data_hora timestamp,
  Observacao
  primary key(PacienteNum_Beneficiario, MedicoCodigo, Data_hora)
  foreign key(Data_hora)
);

INSERT INTO Paciente VALUES(99901, "Washington Silva", "R. Anhaia", 150, 02345000, "Casa", "922229999"),
(99902, "Luis Ricardo",  "R. Voluntários da Pátria", 2251, "03254010", "Bloco B. Apto 25", "923450987"),
(99903, "Maria Elisa", "Av. Aguia de Haia", 1188, "06987020", "Apto 1208", "912348765"),
(99904, "José Araújo", "R. XV de Novembro", 18, "03678000", "Casa", "945674312"),
(99905, "Joana Paula", "R. 7 de Abril", 97, "01214000", "Conjunto 3 - Apto 801", "912095674")
GO
INSERT INTO Especialidade VALUES (1, "Otorrinolaringologista"),
(2, "Urologista"), (3, "Geriatra"), (4, "Pediatra")

INSERT INTO Medico VALUES (100001, "Ana Paula", "R. 7 de Setembro", 256, "03698000", "Casa", "915689456", 1),
(100002, "Maria Aparecida", "Av. Brasil", 32, "03145070", "Casa", "923235454", 1),
(100003, "Lucas Borges", "Av. do Estado", 3210, "05241000", "Apto 205", "963698585", 2),
(100004, "Gabriel Oliveira", "Av. Dom Helder Camara", 350, "03145000", "Apto 602", "932458745", 3)

INSERT INTO Consulta VALUES(99901, 100002, "2021-09-04 13:20", "Infecção Urina"),
(99902, 100003, "2021-09-04 13:15", "Gripe"),
(99901, 100001, "2021-09-04 12:30", "Infecção Garganta")


ALTER TABLE Medico(
  dia_atendimento varchar(10)
);

UPDATE Medico SET dia_atendimento = "2ª feira" WHERE Codigo = 100001;
UPDATE Medico SET dia_atendimento = "4ª feira" WHERE Codigo = 100002;
UPDATE Medico SET dia_atendimento = "2ª feira" WHERE Codigo = 100003;
UPDATE Medico SET dia_atendimento = "5ª feira" WHERE Codigo = 100004;

DELETE Especialidade WHERE ID = 4;

UPDATE Medico SET Lograouro = "Av. Bras Leme", Numero = 876, Complemento = "Apto 504", CEP = "02122000" WHERE ID = 100003

ALTER TABLE Medico(
  Observacao varchar(200);
);






