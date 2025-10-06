CREATE DATABASE Loja;
USE Loja;

-- Crie um CRUD Java Web com Spring com as operações de Inserir, Atualizar, Excluir, Selecionar Um,
-- Selecionar Tudo e Selecionar para uma UDF, com base nas tabelas abaixo, retornar
-- UDF (Nome do Cliente, Nome do Produto, Quantidade e Valor Total, Data de hoje)
-- Tabelas iniciais:
-- Cliente (Código, nome)
-- Produto (Código, nome, valor)

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
    @Valor DECIMAL(10,2),
    @qtd INT,
    @resultado VARCHAR(100) OUTPUT
)
AS
BEGIN
    DECLARE @erro VARCHAR(MAX)
    BEGIN TRY
        INSERT INTO Produto VALUES (@codigo, @nome, @valor, @qtd);
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
    @qtd INT,
    @resultado VARCHAR(100) OUTPUT
)
AS
BEGIN
    UPDATE Produto SET nome = @nome, valor_unitario = @valor, qtd_estoque = @qtd WHERE codigo = @codigo;
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