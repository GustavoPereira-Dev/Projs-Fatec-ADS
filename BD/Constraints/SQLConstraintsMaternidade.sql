CREATE DATABASE Maternidade;
USE Maternidade;

CREATE TABLE Mae(
	ID_Mae int IDENTITY(1001,1),
	Nome varchar(60),
	Logradouro_Endereco varchar(100),
	Numero_Endereco int CHECK(Numero_Endereco > 0),
	CEP_Endereco char(8) CHECK(CEP_Endereco > 0),
	Complemento_Endereco varchar(200),
	Telefone char(10) CHECK(LEN(Telefone) = 10),
	Data_Nasc date
	PRIMARY KEY(ID_Mae)
);
GO
CREATE TABLE Medico(
	CRM_Numero int,
	CRM_UF char(2),
	Nome varchar(60),
	Telefone_Celular char(11) UNIQUE CHECK(LEN(Telefone_Celular) = 11),
	Especialidade varchar(30)
	PRIMARY KEY(CRM_Numero, CRM_UF)
);
GO
CREATE TABLE Bebe(
	ID_Bebe int IDENTITY,
	Nome varchar(60),
	Data_nasc date DEFAULT GETDATE(),
	Altura decimal(17,2) CHECK(Altura > 0),
	Peso decimal(4,3) CHECK(Peso > 0),
	MaeID_Mae int
	PRIMARY KEY(ID_Bebe)
	FOREIGN KEY(MaeID_Mae) REFERENCES Mae(ID_Mae)
);
GO
CREATE TABLE Bebe_Medico(
	BebeID_Bebe int,
	MedicoCRM_Numero int,
	MedicoCRM_UF char(2)
	PRIMARY KEY (BebeID_Bebe, MedicoCRM_Numero, MedicoCRM_UF)
	FOREIGN KEY(BebeID_Bebe) REFERENCES Bebe(ID_Bebe),
	FOREIGN KEY(MedicoCRM_Numero, MedicoCRM_UF) REFERENCES Medico(CRM_Numero, CRM_UF)
);