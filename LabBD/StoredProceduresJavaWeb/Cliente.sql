CREATE DATABASE CadastroCliente;
USE CadastroCliente;

CREATE TABLE Cliente (
    CPF CHAR(11) PRIMARY KEY,
    Nome VARCHAR(100),
    Email VARCHAR(200),
    Limite_de_credito DECIMAL(7,2),
    Dt_Nascimento DATE
);

CREATE PROCEDURE ValidarCPF
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

CREATE PROCEDURE InserirCliente
    @CPF CHAR(11),
    @Nome VARCHAR(100),
    @Email VARCHAR(200),
    @Limite DECIMAL(7,2),
    @Nascimento DATE
AS
BEGIN
    DECLARE @Valido BIT
    EXEC ValidarCPF @CPF, @Valido OUTPUT

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
END

CREATE PROCEDURE AtualizarCliente
    @CPF CHAR(11),
    @Nome VARCHAR(100),
    @Email VARCHAR(200),
    @Limite DECIMAL(7,2),
    @Nascimento DATE
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
END

CREATE PROCEDURE ExcluirCliente
    @CPF CHAR(11)
AS
BEGIN
    DELETE FROM Cliente WHERE CPF = @CPF
END

EXEC InserirCliente '16899562004', 'João Silva', 'joao.silva@email.com', 4500.00, '1985-06-15'
EXEC InserirCliente '39053321000', 'Maria Oliveira', 'maria.oliveira@email.com', 3200.00, '1990-03-22'
EXEC InserirCliente '78414072038', 'Carlos Pereira', 'carlos.pereira@email.com', 2800.00, '1978-11-09'
EXEC InserirCliente '74648796004', 'Ana Costa', 'ana.costa@email.com', 5100.00, '1992-07-30'
EXEC InserirCliente '60694721018', 'Fernanda Lima', 'fernanda.lima@email.com', 3900.00, '1988-01-12'
EXEC InserirCliente '71449581005', 'Rafael Souza', 'rafael.souza@email.com', 4700.00, '1995-09-05'
EXEC InserirCliente '24749971057', 'Juliana Rocha', 'juliana.rocha@email.com', 5300.00, '1983-04-18'
EXEC InserirCliente '97781954041', 'Bruno Martins', 'bruno.martins@email.com', 3100.00, '1991-12-25'
EXEC InserirCliente '14342170000', 'Patrícia Mendes', 'patricia.mendes@email.com', 6000.00, '1986-08-03'
EXEC InserirCliente '15692198002', 'Eduardo Ramos', 'eduardo.ramos@email.com', 3400.00, '1979-10-27'
EXEC InserirCliente '88182179092', 'Camila Duarte', 'camila.duarte@email.com', 4200.00, '1993-05-14'
EXEC InserirCliente '30544373022', 'Rodrigo Teixeira', 'rodrigo.teixeira@email.com', 3800.00, '1980-02-07'
EXEC InserirCliente '80433435003', 'Luciana Barros', 'luciana.barros@email.com', 4600.00, '1987-06-21'
EXEC InserirCliente '96267697002', 'Marcelo Nunes', 'marcelo.nunes@email.com', 5000.00, '1994-11-30'
EXEC InserirCliente '12735354059', 'Vanessa Carvalho', 'vanessa.carvalho@email.com', 3700.00, '1982-03-19'
EXEC InserirCliente '68054239025', 'Thiago Almeida', 'thiago.almeida@email.com', 3300.00, '1996-07-08'
EXEC InserirCliente '24249946096', 'Beatriz Freitas', 'beatriz.freitas@email.com', 5500.00, '1989-09-27'
EXEC InserirCliente '85630690043', 'Fábio Moreira', 'fabio.moreira@email.com', 2900.00, '1977-12-02'
EXEC InserirCliente '70319156001', 'Débora Santos', 'debora.santos@email.com', 6100.00, '1997-01-20'
EXEC InserirCliente '73103447078', 'Gustavo Ferreira', 'gustavo.ferreira@email.com', 4400.00, '1984-10-10'

SELECT * FROM Cliente;