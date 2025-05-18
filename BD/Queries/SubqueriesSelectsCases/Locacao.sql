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
 

-- Novas consultas - Operações com Dados (Subqueries e Selects Cases)

-- 1) Fazer uma consulta que retorne ID, Ano, nome do Filme (Caso o nome do filme tenha
-- mais de 10 caracteres, para caber no campo da tela, mostrar os 10 primeiros
-- caracteres, seguidos de reticências ...) dos filmes cujos DVDs foram fabricados depois
-- de 01/01/2020
SELECT id, ano,
	CASE WHEN LEN(titulo) > 10 THEN SUBSTRING(titulo, 1 , 10) + '...'
	ELSE titulo
	END AS titulo
FROM Filme WHERE id IN (SELECT Filmeid FROM DVD WHERE data_fabricacao > '01/01/2020');

-- 2) Fazer uma consulta que retorne num, data_fabricacao, qtd_meses_desde_fabricacao
-- (Quantos meses desde que o dvd foi fabricado até hoje) do filme Interestelar
SELECT num, data_fabricacao, DATEDIFF(MONTH, data_fabricacao, GETDATE())  AS qtd_meses_desde_fabricacao FROM DVD WHERE Filmeid IN (SELECT id FROM Filme WHERE titulo = "Interestelar");

-- 3) Fazer uma consulta que retorne num_dvd, data_locacao, data_devolucao,
-- dias_alugado(Total de dias que o dvd ficou alugado) e valor das locações da cliente que
-- tem, no nome, o termo Rosa
SELECT DVDnum, data_locacao, data_devolucao, DATEDIFF(DAY, data_locacao, data_devolucao) AS dias_alugado, valor FROM Locacao WHERE Clientenum_cadastro 
IN (SELECT num_cadastro FROM Cliente WHERE nome LIKE "%Rosa%")

-- 4) Nome, endereço_completo (logradouro e número concatenados), cep (formato
-- XXXXX-XXX) dos clientes que alugaram DVD de num 10002.
SELECT nome, logradouro + CONVERT(varchar(10), num) AS endereco_completo, SUBSTRING(cep, 1, 5) + '-' + SUBSTRING(cep, 5, 3) AS cep FROM Cliente WHERE num_cadastro IN
(SELECT Clientenum_cadastro FROM Locacao WHERE DVDnum = 10002);