CREATE DATABASE Locacao;
USE Locacao;

CREATE TABLE Filme(
	id int NOT NULL,
	titulo varchar(40) NOT NULL,
	ano int NOT NULL CHECK(ano <= 2021)
	PRIMARY KEY(id)
);

CREATE TABLE Estrela(
	id int NOT NULL,
	nome varchar(50) NOT NULL
	PRIMARY KEY(id)
);

CREATE TABLE Filme_Estrela(
	Filmeid int NOT NULL,
	Estrelaid int NOT NULL
	PRIMARY KEY(Filmeid, Estrelaid)
	FOREIGN KEY(Filmeid) REFERENCES Filme(id),
	FOREIGN KEY(Estrelaid) REFERENCES Estrela(id)
);

CREATE TABLE DVD(
	num int NOT NULL,
	data_fabricacao date NOT NULL CHECK(data_fabricacao < GETDATE()),
	Filmeid int NOT NULL
	PRIMARY KEY(num)
);

CREATE TABLE Cliente(
	num_cadastro int,
	nome varchar(70) NOT NULL,
	logradouro varchar(150) NOT NULL,
	num int NOT NULL CHECK(num > 0),
	cep char(8) CHECK(LEN(cep) = 8)
	PRIMARY KEY(num_cadastro)
);

CREATE TABLE Locacao(
	DVDnum int NOT NULL,
	Clientenum_cadastro int NOT NULL,
	data_locacao date NOT NULL DEFAULT GETDATE(),
	data_devolucao date NOT NULL,
	valor decimal(7, 2) NOT NULL CHECK(valor > 00000.00)
	PRIMARY KEY(DVDnum, Clientenum_cadastro, data_locacao),
	FOREIGN KEY(DVDnum) REFERENCES DVD(num),
	FOREIGN KEY(Clientenum_cadastro) REFERENCES Cliente(num_cadastro),
	CONSTRAINT chk_dtloc_dtdevloc CHECK(data_devolucao > data_locacao)
);

ALTER TABLE Estrela
ADD nome_real varchar(50)

ALTER TABLE Filme
ALTER COLUMN titulo varchar(80) NOT NULL

INSERT INTO Filme VALUES
(1001, "Whiplash", 2015),
(1002, "Birdman", 2015),
(1003, "Interestelar", 2014),
(1004, "A Culpa é das estrelas", 2014),
(1005, "Alexandre e o Dia Terrível, Horrível, Espantoso e Horroroso", 2014),
(1006, "Sing", 2016)

INSERT INTO Estrela VALUES
(9901, "Michael Keaton Michael", "John Douglas"),
(9902, "Emma Stone", "Emily Jean Stone"),
(9903, "Miles Teller", NULL),
(9904, "Steve Carell", "Steven John Carell"),
(9905, "Jennifer Garner", "Jennifer Anne Garner")

INSERT INTO Filme_Estrela VALUES
(1002, 9901),
(1002, 9902),
(1001, 9903),
(1005, 9904),
(1005, 9905)

INSERT INTO DVD VALUES
(10001, '2020-12-02', 1001),
(10002, '2019-10-18', 1002),
(10003, '2020-04-03', 1003),
(10004, '2020-12-02', 1001),
(10005, '2019-10-18', 1004),
(10006, '2020-04-03', 1002),
(10007, '2020-12-02', 1005),
(10008, '2019-10-18', 1002),
(10009, '2020-04-03', 1003)

INSERT INTO Cliente VALUES
(5501, "Matilde Luz", "Rua Síria", 150, "03086040"),
(5502, "Carlos Carreiro", "Rua Bartolomeu Aires", 1250, "04419110"),
(5503, "Daniel Ramalho", "Rua Itajutiba", 169, NULL),
(5504, "Roberta Bento", "Rua Jayme Von Rosenburg", 36, NULL),
(5505, "Rosa Cerqueira", "Rua Arnaldo Simões Pinto", 235, "02917110")

INSERT INTO Locacao VALUES
(10001, 5502, '2021-02-18', '2021-02-21', 3.50),
(10009, 5502, '2021-02-18', '2021-02-21', 3.50),
(10002, 5503, '2021-02-18', '2021-02-19', 3.50),
(10002, 5505, '2021-02-20', '2021-02-23', 3.00),
(10004, 5505, '2021-02-20', '2021-02-23', 3.00),
(10005, 5505, '2021-02-20', '2021-02-23', 3.00),
(10001, 5501, '2021-02-24', '2021-02-26', 3.50),
(10008, 5501, '2021-02-24', '2021-02-26', 3.50)

-- Operações com Dados

-- Os CEP dos clientes 5503 e 5504 são 08411150 e 02918190 respectivamente
UPDATE Cliente SET CEP = "08411150" WHERE num_cadastro = 5503;
UPDATE Cliente SET CEP = "02918190" WHERE num_cadastro = 5504;

-- A locação de 2021-02-18 do cliente 5502 teve o valor de 3.25 para cada DVD alugado
UPDATE Locacao SET valor = 3.25 WHERE Clientenum_cadastro = 5502 AND data_locacao = '2021-02-18';

-- A locação de 2021-02-24 do cliente 5501 teve o valor de 3.10 para cada DVD alugado
UPDATE Locacao SET valor = 3.10 WHERE Clientenum_cadastro = 5501 AND data_locacao = '2021-02-24';

-- O DVD 10005 foi fabricado em 2019-07-14
UPDATE DVD SET data_fabricacao = '2019-07-14' WHERE num = 10005;

-- O nome real de Miles Teller é Miles Alexander Teller
UPDATE Estrela SET nome_real = "Miles Alexander Teller" WHERE nome = "Miles Teller";

-- O filme Sing não tem DVD cadastrado e deve ser excluído
DELETE Filme WHERE id = 1006;


-- Consultas

-- 1) Fazer um select que retorne os nomes dos filmes de 2014
SELECT titulo FROM Filme WHERE ano = 2014;

-- 2) Fazer um select que retorne o id e o ano do filme Birdman
SELECT id, ano FROM Filme WHERE titulo = "Birdman";

-- 3) Fazer um select que retorne o id e o ano do filme que tem o nome terminado por plash
SELECT id, ano FROM Filme WHERE titulo LIKE "%plash"; 

-- 4) Fazer um select que retorne o id, o nome e o nome_real da estrela cujo nome começa com Steve
SELECT id, nome, nome_real FROM Estrela WHERE nome LIKE "Steve%";

-- 5) Fazer um select que retorne FilmeId e a data_fabricação em formato (DD/MM/YYYY) (apelidar de fab) dos filmes fabricados a partir de 01-01-2020
SELECT FilmeId, CONVERT(VARCHAR, data_fabricacao, 103) AS fab FROM DVD WHERE data_fabricacao >= '01-01-2020'; 

-- 6) Fazer um select que retorne DVDnum, data_locacao, data_devolucao, valor e valor com multa de acréscimo de 2.00 da locação do cliente 5505
SELECT DVDnum, data_locacao, data_devolucao, valor, valor + 2.00 AS multa FROM Locacao WHERE Clientenum_cadastro = 5505

-- 7) Fazer um select que retorne Logradouro, num e CEP de Matilde Luz
SELECT logradouro, num, cep FROM Cliente WHERE nome = "Matilde Luz";

-- 8) Fazer um select que retorne Nome real de Michael Keaton
SELECT nome_real FROM Estrela WHERE nome = "Michael Keaton";

-- 9) Fazer um select que retorne o num_cadastro, o nome e o endereço completo, concatenando (logradouro, numero e CEP), apelido end_comp, dos clientes cujo ID é maior ou igual 5503
SELECT num_cadastro, nome, logradouro + " " + CAST(num AS VARCHAR(10)) + " " + CEP AS endereco_completo FROM Cliente WHERE num_cadastro >= 5503;
