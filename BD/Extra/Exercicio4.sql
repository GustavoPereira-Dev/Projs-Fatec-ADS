CREATE TABLE Mercado;
USE Mercado;

CREATE TABLE Cliente(
	CPF char(12),
	Nome varchar(100),
	Telefone char(9)
	PRIMARY KEY(CPF)
);

CREATE TABLE Fornecedor(
	ID int IDENTITY,
	Nome varchar(40),
	Logradouro varchar(60),
	N int,
	Complemento varchar(30),
	Cidade varchar(40)
	PRIMARY KEY(ID)
);

CREATE TABLE Produto(
	Codigo int IDENTITY,
	Descricao varchar(200),
	Fornecedor int,
	Preco DECIMAL(8,2)
	PRIMARY KEY(Codigo)
	FOREIGN KEY(Fornecedor) REFERENCES Fornecedor(ID)
);

CREATE TABLE Venda(
	Codigo int,
	Produto int,
	Cliente char(12),
	Quantidade int,
	ValorTotal DECIMAL(8, 2),
	Data date,
	PRIMARY KEY(Codigo, Produto, Cliente),
	FOREIGN KEY(Produto) REFERENCES Produto(ID),
	FOREIGN KEY(Cliente) REFERENCES Cliente(CPF)
);


INSERT INTO Cliente
VALUES ('345789092-90',	'Julio Cesar',	'8273-6541'),
	   ('251865337-10',	'Maria Antonia',	'8765-2314'),
	   ('876273154-16',	'Luiz Carlos',	'6128-9012'),
       ('791826398-00',	'Paulo Cesar',	'9076-5273');

INSERT INTO Fornecedor (Nome,	Logradouro,	N,	Complemento,	Cidade)
VALUES ('LG',	'Rod. Bandeirantes',	70000,	'Km 70',	'Itapeva'),
	   ('Asus',	'Av. Nações Unidas',	10206,	'Sala 225',	'São Paulo'),
	   ('AMD',	'Av. Nações Unidas',	10206,	'Sala 1095',	'São Paulo'),
	   ('Leadership',	'Av. Nações Unidas',	10206,	'Sala 87',	'São Paulo'),
	   ('Inno',	'Av. Nações Unidas',	10206,	'Sala 34',	'São Paulo');
	   
INSERT INTO Produto (Descricao,	Fornecedor,	Preço)
VALUES ('Monitor 19 pol.',	1,	449.99),
	   ('Netbook 1GB Ram 4 Gb HD',	2,	699.99),
	   ('Gravador de DVD - Sata	1',	99.99),
	   ('Leitor de CD',	1,	49.99),
	   ('Processador - Phenom X3 - 2.1GHz',	3,	349.99),
	   ('Mouse',	4,	19.99),
	   ('Teclado',	4,	25.99),
	   ('Placa de Video - Nvidia 9800 GTX - 256MB/256 bits',	5,	599.99);

INSERT INTO Venda
VALUES (1,	1,	'251865337-10',	1,	449.99,	'03/09/2009'),
	   (1,	4,	'251865337-10',	1,	49.99,	'03/09/2009'),
	   (1,	5,	'251865337-10',	1,	349.99,	'03/09/2009'),
	   (2,	6,	'791826398-00',	4,	79.96,	'06/09/2009'),
	   (3,	8,	'876273154-16',	1,	599.99,	'06/09/2009'),
	   (3,	3,	'876273154-16',	1,	99.99,	'06/09/2009'),
	   (3,	7,	'876273154-16',	1,	25.99,	'06/09/2009'),
	   (4,	2,	'345789092-90',	2,	1399.98,	'08/09/2009');
	   

SELECT CONVERT(VARCHAR(10), Data, 103) AS Data FROM Venda WHERE ID = 4;



