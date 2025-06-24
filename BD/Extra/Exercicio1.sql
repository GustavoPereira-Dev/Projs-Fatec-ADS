CREATE DATABASE Escola;
USE Escola;

CREATE TABLE Aluno(
	RA int IDENTITY(12345, 1),
	Nome varchar(60),
	Sobrenome varchar(40),
	Rua varchar(80),
	N int,
	Bairro varchar(50),
	CEP char(8),
	Telefone char(9)
	PRIMARY KEY(RA)
);

CREATE TABLE Cursos(
	Codigo int IDENTITY,
	Nome varchar(60),
	CargaHoraria int,
	Turno varchar(10)
	PRIMARY KEY(Codigo)
);

CREATE TABLE Disciplinas(
	Codigo int IDENTITY,
	Nome varchar(60),
	CargaHoraria int,
	Turno varchar(10),
	Semestre int,
	PRIMARY KEY(Codigo)
);

INSERT INTO Aluno (Nome, Sobrenome, Rua, N, Bairro, CEP, Telefone) 
VALUES ('José', 'Silva', 'Almirante Noronha', 236,	'Jardim São Paulo', 1589000, 69875287),
	   ('Ana', 'Maria Bastos',	'Anhaia',	1568, 'Barra Funda', 3569000, 25698526),
       ('Mario', 'Santos', 'XV de Novembro', 1841, 'Centro', 1020030, NULL),
       ('Marcia', 'Neves', 'Voluntários da Patria',	225, 'Santana',	2785090, 78964152);


INSERT INTO Cursos (Nome, CargaHoraria,	Turno)
VALUES ('Informática',	2800,	'Tarde'),
	   ('Informática',	2800,	'Noite'),
	   ('Logística',	2650,	'Tarde'),
	   ('Logística',	2650,	'Noite'),
	   ('Plásticos',	2500,	'Tarde'),
	   ('Plásticos',	2500,	'Noite');

INSERT INTO Disciplinas(Nome, CargaHoraria, Turno, Semestre)
VALUES
('Informática', 4, 'Tarde', 1),
('Informática', 4, 'Noite', 1),
('Quimica', 4, 'Tarde', 1),
('Quimica', 4, 'Noite', 1),
('Banco de Dados I', 2, 'Tarde', 3),
('Banco de Dados I', 2, 'Noite', 3),
('Estrutura de Dados', 4, 'Tarde', 4),
('Estrutura de Dados', 4, 'Noite', 4);

SELECT Nome + ' ' + Sobrenome AS NomeCompleto FROM Aluno;
SELECT Rua + ' ' + CAST(N AS VARCHAR) + ' ' + Bairro + ' ' + CEP AS Endereco FROM Aluno WHERE Telefone IS NULL;
SELECT Telefone FROM Aluno WHERE RA = 12348;
SELECT Nome, Turno FROM Cursos WHERE CargaHoraria > 2800;
SELECT Semestre FROM Disciplinas WHERE Nome = 'Banco de Dados I' AND Turno = 'Noite';