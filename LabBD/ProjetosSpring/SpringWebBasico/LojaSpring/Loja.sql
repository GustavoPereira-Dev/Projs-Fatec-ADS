CREATE DATABASE Loja;
GO
USE Loja;

-- Crie um CRUD Java Web com Spring com as operações de Inserir, Atualizar, Excluir, Selecionar Um,
-- Selecionar Tudo e Selecionar para uma UDF, com base nas tabelas abaixo, retornar
-- UDF (Nome do Cliente, Nome do Produto, Quantidade e Valor Total, Data de hoje)
-- Tabelas iniciais:
-- Cliente (Código, nome)
-- Produto (Código, nome, valor)

CREATE TABLE Cliente (
    Codigo INT PRIMARY KEY,
    Nome VARCHAR(100)
);
GO
CREATE TABLE Produto (
    Codigo INT PRIMARY KEY,
    Nome VARCHAR(100),
    Valor DECIMAL(10, 2)
);
GO
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
GO
INSERT INTO Produto VALUES
(1, 'Notebook', 3500.00), (2, 'Mouse', 80.00), (3, 'Teclado', 120.00);
GO
INSERT INTO Compra VALUES
(101, 1, 2, 2),
(102, 2, 1, 1),
(103, 3, 3, 3); 

CREATE FUNCTION fn_dadoscompra()
RETURNS @tabela TABLE(
	Codigo INT,
    Nome_Cliente VARCHAR(100),
    Nome_Produto VARCHAR(100),
    Quantidade INT,
    Valor_Total DECIMAL(10,2),
    Data_Hoje DATE
)
AS
BEGIN
    INSERT INTO @tabela (Codigo, Nome_Cliente, Nome_Produto, Quantidade, Valor_Total, Data_Hoje)
        SELECT cp.Codigo AS Codigo, c.Nome AS Nome_Cliente, p.Nome AS Nome_Produto, cp.Quantidade, 
               (cp.Quantidade * p.Valor) AS Valor_Total,
               GETDATE() AS Data_Hoje
        FROM Compra cp
        JOIN Cliente c ON cp.Codigo_Cliente = c.Codigo
        JOIN Produto p ON cp.Codigo_Produto = p.Codigo
    RETURN
END;

SELECT * FROM fn_dadoscompra();

-- Inserir Cliente
CREATE PROCEDURE sp_inserir_cliente (
    @codigo INT,
    @nome VARCHAR(100),
    @resultado VARCHAR(100) OUTPUT
)
AS
BEGIN
    DECLARE @erro VARCHAR(MAX)
    BEGIN TRY
        INSERT INTO Cliente VALUES (@codigo, @nome);
    END TRY
    BEGIN CATCH
        SET @erro = ERROR_MESSAGE()
        IF(@erro LIKE '%PRIMARY KEY%')
            RAISERROR('Cliente ja existente!', 16, 1)
		ELSE
			RAISERROR(@erro, 16, 1)
    END CATCH
    SET @resultado = 'Cliente adicionado com sucesso';
END;

-- Atualizar Cliente
CREATE PROCEDURE sp_atualizar_cliente (
    @Codigo INT,
    @NovoNome VARCHAR(100),
    @resultado VARCHAR(100) OUTPUT
)
AS
BEGIN
    UPDATE Cliente SET Nome = @NovoNome WHERE Codigo = @Codigo;
    SET @resultado = 'Cliente atualizado com sucesso';
END;

-- Excluir Cliente
CREATE PROCEDURE sp_excluir_cliente (
    @Codigo INT,
    @resultado VARCHAR(100) OUTPUT
)
AS
BEGIN
    DECLARE @erro VARCHAR(MAX)
    BEGIN TRY
        DELETE FROM Cliente WHERE Codigo = @Codigo;
    END TRY
    BEGIN CATCH
        SET @erro = ERROR_MESSAGE()
        IF(@erro LIKE '%FOREIGN KEY%')
            RAISERROR('Cliente possui compras!', 16, 1);
        ELSE 
            RAISERROR(@erro, 16, 1);
    END CATCH
    SET @resultado = 'Cliente removido com sucesso';
END;

-- Inserir Produto
CREATE PROCEDURE sp_inserir_produto (
    @codigo INT,
    @nome VARCHAR(100),
    @valor DECIMAL(10,2),
    @resultado VARCHAR(100) OUTPUT
)
AS
BEGIN
    DECLARE @erro VARCHAR(MAX)
    BEGIN TRY
        INSERT INTO Produto VALUES (@codigo, @nome, @valor);
    END TRY
    BEGIN CATCH
        SET @erro = ERROR_MESSAGE()
        IF(@erro LIKE '%PRIMARY KEY%')
            RAISERROR('Produto ja existente!', 16, 1)
        ELSE 
            RAISERROR(@erro, 16, 1)
    END CATCH
    SET @resultado = 'Produto inserido com sucesso';
END;

-- Atualizar Produto
CREATE PROCEDURE sp_atualizar_produto (
    @codigo INT,
    @nome VARCHAR(100),
    @valor DECIMAL(10,2),
    @resultado VARCHAR(100) OUTPUT
)
AS
BEGIN
    UPDATE Produto SET nome = @nome, valor = @valor WHERE codigo = @codigo;
    SET @resultado = 'Produto atualizado com sucesso';
END;

-- Excluir Produto
CREATE PROCEDURE sp_excluir_produto (
    @codigo INT,
    @resultado VARCHAR(100) OUTPUT
)
AS
BEGIN
    DECLARE @erro VARCHAR(MAX)
    BEGIN TRY
            DELETE FROM Produto WHERE Codigo = @codigo;
    END TRY
    BEGIN CATCH
        SET @erro = ERROR_MESSAGE()
        IF(@erro LIKE '%FOREIGN KEY%')
            RAISERROR('Produto esta contido em compras!', 16, 1)
        ELSE 
            RAISERROR(@erro, 16, 1)
    END CATCH
    SET @resultado = 'Produto removido com sucesso';
END;

-- Inserir Compra
CREATE PROCEDURE sp_inserir_compra (
    @Codigo INT,
    @Codigo_Cliente INT,
    @Codigo_Produto INT,
    @Quantidade INT,
    @resultado VARCHAR(100) OUTPUT
)
AS
BEGIN
    DECLARE @erro VARCHAR(MAX)
    BEGIN TRY
        INSERT INTO Compra (Codigo, Codigo_Cliente, Codigo_Produto, Quantidade) VALUES (@Codigo, @Codigo_Cliente, @Codigo_Produto, @Quantidade);
    END TRY
    BEGIN CATCH
        SET @erro = ERROR_MESSAGE()
        IF(@erro LIKE '%PRIMARY KEY%')
            RAISERROR('Compra ja existente!', 16, 1)
        ELSE IF(@erro LIKE '%FOREIGN KEY%')
            RAISERROR('Cliente ou Produto inexistente!', 16, 1)
    END CATCH
    SET @resultado = 'Compra inserida com sucesso';
END;

-- Atualizar Compra
CREATE PROCEDURE sp_atualizar_compra (
    @codigo INT,
    @quantidade DECIMAL(10,2),
    @resultado VARCHAR(100) OUTPUT
)
AS
BEGIN
    UPDATE Compra SET Quantidade  = @quantidade WHERE Codigo = @codigo;
    SET @resultado = 'Compra atualizada com sucesso';
END;

-- Excluir Compra
CREATE PROCEDURE sp_excluir_compra (
    @codigo INT,
    @resultado VARCHAR(100) OUTPUT
)
AS
BEGIN
    DELETE FROM Compra WHERE Codigo = @codigo;
    SET @resultado = 'Compra removida com sucesso';
END;