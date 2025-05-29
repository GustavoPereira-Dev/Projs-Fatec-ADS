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
('João da Silva', 'R. ABC', 78, '00000001', 'Casa'),
('Maria dos Santos', 'R. BCD', 150, '00000002', 'Bloco 3 Apto 340'),
('José Abreu', 'R. CDE', 1500, '00000003', 'Casa'),
('Ana Souza', 'R. DEF', 2350, '00000004', 'Apto 203')
GO
INSERT INTO Telefone_Cliente VALUES
(3401, '11999991111'),
(3401, '11999991112'),
(3416, '11988881111'),
(3416, '11988881122'),
(3416, '11988881133'),
(3431, '11977776666')
GO
INSERT INTO Veiculo VALUES
('AAA0A00', 'Fiat', 'Uno', 'Vermelho', 2001, 2002, 2010, 3401),
('AAA0A01', 'Renault', 'Sandero', 'Prata', 2010, 2011, 2015, 3416),
('AAA0A02', 'Honda', 'Civic', 'Cinza', 2018, 2019, 2022, 3401),
('AAA0A03', 'Ford', 'Focus', 'Azul', 2009, 2010, 2023, 3416),
('AAA0A04', 'Nissan', 'March', 'Verde', 2021, 2022, 2022, 3431),
('AAA0A05', 'Renault', 'Logan', 'Preta', 2008, 2009, 2018, 3401)
GO
INSERT INTO Peca(Nome, Preco, Estoque) VALUES
('Bateria', 250.00, 25),
('Pneu', 175.00, 18),
('Jogo de Vela', 150.00, 100),
('Bóia de combustível', 127.00, 15)
GO
INSERT INTO Funcionario(Nome, Logradouro, Numero, Telefone, Categoria_Habilitacao, CategoriaID) VALUES
('Tefus Layquoda', 'Rua São Paulo', 753, '11936344697', 'B', 3 ),
('Siso Diasitin', 'Avenida dos Estados', 686, '11986176665', 'B', 3),
('Kuaxo Reriu', 'Rua Maria Eunice', 995, '11994499303', 'C', 4 ),
('Pefa Rutea', 'Rua Vila Leão', 710, '11993311122', 'B', 2),
('Duidi Mupiuri', 'Rua Anailândia', 268, '11981108263', 'B', 1)
GO
INSERT INTO Reparo VALUES
('AAA0A00', 101, 3411, GETDATE(), 300.00, 35),
('AAA0A00', 101, 3418, GETDATE(), 400.00, 40),
('AAA0A01', 104, 3418, GETDATE(), 800.00, 70)
GO
SELECT * FROM Categoria
SELECT * FROM Cliente
SELECT * FROM Telefone_Cliente
SELECT * FROM Veiculo
SELECT * FROM Peca
SELECT * FROM funcionario
SELECT * FROM Reparo

-- Selects Simples

-- O valor da hora de um estagiário
 SELECT Valor_Hora FROM Categoria WHERE Categoria = "Estagiário";

-- Nome e valor das Categorias que ganham menos de 35.00 em ordem crescente de valor 
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





-- Operações de Junções (INNER e OUTER JOINS)

--Consultar Nome, Logradouro, Numero e Complemento do cliente, Placa, Marca, Modelo, Cor e Ano_Modelo --dos carros cadastrados cujo Ano de Fabricação é Maior ou igual a 2010 (Ordenado por nome de Cliente)  
SELECT cl.Nome, cl.Logradouro, cl.Numero, cl.Complemento, v.Placa, v.Marca, v.Modelo, v.Cor, v.Ano_Modelo FROM Cliente cl 
INNER JOIN Veiculo v ON cl.ID = v.ClienteID ORDER BY cl.Nome;  

--Consultar os telefones do cliente cujo veiculo tem placa AAA0A00. Aplicar máscara para sair (XX)XXXXX-XXXX  
SELECT '(' + SUBSTRING(tc.Telefone, 1, 2) + ')' + SUBSTRING(tc.Telefone, 3, 5) + '-' + SUBSTRING(tc.Telefone, 8, 4) FROM Telefone_Cliente tc 
INNER JOIN Cliente cl ON tc.ClienteID = cl.ID INNER JOIN Veiculo v ON cl.ID = v.ClienteID WHERE v.Placa LIKE 'AAA0A00'; 

--Consultar Nome, Logradouro, Numero e Complemento dos clientes que não tem telefone cadastrado  
SELECT cl.Nome, cl.Logradouro, cl.Numero, cl.Complemento FROM Cliente cl LEFT OUTER JOIN Telefone_Cliente tc ON cl.ID = tc.ClienteID 
WHERE tc.ClienteID IS NULL; 

--Consultar Nome, Logradouro, Numero e Complemento dos clientes que fizeram reparo hoje (Remover linhas duplicadas)  
SELECT DISTINCT cl.Nome, cl.Logradouro, cl.Numero, cl.Complemento FROM Cliente cl INNER JOIN Veiculo v ON cl.ID = v.ClienteID 
INNER JOIN Reparo r ON v.Placa = r.VeiculoPlaca WHERE r.Dt = '20-05-2025' AND cl.ID = v.ClienteID; 

--Consultar nome, preço e estoque das peças que ainda não foram utilizadas em reparos, --ordenadas pelo preço decrescente  
SELECT pc.Nome, pc.Preco, pc.Estoque FROM Reparo r RIGHT OUTER JOIN Peca pc ON pc.ID = r.PecaID WHERE r.PecaID IS NULL ORDER BY pc.Preco DESC; 



-- Funções de Agregação com JOINS

--Consultar o nome do cliente e a quantidade de carros por cliente, ordenados pelo nome do cliente ascendentemente 
SELECT cl.nome, COUNT(cl.id) AS quant_carros FROM Cliente cl INNER JOIN Veiculo v ON cl.ID = v.ClienteID GROUP BY cl.Nome ORDER BY cl.nome ASC; 

--Consultar o nome do cliente e a média do Ano_modelo (alias media_modelo) de carros por cliente, ordenados pelo 
--media_modelo em ordem descrescente 
SELECT cl.Nome, AVG(v.Ano_Modelo) AS media_modelo FROM Cliente cl INNER JOIN Veiculo v ON cl.id = v.ClienteID GROUP BY cl.Nome ORDER BY media_modelo; 

--Consultar o nome do cliente e a soma do custo_total do reparo (soma_reparos), 
--ordenados pelo nome do cliente ascendentemente 
SELECT cl.nome, SUM(r.Custo_Total) AS soma_reparos FROM Cliente cl INNER JOIN Veiculo v ON cl.id = v.ClienteID 
INNER JOIN Reparo r ON v.Placa = r.VeiculoPlaca GROUP BY cl.Nome ORDER BY cl.Nome 

--Consultar o nome do cliente, a marca, o modelo e o ano_modelo do veículo e o tempo e o custo_total do reparo 
--de maior valor dentre os cadastrados 
SELECT cl.Nome, v.Marca, v.Modelo, v.Ano_Modelo, r.Tempo, r.Custo_Total FROM Cliente cl INNER JOIN Veiculo v ON cl.ID = v.ClienteID 
INNER JOIN Reparo r ON v.Placa = r.VeiculoPlaca WHERE r.Custo_Total IN (SELECT MAX(r.Custo_Total) FROM Cliente cl 
INNER JOIN Veiculo v ON cl.ID = v.ClienteID INNER JOIN Reparo r ON v.Placa = r.VeiculoPlaca) 