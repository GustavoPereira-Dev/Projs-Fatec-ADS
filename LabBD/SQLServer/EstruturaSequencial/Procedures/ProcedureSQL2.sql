CREATE DATABASE UsuarioTelefone;
USE UsuarioTelefone;

CREATE TABLE Pessoa (
    Id INT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Estado CHAR(2) NOT NULL,
    Telefone1 VARCHAR(9) NOT NULL,
    Telefone2 VARCHAR(9)
);


CREATE PROCEDURE sp_cadastrar_telefone
    @telefone CHAR(9),
    @tipoTelefone CHAR(1) OUTPUT
AS
    IF LEN(@telefone) = 8
        SET @tipoTelefone = 'f'; -- telefone fixo
    ELSE IF LEN(@telefone) = 9
        SET @tipoTelefone = 'c'; -- celular
    ELSE
        SET @tipoTelefone = NULL;

CREATE PROCEDURE sp_validar_pessoa
    @acao CHAR(1),
    @nome VARCHAR(200),
    @estado CHAR(2),
    @telefone1 CHAR(9),
    @telefone2 CHAR(9),
    @idPessoa INT = NULL
AS
    DECLARE @tipoTel1 CHAR(1), @tipoTel2 CHAR(1);
    DECLARE @id INT;

    IF UPPER(@estado) <> 'SP'
        RAISERROR('O cadastro somente funciona em Sao Paulo', 16, 1);

    EXEC sp_cadastrar_telefone @telefone1, @tipoTel1 OUTPUT;
    EXEC sp_cadastrar_telefone @telefone2, @tipoTel2 OUTPUT;

    IF @tipoTel1 = @tipoTel2
    BEGIN
        IF @tipoTel1 = 'c'
            RAISERROR('Ambos os telefones nao podem ser de celulares!', 16, 1);
        ELSE IF @tipoTel1 = 'f'
            RAISERROR('Ambos os telefones nao podem ser fixos!', 16, 1);
        ELSE
            RAISERROR('Ambos os telefones nao podem ser nulos!', 16, 1);
    END

    IF @acao = 'C'
    BEGIN
        SELECT @id = ISNULL(MAX(id), 0) + 1 FROM Pessoa;

        INSERT INTO Pessoa (id, nome, estado, telefone1, telefone2)
        VALUES (@id, @nome, @estado, @telefone1, @telefone2);
    END
    ELSE IF @acao = 'U'
    BEGIN
        IF @idPessoa IS NULL
            RAISERROR('ID da pessoa deve ser informado para atualizacao', 16, 1);

        UPDATE Pessoa
        SET nome = @nome,
            telefone1 = @telefone1,
            telefone2 = @telefone2
        WHERE id = @idPessoa;

    END
    ELSE
        RAISERROR('Acao invalida', 16, 1);

CREATE PROCEDURE sp_inserir_pessoa
    @nome VARCHAR(200),
    @estado CHAR(2),
    @telefone1 CHAR(9),
    @telefone2 CHAR(9),
    @mensagem VARCHAR(100) OUTPUT
AS
    EXEC sp_validar_pessoa 'C', @nome, @estado, @telefone1, @telefone2, NULL;
    SET @mensagem = 'Pessoa inserida com sucesso';

CREATE OR ALTER PROCEDURE sp_atualizar_pessoa
    @id INT,
    @nome VARCHAR(200),
    @telefone1 CHAR(9),
    @telefone2 CHAR(9),
    @mensagem VARCHAR(100) OUTPUT
AS
    EXEC sp_validar_pessoa 'U', @nome, 'SP', @telefone1, @telefone2, @id;
    SET @mensagem = 'Pessoa atualizada com sucesso';

CREATE PROCEDURE sp_deletar_pessoa
    @id INT,
    @mensagem VARCHAR(100) OUTPUT
AS
    DELETE FROM Pessoa WHERE id = @id;
    SET @mensagem = 'Pessoa deletada com sucesso';

	DECLARE @msg VARCHAR(100);

DECLARE @msg VARCHAR(100);
EXEC sp_inserir_pessoa @nome = 'Maria Silva', @estado = 'SP', @telefone1 = '912345678', @telefone2 = '34567890',@mensagem = @msg OUTPUT;
PRINT @msg;


DECLARE @msg VARCHAR(100);
EXEC sp_atualizar_pessoa @id = 1, @nome = 'Maria Souza', @telefone1 = '987654321', @telefone2 = '33445566',
    @mensagem = @msg OUTPUT;
PRINT @msg;

DECLARE @msg VARCHAR(100);
EXEC sp_deletar_pessoa @id = 1, @mensagem = @msg OUTPUT;
PRINT @msg;
