CREATE DATABASE ex_triggers_03
GO
USE ex_triggers_03;
GO
CREATE TABLE Produto(
	Codigo INT PRIMARY KEY,
	Nome VARCHAR(100) NOT NULL,
	Descricao VARCHAR(200) NOT NULL,
	Valor_Unitario DECIMAL (7,2) NOT NULL
);
GO
CREATE TABLE Estoque(
	Codigo_Produto INT,
	Qtd_Estoque INT NOT NULL,
	Estoque_Minimo INT NOT NULL
	PRIMARY KEY(Codigo_Produto)
	FOREIGN KEY(Codigo_Produto) REFERENCES Produto(Codigo)
);
GO
CREATE TABLE Venda(
    Nota_Fiscal INT,
    Codigo_Produto INT,
    Quantidade INT NOT NULL,
    PRIMARY KEY(Nota_Fiscal, Codigo_Produto),
    FOREIGN KEY(Codigo_Produto) REFERENCES Produto(Codigo)
);

INSERT INTO Produto (Codigo, Nome, Descricao, Valor_Unitario) VALUES
(1, 'Arroz', 'Arroz branco 5kg', 20.00),
(2, 'Feijão', 'Feijão preto 1kg', 7.50),
(3, 'Açúcar', 'Açúcar cristal 2kg', 5.00),
(4, 'Óleo', 'Óleo de soja 900ml', 6.00),
(5, 'Macarrão', 'Macarrão espaguete 500g', 4.50),
(6, 'Leite', 'Leite integral 1L', 3.20),
(7, 'Café', 'Café torrado 250g', 8.00),
(8, 'Sal', 'Sal refinado 1kg', 2.00),
(9, 'Farinha', 'Farinha de trigo 1kg', 4.00),
(10, 'Margarina', 'Margarina 500g', 5.50);
GO
INSERT INTO Estoque (Codigo_Produto, Qtd_Estoque, Estoque_Minimo) VALUES
(1, 100, 10),
(2, 80, 8),
(3, 50, 5),
(4, 60, 6),
(5, 70, 7),
(6, 90, 9),
(7, 40, 4),
(8, 30, 3),
(9, 20, 2),
(10, 25, 2);
GO
INSERT INTO Venda (Nota_Fiscal, Codigo_Produto, Quantidade) VALUES
(1001, 1, 5),
(1001, 2, 10),
(1002, 3, 15),
(1002, 4, 5),
(1003, 5, 10),
(1003, 6, 20),
(1004, 7, 5),
(1004, 8, 10),
(1005, 9, 10),
(1005, 10, 5);

-- Fazer uma TRIGGER AFTER na tabela Venda que, uma vez feito um INSERT, verifique se a quantidade
-- está disponível em estoque. Caso esteja, a venda se concretiza, caso contrário, a venda deverá ser
-- cancelada e uma mensagem de erro deverá ser enviada. A mesma TRIGGER deverá validar, caso a
-- venda se concretize, se o estoque está abaixo do estoque mínimo determinado ou se após a venda,
-- ficará abaixo do estoque considerado mínimo e deverá lançar um print na tela avisando das duas
-- situações.

CREATE TRIGGER t_insrtvendaestoque
ON Venda
AFTER INSERT
AS
BEGIN
    -- Verifica estoque suficiente para cada produto inserido
    IF EXISTS (
        SELECT 1
        FROM inserted i
        JOIN Estoque e ON i.Codigo_Produto = e.Codigo_Produto
        WHERE i.Quantidade > e.Qtd_Estoque
    )
    BEGIN
        RAISERROR('Venda cancelada: quantidade em estoque insuficiente para um ou mais produtos.', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END

    -- Atualiza estoque para cada produto vendido
    UPDATE e
    SET e.Qtd_Estoque = e.Qtd_Estoque - i.Quantidade
    FROM Estoque e
    JOIN inserted i ON e.Codigo_Produto = i.Codigo_Produto;

    -- Cria uma tabela temporária para armazenar os produtos com estoque abaixo do mínimo
    DECLARE @produtos_abaixo TABLE (
        Codigo_Produto INT PRIMARY KEY,
        Qtd_Estoque INT,
        Estoque_Minimo INT,
        Nome VARCHAR(100)
    );

    INSERT INTO @produtos_abaixo (Codigo_Produto, Qtd_Estoque, Estoque_Minimo, Nome)
		SELECT e.Codigo_Produto, e.Qtd_Estoque, e.Estoque_Minimo, p.Nome FROM Estoque e
		JOIN Produto p ON e.Codigo_Produto = p.Codigo WHERE e.Qtd_Estoque < e.Estoque_Minimo;

    -- Loop para emitir mensagens
    WHILE EXISTS (SELECT 1 FROM @produtos_abaixo)
    BEGIN
        DECLARE @Codigo_Produto INT, @Qtd_Estoque INT, @Estoque_Minimo INT, @Nome VARCHAR(100), @msg NVARCHAR(200);

        SELECT TOP 1 @Codigo_Produto = Codigo_Produto, @Qtd_Estoque = Qtd_Estoque, @Estoque_Minimo = Estoque_Minimo, @Nome = Nome
        FROM @produtos_abaixo;

        SET @msg = 'ATENÇÃO: Estoque do produto "' + @Nome + '" (Código ' + CAST(@Codigo_Produto AS VARCHAR) + 
                   ') está abaixo do estoque mínimo. Estoque atual: ' + CAST(@Qtd_Estoque AS VARCHAR) +
                   ', mínimo: ' + CAST(@Estoque_Minimo AS VARCHAR);

        PRINT @msg;

        DELETE FROM @produtos_abaixo WHERE Codigo_Produto = @Codigo_Produto;
    END
END

-- Fazer uma UDF (User Defined Function) Multi Statement Table, que apresente, para uma dada nota
-- fiscal, a seguinte saída:
-- (Nota_Fiscal | Codigo_Produto | Nome_Produto | Descricao_Produto | Valor_Unitario | Quantidade | Valor_Total*)
-- * Considere que Valor_Total = Valor_Unitário * Quantidade

CREATE FUNCTION fn_vendadetalhes (@Nota_Fiscal INT)
RETURNS @tabela TABLE (
    Nota_Fiscal INT,
    Codigo_Produto INT,
    Nome_Produto VARCHAR(100),
    Descricao_Produto VARCHAR(200),
    Valor_Unitario DECIMAL(7,2),
    Quantidade INT,
    Valor_Total DECIMAL(12,2)
)
AS
BEGIN
    INSERT INTO @tabela
		SELECT v.Nota_Fiscal, v.Codigo_Produto, p.Nome, p.Descricao, p.Valor_Unitario, v.Quantidade, p.Valor_Unitario * v.Quantidade AS Valor_Total
		FROM Venda v JOIN Produto p ON v.Codigo_Produto = p.Codigo WHERE v.Nota_Fiscal = @Nota_Fiscal;

    RETURN;
END
