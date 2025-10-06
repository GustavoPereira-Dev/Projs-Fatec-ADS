-- 1. Criar uma database, criar as tabelas abaixo, definindo o tipo de dados e a relação PK/FK e popular com alguma massa de dados de teste (Suficiente para testar UDFs)
-- Funcionário (Código, Nome, Salário)
-- Dependendente (Código_Dep, Código_Funcionário, Nome_Dependente, Salário_Dependente)
CREATE DATABASE EmpresaDB;
GO
USE EmpresaDB;
GO
CREATE TABLE Funcionario(
	Codigo INT PRIMARY KEY,
	Nome varchar(100),
	Salario decimal(7,1)
);
GO
CREATE TABLE Dependente(
	Codigo_Dep INT,
	Codigo_Funcionario INT,
	Nome_Dependente varchar(100),
	Salario_Dependente decimal(7,1)
	PRIMARY KEY (Codigo_Dep, Codigo_Funcionario),
	FOREIGN KEY (Codigo_Funcionario) REFERENCES Funcionario(Codigo)
);

-- Funcionários
INSERT INTO Funcionario VALUES
(1, 'Ana', 3000.00),
(2, 'Bruno', 4500.00),
(3, 'Carlos', 5000.00),
(4, 'Daniela', 3200.00),
(5, 'Eduardo', 2800.00);

-- Dependentes
INSERT INTO Dependente VALUES
(101, 1, 'Lucas', 500.00),
(102, 1, 'Lara', 600.00),
(103, 2, 'Pedro', 700.00),
(104, 3, 'Sofia', 800.00),
(105, 3, 'Tiago', 900.00),
(106, 4, 'Marina', 400.00),
(107, 5, 'João', 300.00);

-- a) Código no Github ou Pastebin de uma Function que Retorne uma tabela:
-- (Nome_Funcionário, Nome_Dependente, Salário_Funcionário, Salário_Dependente)
CREATE FUNCTION fn_tabeladependente()
RETURNS @tabela TABLE (
Nome_Funcionario VARCHAR(100),
Nome_Dependente VARCHAR(100),
Salario_Funcionario decimal(7,1),
Salario_Dependente decimal(7,1)
)
AS
BEGIN
	INSERT INTO @tabela (Nome_Funcionario, Nome_Dependente, Salario_Funcionario, Salario_Dependente) 
		SELECT f.Nome AS Nome_Funcionario, d.Nome_Dependente, f.Salario AS Salario_Funcionario,
        d.Salario_Dependente FROM Funcionario f JOIN Dependente d ON f.Codigo = d.Codigo_Funcionario

	RETURN
END

SELECT * FROM fn_tabeladependente();
-- b) Código no Github ou Pastebin de uma Função Escalar que retorna a soma dos Salários dos dependentes, mais a do funcionário.
CREATE FUNCTION fn_somasalarios(@CodigoFuncionario INT)
RETURNS DECIMAL(10,2)
AS
BEGIN
    DECLARE @total DECIMAL(10,2);

    SELECT @total = f.Salario + SUM(d.Salario_Dependente)
    FROM Funcionario f
    LEFT JOIN Dependente d ON f.Codigo = d.Codigo_Funcionario
    WHERE f.Codigo = @CodigoFuncionario GROUP BY f.Salario;

    RETURN @total;
END;

-- 2. Fazer uma Function que retorne
-- a) a partir da tabela Produtos (codigo, nome, valor unitário e qtd estoque), quantos produtos estão com estoque abaixo de um valor de entrada
CREATE TABLE Produto (
    codigo INT PRIMARY KEY,
    nome VARCHAR(100),
    valor_unitario DECIMAL(10, 2),
    qtd_estoque INT
);

INSERT INTO Produto VALUES
(1, 'Teclado', 120.00, 10),
(2, 'Mouse', 80.00, 5),
(3, 'Monitor', 900.00, 2),
(4, 'Notebook', 3500.00, 0),
(5, 'Cabo HDMI', 35.00, 3),
(6, 'Webcam', 250.00, 1),
(7, 'Headset', 180.00, 4),
(8, 'Impressora', 600.00, 6),
(9, 'Pen Drive', 40.00, 12),
(10, 'HD Externo', 300.00, 0);

CREATE FUNCTION fn_estoqueabaixoentrada(@Entrada INT)
RETURNS INT
AS
BEGIN
    DECLARE @total INT;

    SELECT @total = SUM(codigo) FROM Produto WHERE qtd_estoque < @Entrada GROUP BY qtd_estoque;

    RETURN @total;
END;

-- b) Uma tabela com o código, o nome e a quantidade dos produtos que estão com o estoque abaixo de um valor de entrada
CREATE FUNCTION fn_tabelaprodutos(@Entrada INT)
RETURNS @tabela TABLE (
    codigo INT,
    nome VARCHAR(100),
    qtd_produtos INT
)
AS 
BEGIN
    DECLARE @total INT;
    SELECT @total = dbo.fn_estoqueabaixoentrada(@Entrada);

    INSERT INTO @tabela (codigo, nome, qtd_produtos)
    SELECT codigo, nome, @total
    FROM Produto
    WHERE qtd_estoque < @Entrada;

    RETURN;
END

-- 3. Criar, uma UDF, que baseada nas tabelas abaixo, retorne
-- Nome do Cliente, Nome do Produto, Quantidade e Valor Total, Data de hoje
-- Tabelas iniciais:
-- Cliente (Codigo, nome)
-- Produto (Codigo, nome, valor)
CREATE DATABASE Loja;
USE Loja;

CREATE TABLE Cliente (
    Codigo INT PRIMARY KEY,
    Nome VARCHAR(100)
);

CREATE TABLE Produto (
    Codigo INT PRIMARY KEY,
    Nome VARCHAR(100),
    Valor DECIMAL(10, 2)
);

-- Simulação de compra
CREATE TABLE Compra (
    Codigo INT,
    Codigo_Cliente INT,
    Codigo_Produto INT,
    Quantidade INT,
    PRIMARY KEY (Codigo, Codigo_Cliente, Codigo_Produto),
    FOREIGN KEY (Codigo_Cliente) REFERENCES Cliente(Codigo),
    FOREIGN KEY (Codigo_Produto) REFERENCES Produto(Codigo)
);

INSERT INTO Cliente VALUES
(1, 'Gustavo'),
(2, 'Mariana'),
(3, 'Felipe');

INSERT INTO Produto VALUES
(1, 'Notebook', 3500.00), (2, 'Mouse', 80.00), (3, 'Teclado', 120.00);


INSERT INTO Compra VALUES
(101, 1, 2, 2),
(102, 2, 4, 1), 
(103, 3, 5, 3);

-- Nome do Cliente, Nome do Produto, Quantidade e Valor Total, Data de hoje
CREATE FUNCTION fn_dadoscompra()
RETURNS @tabela TABLE(
    Nome_Cliente VARCHAR(100),
    Nome_Produto VARCHAR(100),
    Quantidade INT,
    Valor_Total DECIMAL(10,2),
    Data_Hoje DATE
)
AS
BEGIN
    INSERT INTO @tabela (Nome_Cliente, Nome_Produto, Quantidade, Valor_Total, Data_Hoje)
        SELECT c.Nome AS Nome_Cliente, p.Nome AS Nome_Produto, cp.Quantidade, (cp.Quantidade * p.Valor_Unitario) AS Valor_Total,
        GETDATE() AS Data_Compra FROM Compra cp JOIN Cliente c ON cp.Codigo_Cliente = c.Codigo JOIN Produto p ON cp.Codigo_Produto = p.Codigo
    RETURN
END;

SELECT * FROM fn_dadosCompra();