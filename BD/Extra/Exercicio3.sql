CREATE DATABASE Hospital;
USE Hospital;

CREATE TABLE Pacientes(
	CPF char(11),
	Nome varchar(100)
	Rua varchar(50),
	N int,
	Bairro varchar(50),
	Telefone char(9),
	Data_Nasc DATE
	PRIMARY KEY(CPF)
);

CREATE TABLE Medico(
	Codigo int IDENTITY,
	Nome varchar(100),
	Especialidade VARCHAR(50)
	PRIMARY KEY(Codigo)
);

CREATE TABLE Prontuario(
	Data DATE,
	CPFPaciente char(11),
	CodigoMedico int,
	Diagnostico varchar(50),
	Medicamento varchar(70),
	PRIMARY KEY (Data, CPFPaciente, CodigoMedico),
	FOREIGN KEY(CPFPaciente) REFERENCES Pacientes(CPF),
	FOREIGN KEY(CodigoMedico) REFERENCES Medico(Codigo)
);

INSERT INTO Pacientes
VALUES (35454562890,	'José Rubens',	'Campos Salles',	2750,	'Centro',	21450998,	'1954-10-18'),
(29865439810,	'Ana Claudia',	'Sete de Setembro',	178,	'Centro',	97382764,	'1960-05-29'),
(82176534800,	'Marcos Aurélio',	'Timóteo Penteado',	236,	'Vila Galvão',	68172651,	'1980-09-24'),
(12386758770,	'Maria Rita',	'Castello Branco',	7765,	'Vila Rosália',	NULL,	'1975-03-30'),
(92173458910,	'Joana de Souza',	'XV de Novembro',	298,	'Centro',	21276578,	'1944-04-24');

INSERT INTO Medico (Nome, Especialidade)
VALUES ('Wilson Cesar',	'Pediatra'),
('Marcia Matos',	'Geriatra'),
('Carolina Oliveira',	'Ortopedista'),
('Vinicius Araujo',	'Clínico Geral');

INSERT INTO Prontuario
VALUES ('2020-09-10',	'35454562890',	2,	'Reumatismo',	'Celebra'),
('2020-09-10',	'92173458910',	2,	'Renite Alérgica',	'Allegra'),
('2020-09-12',	'29865439810',	1,	'Inflamação de garganta',	'Nimesulida'),
('2020-09-13',	'35454562890',	2,	'H1N1',	'Tamiflu'),
('2020-09-15',	'82176534800',	4,	'Gripe',	'Resprin'),
('2020-09-15',	'12386758770',	3,	'Braço Quebrado',	'Dorflex + Gesso');

-- Queries
SELECT Rua + ' ' + N + ' ' + Bairro AS Endereco FROM Pacientes WHERE DATEDIFF(YEAR, Data_Nasc, GETDATE()) > 50;
SELECT Especialidade FROM Medico WHERE Nome LIKE 'Carolina Oliveira';
SELECT Medicamento FROM Prontuario WHERE Diagnostico LIKE 'Reumatismo'


-- Subqueries
SELECT Diagnostico, Medicamento FROM Prontuario WHERE CPFPaciente IN (SELECT CPF FROM Paciente WHERE Nome LIKE 'Jose Rubens');
SELECT Nome, 
			CASE WHEN LEN(Especialidade) > 3
				THEN SUBSTRING(Especialidade, 1 , 3)
				ELSE Especialidade
			END AS Especialidade
FROM Medico WHERE Codigo IN (SELECT CodigoMedico WHERE CPF LIKE '35454562890');
SELECT SUBSTRING(CPF,1, 3) + ' ' + SUBSTRING(CPF, 4 , 6) + ' ' + SUBSTRING(CPF, 7, 9) + ' ' + SUBSTRING(CPF, 12, 3),
Nome, Rua + ' ' + N + ' ' + Bairro AS Endereco, 
	CASE WHEN Telefone IS NULL
		THEN '-'
		ELSE Telefone
	END AS Telefone
FROM Paciente WHERE CPF IN (SELECT CPFPaciente FROM Prontuario WHERE CodigoMedico = 4);

