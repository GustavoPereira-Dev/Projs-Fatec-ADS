CREATE DATABASE Delivery;
USE Delivery;

CREATE TABLE Clientes (
    RG VARCHAR(10) PRIMARY KEY,
    CPF VARCHAR(11),
    Nome VARCHAR(100),
    Logradouro VARCHAR(100),
    Numero INT
);

CREATE TABLE Fornecedor (
    Codigo INT PRIMARY KEY,
    Nome VARCHAR(100),
    Logradouro VARCHAR(100),
    Numero INT NULL,
    Pais VARCHAR(50),
    Area INT,
    Telefone INT NULL,
    CNPJ CHAR(14) NULL,
    Cidade VARCHAR(100),
    Transporte VARCHAR(50) NULL,
    Moeda VARCHAR(10)
);

CREATE TABLE Produto (
    Codigo INT PRIMARY KEY,
    Descricao VARCHAR(100),
    Preco DECIMAL(10,2),
    Qtd INT,
    Cod_Fornecedor INT FOREIGN KEY REFERENCES Fornecedor(Codigo)
);

CREATE TABLE Pedido (
    NotaFiscal INT PRIMARY KEY,
    Logradouro VARCHAR(100),
    Numero INT,
    Valor DECIMAL(10,2),
    Data DATE,
    RG_Cliente VARCHAR(20)
);

INSERT INTO Clientes (RG, CPF, Nome, Logradouro, Numero) VALUES
('29531844', '34519878040', 'Luiz André', 'R. Astorga', 500),
('13514996x', '84984285630', 'Maria Luiza', 'R. Piauí', 174),
('121985541', '23354997310', 'Ana Barbara', 'Av. Jaceguai', 1141),
('23987746x', '43587669920', 'Marcos Alberto', 'R. Quinze', 22);

INSERT INTO Fornecedor (Codigo, Nome, Logradouro, Numero, Pais, Area, Telefone, CNPJ, Cidade, Transporte, Moeda)
VALUES 
(1, 'Clone', 'Av. Nações Unidas, 12000', 12000, 'BR', 55, 1141487000, NULL, 'São Paulo', NULL, 'R$'),
(2, 'Logitech', '28th Street, 100', 100, 'USA', 1, 2127695100, NULL, NULL, 'Avião', 'US$'),
(3, 'LG', 'Rod. Castello Branco', NULL, 'BR', 55, 800664400, '4159978100001', 'Sorocaba', NULL, 'R$'),
(4, 'PcChips', 'Ponte da Amizade', NULL, 'PY', 595, NULL, NULL, NULL, 'Navio', 'US$');

INSERT INTO Produto (Codigo, Descricao, Preco, Qtd, Cod_Fornecedor)
VALUES 
(10, 'Mouse', 24.00, 30, 1),
(11, 'Teclado', 50.00, 20, 1),
(12, 'Cx. De Som', 30.00, 8, 2),
(13, 'Monitor 17', 350.00, 4, 3),
(14, 'Notebook', 1500.00, 7, 4);

INSERT INTO Pedido (NotaFiscal, Logradouro, Numero, Valor, Data, RG_Cliente)
VALUES 
(1001, 'R. Astorga', 500, 754.00, '2018-04-01', '121985541'),
(1002, 'R. Piauí', 174, 350.00, '2018-04-02', '121985541'),
(1003, 'Av. Jaceguai', 1141, 30.00, '2018-04-02', '29531844'),
(1004, 'R. Quinze', 22, 1500.00, '2018-04-03', '13514996x');

SELECT Logradouro + CASE 
						WHEN Numero IS NOT NULL THEN ', ' + CAST(Numero AS VARCHAR) 
						ELSE '' 
					END + ' - ' + Pais AS EnderecoCompleto
FROM Fornecedor;

SELECT FORMAT(CAST(SUBSTRING(CPF, 1, 3) AS INT), '000') + '.' + FORMAT(CAST(SUBSTRING(CPF, 4, 3) AS BIGINT), '000') + '.' +
    FORMAT(CAST(SUBSTRING(CPF, 7, 3) AS BIGINT), '000') + '-' +
    SUBSTRING(CPF, 10, 2) AS CPF_Formatado,
    STUFF(RG, LEN(RG), 0, '-') AS RG_Formatado
FROM Cliente;

SELECT NotaFiscal, Valor, Valor * 0.9 AS ValorComDesconto FROM Pedido WHERE NotaFiscal = 1003;

SELECT NotaFiscal, Valor, Valor * 0.95 AS ValorComDesconto FROM Pedido WHERE Valor > 700;

UPDATE Produto SET Preco = Preco * 1.2 WHERE Qtd < 10;
-- SELECT * FROM Produto WHERE Qtd < 10;

SELECT c.CPF, c.Nome, c.Logradouro + ', ' + CAST(c.Numero AS VARCHAR) AS Endereco FROM Cliente c INNER JOIN Pedido p ON p.RG_Cliente = c.RG WHERE p.NotaFiscal = 1004;

SELECT f.Pais, f.Transporte FROM Produto p INNER JOIN Fornecedor f ON p.Cod_Fornecedor = f.Codigo WHERE p.Descricao LIKE '%Cx. De Som%';

SELECT p.Descricao, p.Qtd FROM Produto p JOIN Fornecedor f ON p.Cod_Fornecedor = f.Codigo WHERE f.Nome = 'Clone';

