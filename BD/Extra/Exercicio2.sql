CREATE DATABASE Concersionaria;
USE Concersionaria;

CREATE TABLE Carro(
	Placa char(7),
	Marca varchar(50),
	Modelo varchar(50),
	Cor varchar(30),
	Ano int
	PRIMARY KEY(Placa)
);

CREATE TABLE Pecas(
	Codigo int IDENTITY,
	Nome varchar(50),
	Valor DECIMAL(8,2)
	PRIMARY KEY(Codigo)
);

CREATE TABLE Cliente(
	Nome varchar(100),
	Logradouro varchar(100),
	N int,
	Bairro varchar(50),
	Telefone char(9),
	Carro char(7)
	PRIMARY KEY(Carro)
	FOREIGN KEY(Carro) REFERENCE Carro(Placa)
);

CREATE TABLE Servico(
	Carro char(7),
	Peca int,
	Quantidade int,
	Valor DECIMAL(8, 2)
	Data DATE,
	PRIMARY KEY (Carro, Peca, Data),
	FOREIGN KEY(Carro) REFERENCES Carro(Placa),
	FOREIGN KEY(Peca) REFERENCES Peca(Codigo)
);

INSERT INTO Carro (Placa,	Marca,	Modelo,	Cor,	Ano) 
VALUES ('AFT9087',	'VW',	'Gol',	'Preto',	2007),
('DXO9876',	'Ford',	'Ka',	'Azul',	2000),
('EGT4631',	'Renault',	'Clio',	'Verde',	2004),
('LKM7380',	'Fiat',	'Palio',	'Prata',	1997),
('BCD7521',	'Ford',	'Fiesta',	'Preto',	1999);


INSERT INTO Pecas (Código,	Nome,	Valor)
VALUES (1,	'Vela',	70),
(2,	'Correia Dentada',	125),
(3,	'Trambulador',	90),
(4,	'Filtro de Ar',	30);

INSERT INTO Cliente (Nome,	Logradouro,	N,	Bairro,	Telefone,	Carro)
VALUES ('João Alves',	'R. Pereira Barreto',	1258,	'Jd. Oliveiras',	2154-9658,	DXO9876),
('Ana Maria',	'R. 7 de Setembro',	259,	'Centro',	9658-8541,	LKM7380),
('Clara Oliveira',	'Av. Nações Unidas',	10254,	'Pinheiros',	2458-9658,	EGT4631),
('José Simões',	'R. XV de Novembro',	36,	'Água Branca',	7895-2459,	BCD7521),
('Paula Rocha',	'R. Anhaia',	548,	'Barra Funda',	6958-2548,	AFT9087);

INSERT INTO Servico (Carro,	Peca,	Quantidade,	Valor,	Data)
('DXO9876',	1,	4,	280,	'2020-08-01'),
('DXO9876',	4,	1,	30,	'2020-08-01'),
('EGT4631',	3,	1,	90,	'2020-08-02'),
('DXO9876',	2,	1,	125,	'2020-08-07');

-- Subqueries
SELECT Telefone FROM Cliente WHERE Carro IN (SELECT Placa FROM Carro WHERE Marca LIKE 'Ka' AND Cor LIKE 'Azul');
SELECT Logradouro + ' ' + N + ' ' + Bairro FROM Cliente WHERE Carro IN (SELECT Carro FROM Servico WHERE Data LIKE '2020-08-02');


-- Queries
SELECT Placa FROM Carro WHERE Ano < 2001;
SELECT Marca + ' ' + Modelo + ' ' + Cor AS Veiculo FROM Carro WHERE Ano > 2005;
SELECT Codigo, Nome FROM Pecas WHERE Valor < 80.00;


