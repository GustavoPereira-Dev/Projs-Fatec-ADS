CREATE DATABASE Entrega;
USE Entrega;

CREATE TABLE Fornecedores(
	Codigo int IDENTITY(1001, 1),
	Nome varchar(50),
	Atividade varchar(50),
	Telefone char(9)
	PRIMARY KEY(Codigo)
);

CREATE TABLE Produto(
	Codigo int IDENTITY,
	Nome varchar(100),
	Valor_Unitario DECIMAL(8,2),
	Quantidade_Estoque int,
	Descricao varchar(150),
	Codigo_Fornecedor int
	PRIMARY KEY(Codigo)
	FOREIGN KEY(Codigo_Fornecedor) REFERENCES Fornecedores(Codigo)
);

CREATE TABLE Cliente(
	Codigo int IDENTITY(33601, 1),
	Nome varchar(100),
	Logradouro varchar(100),
	Numero int,
	Telefone char(9),
	Data_Nasc date
	PRIMARY KEY(Codigo)
);

CREATE TABLE Pedido(
	Codigo int,
	Codigo_Cliente int,
	Codigo_Produto int,
	Quantidade int,
	Previsao_Entrega date,
	PRIMARY KEY(Codigo, Codigo_Cliente, Codigo_Produto),
	FOREIGN KEY(Codigo_Cliente) REFERENCES Cliente(Codigo),
	FOREIGN KEY(Codigo_Produto) REFERENCES Produto(Codigo)
);

INSERT INTO Fornecedores (Nome,	Atividade,	Telefone)
VALUES ('Estrela'	'Brinquedo',	41525898),
	   ('Lacta',	'Chocolate',	42698596),
	   ('Asus',	'Informática',	52014596),
	   ('Tramontina',	'Utensílios Domésticos',	50563985),
	   ('Grow',	'Brinquedos',	47896325),
	   ('Mattel',	'Bonecos',	59865898);

INSERT INTO Produto (Nome,	Valor_Unitario,	Quantidade_Estoque,	Descricao,	Codigo_Fornecedor)
VALUES ('Banco Imobiliário',	65.00,	15,	'Versão Super Luxo',	1001),
	   ('Puzzle 5000 peças',	50.00,	5,	'Mapas Mundo',	1005),
	   ('Faqueiro',	350.00,	0,	'120 peças',	1004),
	   ('Jogo para churrasco',	75.00,	3,	'7 peças',	1004),
	   ('Tablet',	750.00,	29,	'Tablet',	1003),
	   ('Detetive',	49.00,	0,	'Nova Versão do Jogo',	1001),
	   ('Chocolate com Paçoquinha',	6.00,	0,	'Barra',	1002),
	   ('Galak',	5.00,	65,	'Barra',	1002);

INSERT INTO Cliente (Nome,	Logradouro,	Numero,	Telefone,	Data_Nasc)
VALUES ('Maria Clara',	'R. 1° de Abril',	870,	96325874,	'2000-08-15'),
	   ('Alberto Souza',	'R. XV de Novembro',	987,	95873625,	'1985-02-02'),
	   ('Sonia Silva',	'R. Voluntários da Pátria',	1151,	75418596,	'1957-08-23'),
	   ('José Sobrinho',	'Av. Paulista',	250,	85236547,	'1986-12-09'),
	   ('Carlos Camargo',	'Av. Tiquatira',	9652,	75896325,	'1971-03-25');

INSERT INTO Pedido
VALUES (99001,	33601,	1,	1,	'2012-06-07'),
	   (99001,	33601,	2,	1,	'2012-06-07'),
	   (99001,	33601,	8,	3,	'2012-06-07'),
	   (99002,	33602,	2,	1,	'2012-06-09'),
	   (99002,	33602,	4,	3,	'2012-06-09'),
	   (99003,	33605,	5,	1,	'2012-06-15');
	   
SELECT  p.Nome AS Produto, pe.Quantidade, (p.Valor_Unitario * pe.Quantidade) AS Valor_Total, (p.Valor_Unitario * pe.Quantidade * 0.75) AS Valor_Total_Com_Desconto
FROM Pedido pe INNER JOIN Cliente c ON pe.Codigo_Cliente = c.Codigo
INNER JOIN Produto p ON pe.Codigo_Produto = p.Codigo
WHERE c.Nome = 'Maria Clara';

SELECT f.Nome FROM Fornecedores f INNER JOIN Produto p ON f.Codigo = p.Codigo_Fornecedor WHERE p.Quantidade_Estoque <= 0;

UPDATE FROM Produto WHERE Descricao LIKE 'Barra' SET Valor_Unitario = Valor_Unitario - (Valor_Unitario * 0,10);     
UPDATE FROM Produto WHERE Nome LIKE 'Faqueiro' SET Quantidade_Estoque = 10;

SELECT COUNT(Codigo) FROM Cliente WHERE DATEDIFF(YEAR, Data_Nasc, GETDATE()) > 40;
SELECT Nome, Telefone FROM Fornecedores WHERE Atividade LIKE 'Brinquedos' OR Atividade LIKE 'Chocolate';
SELECT Nome, Valor_Unitario - (Valor_Unitario * 0.25) AS Desconto FROM Produto WHERE Valor_Unitario < 50.00;
SELECT Nome, Valor_Unitario - (Valor_Unitario * 0.10) AS Desconto FROM Produto WHERE Valor_Unitario > 100.00;
SELECT (p.Valor_Unitario * pe.Quantidade) - ((p.Valor_Unitario * pe.Quantidade) * 0.25) AS Valor_Total_Desconto FROM Pedido WHERE Codigo = 990001;
SELECT p.Codigo, c.Nome, DATEDIFF(YEAR, Data_Nasc, GETDATE()) AS Idade FROM Pedido p INNER JOIN Cliente c ON c.Codigo = p.Codigo_Cliente;