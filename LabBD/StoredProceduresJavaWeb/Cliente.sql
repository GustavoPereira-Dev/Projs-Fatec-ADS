CREATE DATABASE CadastroCliente;
USE CadastroCliente;

CREATE TABLE Cliente (
    CPF CHAR(11) PRIMARY KEY,
    Nome VARCHAR(100),
    Email VARCHAR(200),
    Limite_de_credito DECIMAL(7,2),
    Dt_Nascimento DATE
);

CREATE PROCEDURE sp_valida_cpf
    @CPF CHAR(11),
    @Valido BIT OUTPUT
AS
BEGIN
    DECLARE @i INT = 1
    DECLARE @Soma1 INT = 0
    DECLARE @Soma2 INT = 0
    DECLARE @Digito1 INT
    DECLARE @Digito2 INT

    IF @CPF IN (
        '00000000000','11111111111','22222222222','33333333333',
        '44444444444','55555555555','66666666666','77777777777',
        '88888888888','99999999999'
    )
    BEGIN
        SET @Valido = 0
        RETURN
    END

    WHILE @i <= 9
    BEGIN
        SET @Soma1 = @Soma1 + CAST(SUBSTRING(@CPF, @i, 1) AS INT) * (11 - @i)
        SET @i = @i + 1
    END

    SET @Digito1 = CASE WHEN (@Soma1 * 10) % 11 = 10 THEN 0 ELSE (@Soma1 * 10) % 11 END

    SET @i = 1
    WHILE @i <= 10
    BEGIN
        SET @Soma2 = @Soma2 + CAST(SUBSTRING(@CPF, @i, 1) AS INT) * (12 - @i)
        SET @i = @i + 1
    END

    SET @Digito2 = CASE WHEN (@Soma2 * 10) % 11 = 10 THEN 0 ELSE (@Soma2 * 10) % 11 END

    IF @Digito1 = CAST(SUBSTRING(@CPF, 10, 1) AS INT) AND
       @Digito2 = CAST(SUBSTRING(@CPF, 11, 1) AS INT)
        SET @Valido = 1
    ELSE
        SET @Valido = 0
END

CREATE PROCEDURE sp_inserir_cliente
    @CPF CHAR(11),
    @Nome VARCHAR(100),
    @Email VARCHAR(200),
    @Limite DECIMAL(7,2),
    @Nascimento DATE,
    @saida VARCHAR(100) OUTPUT
AS
BEGIN
    DECLARE @Valido BIT
    EXEC sp_valida_cpf @CPF, @Valido OUTPUT
    IF @Valido = 0
    BEGIN
        RAISERROR('CPF inválido ou repetido.', 16, 1)
        RETURN
    END

    IF EXISTS (SELECT 1 FROM Cliente WHERE CPF = @CPF)
    BEGIN
        RAISERROR('CPF já cadastrado.', 16, 1)
        RETURN
    END

    INSERT INTO Cliente (CPF, Nome, Email, Limite_de_credito, Dt_Nascimento)
    VALUES (@CPF, @Nome, @Email, @Limite, @Nascimento)

    SET @saida = 'Cliente ' + @Nome + ' adicionado com sucesso!';
END

CREATE PROCEDURE sp_atualizar_cliente
    @CPF CHAR(11),
    @Nome VARCHAR(100),
    @Email VARCHAR(200),
    @Limite DECIMAL(7,2),
    @Nascimento DATE,
    @saida VARCHAR(100) OUTPUT
AS
BEGIN
    IF NOT EXISTS (SELECT 1 FROM Cliente WHERE CPF = @CPF)
    BEGIN
        RAISERROR('Cliente não encontrado.', 16, 1)
        RETURN
    END

    UPDATE Cliente
    SET Nome = @Nome,
        Email = @Email,
        Limite_de_credito = @Limite,
        Dt_Nascimento = @Nascimento
    WHERE CPF = @CPF

    SET @saida = 'Cliente com CPF ' + @CPF + ' atualizado com sucesso!';
END

CREATE PROCEDURE sp_excluir_cliente
    @CPF CHAR(11),
    @saida VARCHAR(100) OUTPUT
AS
BEGIN

    IF NOT EXISTS (SELECT 1 FROM Cliente WHERE CPF = @CPF)
    BEGIN
        RAISERROR('Cliente não encontrado.', 16, 1)
        RETURN
    END

    DELETE FROM Cliente WHERE CPF = @CPF

    SET @saida = 'Cliente com CPF ' + @CPF + ' remove com sucesso!';
END


EXEC sp_excluir_cliente '73103447078', NULL 

EXEC sp_inserir_cliente '16899562004', 'João Silva', 'joao.silva@email.com', 4500.00, '1985-06-15', NULL
EXEC sp_inserir_cliente '39053321000', 'Maria Oliveira', 'maria.oliveira@email.com', 3200.00, '1990-03-22', NULL
EXEC sp_inserir_cliente '78414072038', 'Carlos Pereira', 'carlos.pereira@email.com', 2800.00, '1978-11-09', NULL
EXEC sp_inserir_cliente '74648796004', 'Ana Costa', 'ana.costa@email.com', 5100.00, '1992-07-30', NULL
EXEC sp_inserir_cliente '60694721018', 'Fernanda Lima', 'fernanda.lima@email.com', 3900.00, '1988-01-12', NULL
EXEC sp_inserir_cliente '71449581005', 'Rafael Souza', 'rafael.souza@email.com', 4700.00, '1995-09-05', NULL
EXEC sp_inserir_cliente '24749971057', 'Juliana Rocha', 'juliana.rocha@email.com', 5300.00, '1983-04-18', NULL
EXEC sp_inserir_cliente '97781954041', 'Bruno Martins', 'bruno.martins@email.com', 3100.00, '1991-12-25', NULL
EXEC sp_inserir_cliente '14342170000', 'Patrícia Mendes', 'patricia.mendes@email.com', 6000.00, '1986-08-03', NULL
EXEC sp_inserir_cliente '15692198002', 'Eduardo Ramos', 'eduardo.ramos@email.com', 3400.00, '1979-10-27', NULL
EXEC sp_inserir_cliente '88182179092', 'Camila Duarte', 'camila.duarte@email.com', 4200.00, '1993-05-14', NULL
EXEC sp_inserir_cliente '30544373022', 'Rodrigo Teixeira', 'rodrigo.teixeira@email.com', 3800.00, '1980-02-07', NULL
EXEC sp_inserir_cliente '80433435003', 'Luciana Barros', 'luciana.barros@email.com', 4600.00, '1987-06-21', NULL
EXEC sp_inserir_cliente '96267697002', 'Marcelo Nunes', 'marcelo.nunes@email.com', 5000.00, '1994-11-30', NULL
EXEC sp_inserir_cliente '12735354059', 'Vanessa Carvalho', 'vanessa.carvalho@email.com', 3700.00, '1982-03-19', NULL
EXEC sp_inserir_cliente '68054239025', 'Thiago Almeida', 'thiago.almeida@email.com', 3300.00, '1996-07-08', NULL
EXEC sp_inserir_cliente '24249946096', 'Beatriz Freitas', 'beatriz.freitas@email.com', 5500.00, '1989-09-27', NULL
EXEC sp_inserir_cliente '85630690043', 'Fábio Moreira', 'fabio.moreira@email.com', 2900.00, '1977-12-02', NULL
EXEC sp_inserir_cliente '70319156001', 'Débora Santos', 'debora.santos@email.com', 6100.00, '1997-01-20', NULL
EXEC sp_inserir_cliente '73103447078', 'Gustavo Ferreira', 'gustavo.ferreira@email.com', 4400.00, '1984-10-10', NULL

SELECT * FROM Cliente;



SELECT cpf, nome, email, limite_de_credito, CONVERT(CHAR(10), dt_nasciment, 103) AS dtNasc, email FROM cliente;