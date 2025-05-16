USE master
GO
CREATE DATABASE Ex_Mecanica
GO
USE Ex_Mecanica
GO
CREATE TABLE Cliente(
	ID int IDENTITY(3401, 15),
	Nome varchar(100),
	Logradouro varchar(200),
	Numero int CHECK(Numero > 0),
	CEP char(8) CHECK(LEN(CEP) = 8),
	Complemento varchar(255)
	PRIMARY KEY(ID)
)
GO
CREATE TABLE Telefone_Cliente(
	ClienteID int,
	Telefone varchar(11) CHECK(LEN(Telefone) = 10 OR LEN(Telefone) = 11) 
	PRIMARY KEY(ClienteID, Telefone)
	FOREIGN KEY(ClienteID) REFERENCES Cliente(ID)
)
GO
CREATE TABLE Veiculo(
	Placa char(7),
	Marca varchar(30),
	Modelo varchar(30),
	Cor varchar(30),
	Ano_Fabricacao int CHECK(Ano_Fabricacao > 1997),
	Ano_Modelo int CHECK(Ano_Modelo > 1997),
	Data_Aquisicao int,
	ClienteID int
	PRIMARY KEY(Placa),
	FOREIGN KEY(ClienteID) REFERENCES Cliente(ID),
	CONSTRAINT chk_fabricacao_modelo CHECK(Ano_Modelo - Ano_Fabricacao >= 0)
)
GO
CREATE TABLE Peca(
	ID int IDENTITY(3411, 7),
	Nome varchar(30) UNIQUE,
	Preco decimal(4, 2) CHECK(Preco > 0),
	Estoque int CHECK(Estoque >= 10)
	PRIMARY KEY(ID)
)
GO
CREATE TABLE Categoria(
	ID int IDENTITY,
	Categoria varchar(10),
	Valor_Hora decimal(4, 2)
	PRIMARY KEY(ID),
	CONSTRAINT chk_Categoria_ValorHora CHECK(Categoria = 'Estagiário' AND Valor_Hora >= 15.00 OR
	Categoria = 'Nível 1' AND Valor_Hora >= 25.00 OR 
	Categoria = 'Nível 2' AND Valor_Hora >= 35.00 OR
	Categoria = 'Nível 3' AND Valor_Hora >= 50.00)
)
GO
CREATE TABLE Funcionario(
	ID int IDENTITY(101, 1),
	Nome varchar(100),
	Logradouro varchar(200),
	Numero int CHECK(Numero > 0),
	Telefone char(11),
	Categoria_Habilitacao varchar(2),
	CategoriaID int
	PRIMARY KEY(ID),
	FOREIGN KEY(CategoriaID) REFERENCES Categoria(ID),
	CONSTRAINT chk_categoria_habilitacao CHECK(Categoria_Habilitacao = 'A' OR
	Categoria_Habilitacao = 'B' OR Categoria_Habilitacao = 'C' OR
	Categoria_Habilitacao = 'D' OR Categoria_Habilitacao = 'E')
)
GO
CREATE TABLE Reparo(
	VeiculoPlaca char(7) CHECK(LEN(VeiculoPlaca) = 7),
	FuncionarioID int,
	PecaID int,
	Dt date DEFAULT GETDATE(),
	Custo_Total decimal(4, 2) CHECK(Custo_Total > 0),
	Tempo int CHECK(Tempo > 0)
	PRIMARY KEY(VeiculoPlaca, FuncionarioID, PecaID, Dt),
	FOREIGN KEY(VeiculoPlaca) REFERENCES Veiculo(Placa),
	FOREIGN KEY(FuncionarioID) REFERENCES Funcionario(ID),
	FOREIGN KEY(PecaID) REFERENCES Peca(ID)
)
GO
INSERT INTO categoria(Categoria, Valor_Hora) VALUES
('Estagiário', 20.00),
('Nível 1', 30.00),
('Nível 2', 40.00),
('Nível 3', 80.00)
GO
INSERT INTO cliente (nome, logradouro, numero, cep, complemento) VALUES
('Fulano de Tal', 'R. ABC', 78, '00000001', 'Casa'),
('Cicrano de Tal', 'R. BCD', 150, '00000002', 'Bloco 3 Apto 340'),
('Beltrano de Tal', 'R. CDE', 1500, '00000003', 'Casa')
GO
INSERT INTO veiculo VALUES
('AAA0A00', 'Fiat', 'Uno', 'Vermelho', 2001, 2002, 2010, 3401),
('AAA0A01', 'Renault', 'Sandero', 'Prata', 2010, 2011, 2015, 3416),
('AAA0A02', 'Honda', 'Civic', 'Cinza', 2018, 2019, 2022, 3401),
('AAA0A03', 'Ford', 'Focus', 'Azul', 2009, 2010, 2023, 3416),
('AAA0A04', 'Nissan', 'March', 'Verde', 2021, 2022, 2022, 3431),
('AAA0A05', 'Renault', 'Logan', 'Preta', 2008, 2009, 2018, 3401)
GO
SELECT * FROM Categoria
SELECT * FROM cliente
SELECT * FROM veiculo

-- Selects Simples

-- O valor da hora de um estagiário
 SELECT Valor_Hora FROM Categoria WHERE Categoria = 'Estagiário";

--Nome e valor das Categorias que ganham menos de 35.00 em ordem crescente de valor 
SELECT FROM Categoria WHERE Valor_Hora < 35.00 ORDER BY Valor_Hora ASC;

--Placa, marca, modelo e ano de fabricação dos carros cadastrados que foram fabricados entre 2000 e 2010 em ordem crescente de marca e modelo 
SELECT Placa, Marca, Modelo, Ano_Fabricacao FROM Veiculo WHERE Ano_Fabricacao BETWEEN 2000 AND 2010 ORDER BY Marca ASC, Modelo ASC;

--Placa, marca, modelo e cor dos carros cadastrados adquiridos depois de 2014 em ordem descrescente de ano 
SELECT Placa, Marca, Modelo, Cor FROM Veiculo WHERE Data_Aquisicao > 2014 ORDER BY Data_Aquisicao DESC;

--Placa, marca, modelo, cor dos camos do cliente 3401 em ordem descrescente de placa 
SELECT Placa, Marca, Modelo, Cor FROM Veiculo WHERE ClienteID=3401 ORDER BY Placa DESC;


-- Selects CASES, Funções e Subquery

--Consultar o nome, o logradouro e o número de porta do endereço dos clientes cujos carros são da marca Renault, ordenar a saída por nome ascendente  
SELECT nome, Logradouro, Numero FROM Cliente WHERE ID IN (SELECT ClienteID FROM Veiculo WHERE Marca LIKE 'Renault') ORDER BY Nome ASC; 

 

--Para minimizar falhas de caracteres acentuados, consultar a categoria e o valor hora da categoria, no entanto, caso a categoria seja Nível 1, Nível 2 ou Nível 3, mudar para Categoria 1, Categoria 2 e Categoria 3 
SELECT Categoria = CASE(Categoria) 
WHEN 'Nível 1' THEN 'Categoria 1' 
WHEN 'Nível 2' THEN 'Categoria 2' 
WHEN 'Nível 3' THEN 'Categoria 3' 
ELSE Categoria 
END,  
Valor_Hora FROM Categoria 

 

--Consultar o nome, o cep e o número do endereço dos proprietários de carros cuja diferença entre ano de aquisição e ano de fabricação é superior a 5 (Por serem INT, não usar o DATEDIFF). Ordenação ascendente por nome.  
SELECT Nome, CEP, Numero FROM Cliente WHERE ID IN (SELECT ClienteID FROM Veiculo WHERE Data_Aquisicao - Ano_Fabricacao > 5) ORDER BY Nome ASC; 

 

--Consultar o nome, o cep (mascarado XXXXX-XXX) e o número do endereço (mascarado sempre com 4 dígitos, completar com zeros quando tiver 1, 2 ou 3 dígitos) dos clientes ordenados por nome. 
SELECT Nome, SUBSTRING(CEP, 1, 6) + '-' + SUBSTRING(CEP, 6, 3) AS CEP, Num = CASE(LEN(Numero))  
WHEN 4 THEN CONVERT(VARCHAR(4), Numero) 
WHEN 3 THEN '0' + CONVERT(VARCHAR(3), Numero) 
WHEN 2 THEN '00' + CONVERT(VARCHAR(2), Numero) 
WHEN 1 THEN '000' + CONVERT(VARCHAR(1), Numero) 
END 
FROM Cliente ORDER BY Nome; 