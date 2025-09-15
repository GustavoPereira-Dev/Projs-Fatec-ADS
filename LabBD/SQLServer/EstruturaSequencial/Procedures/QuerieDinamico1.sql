CREATE DATABASE Produto;
USE Produto;

CREATE TABLE Produto(
	Codigo INT PRIMARY KEY,
	Nome VARCHAR(100),
	Valor DECIMAL(5,2)
);
GO
CREATE TABLE Entrada(
	Codigo_Transacao INT,
	Codigo_Produto INT,
	Quantidade INT,
	Valor_Total DECIMAL(6,2)
	PRIMARY KEY(Codigo_Transacao, Codigo_Produto),
	FOREIGN KEY(Codigo_Produto) REFERENCES Produto(Codigo)
);
GO
CREATE TABLE Saida(
	Codigo_Transacao INT,
	Codigo_Produto INT,
	Quantidade INT,
	Valor_Total DECIMAL(6,2)
	PRIMARY KEY(Codigo_Transacao, Codigo_Produto),
	FOREIGN KEY(Codigo_Produto) REFERENCES Produto(Codigo)
);

INSERT INTO Produto (Codigo, Nome, Valor) VALUES
(1, 'Camiseta Básica', 29.90),
(2, 'Calça Jeans', 89.90),
(3, 'Tênis Esportivo', 150.00),
(4, 'Boné', 25.50),
(5, 'Mochila Escolar', 120.00),
(6, 'Relógio Digital', 199.99),
(7, 'Óculos de Sol', 89.50),
(8, 'Jaqueta de Couro', 299.90),
(9, 'Camisa Social', 79.90),
(10, 'Shorts Casual', 45.00),
(11, 'Meias', 10.00),
(12, 'Cinto de Couro', 49.90),
(13, 'Blusa de Frio', 109.90),
(14, 'Tênis Casual', 130.00),
(15, 'Sandália', 55.00),
(16, 'Carteira Masculina', 70.00),
(17, 'Chapéu Panamá', 85.00),
(18, 'Camisa Polo', 65.00),
(19, 'Calça Social', 110.00),
(20, 'Gravata', 39.90),
(21, 'Mala de Viagem', 250.00),
(22, 'Jaqueta Jeans', 180.00),
(23, 'Camiseta Regata', 22.50),
(24, 'Tênis de Corrida', 160.00),
(25, 'Blusa de Seda', 95.00),
(26, 'Moletom', 75.00),
(27, 'Tênis Infantil', 90.00),
(28, 'Vestido Casual', 120.00),
(29, 'Camisa Manga Longa', 80.00),
(30, 'Chinelo', 20.00),
(31, 'Bolsa Feminina', 150.00),
(32, 'Jaqueta Corta-Vento', 210.00),
(33, 'Calça Legging', 50.00),
(34, 'Camiseta Estampada', 35.00),
(35, 'Tênis de Basquete', 140.00),
(36, 'Bolsa de Ombro', 130.00),
(37, 'Camisa Xadrez', 70.00),
(38, 'Blusa de Tricô', 85.00),
(39, 'Calça Cargo', 95.00),
(40, 'Jaqueta Esportiva', 175.00),
(41, 'Camiseta Dry Fit', 40.00),
(42, 'Shorts de Praia', 55.00),
(43, 'Tênis Casual Feminino', 135.00),
(44, 'Relógio Analógico', 210.00),
(45, 'Mochila de Couro', 240.00),
(46, 'Bolsa Carteiro', 110.00),
(47, 'Jaqueta de Nylon', 150.00),
(48, 'Camisa Polo Feminina', 70.00),
(49, 'Vestido de Festa', 250.00),
(50, 'Sapato Social', 180.00);

-- Criar uma procedure que receba um código (‘e’ para ENTRADA e ‘s’ para SAIDA), criar uma exceção de
-- erro para código inválido, receba o codigo_transacao, codigo_produto e a quantidade e preencha a
-- tabela correta, com o valor_total de cada transação de cada produto.

CREATE PROCEDURE sp_transacao
    @codigo_tabela CHAR(1),
    @codigo_transacao INT,
    @codigo_produto INT,
    @quantidade INT,
    @valor_total DECIMAL(6,2),
    @saida VARCHAR(100) OUTPUT
AS
    DECLARE @tabela VARCHAR(7), @query NVARCHAR(MAX), @erro NVARCHAR(MAX);

    IF LOWER(@codigo_tabela) = 'e'
        SET @tabela = 'Entrada';
    ELSE
        SET @tabela = 'Saida';

    BEGIN TRY
        SET @query = 'INSERT INTO ' + @tabela + ' VALUES (' 
            + CAST(@codigo_transacao AS VARCHAR(10)) + ', '
            + CAST(@codigo_produto AS VARCHAR(10)) + ', '
            + CAST(@quantidade AS VARCHAR(10)) + ', '
            + CAST(@valor_total AS VARCHAR(10)) + ');';

        EXEC(@query);

        SET @saida = UPPER(@tabela) + ' inserido(a) com sucesso!';
    END TRY
    BEGIN CATCH
        SET @erro = ERROR_MESSAGE();

        IF(@erro LIKE '%FOREIGN KEY%')
            RAISERROR('Produto inexistente!', 16, 1);
        ELSE
            RAISERROR('Falha no processamento', 16, 1);
    END CATCH



-- Sucesso
DECLARE @resultado VARCHAR(100);
EXEC sp_transacao 's', 1, 1, 10, 320.12, @resultado OUTPUT;
SELECT @resultado AS Resultado;

-- Erro
DECLARE @resultado VARCHAR(100);
EXEC sp_transacao 'e', 1, 56, 10, 320.12, @resultado OUTPUT;
SELECT @resultado AS Resultado;

SELECT * FROM Entrada;
SELECT * FROM Saida;
