CREATE DATABASE Agencia;
USE Agencia;

CREATE TABLE ContaBancaria (
    num_conta INT PRIMARY KEY,
    nome_cliente VARCHAR(255) NOT NULL,
    saldo FLOAT NOT NULL DEFAULT 0
);
GO

CREATE TABLE ContaPoupanca (
    num_conta INT PRIMARY KEY,
    dia_rendimento INT NOT NULL,
    
    CONSTRAINT FK_Poupanca_Conta FOREIGN KEY (num_conta)
        REFERENCES ContaBancaria(num_conta)
        ON DELETE CASCADE
);
GO

CREATE TABLE ContaEspecial (
    num_conta INT PRIMARY KEY,
    limite FLOAT NOT NULL DEFAULT 0,
    
    CONSTRAINT FK_Especial_Conta FOREIGN KEY (num_conta)
        REFERENCES ContaBancaria(num_conta)
        ON DELETE CASCADE
);
GO

CREATE PROCEDURE sp_depositar (
    @num_conta INT,
    @valor FLOAT
)
AS
BEGIN
    SET NOCOUNT ON;

    IF @valor <= 0
    BEGIN
        RAISERROR('O valor do depósito deve ser positivo.', 16, 1);
        RETURN;
    END;

    UPDATE ContaBancaria
    SET saldo = saldo + @valor
    WHERE num_conta = @num_conta;
END;
GO

CREATE PROCEDURE sp_sacar (
    @num_conta INT,
    @valor FLOAT
)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @saldo_atual FLOAT;
    DECLARE @limite_conta FLOAT;
    DECLARE @is_poupanca BIT = 0;
    DECLARE @is_especial BIT = 0;

    IF @valor <= 0
    BEGIN
        RAISERROR('O valor do saque deve ser positivo.', 16, 1);
        RETURN;
    END;

    SELECT 
        @saldo_atual = b.saldo,

        @limite_conta = ISNULL(e.limite, 0),

        @is_poupanca = CASE WHEN p.num_conta IS NOT NULL THEN 1 ELSE 0 END,
        @is_especial = CASE WHEN e.num_conta IS NOT NULL THEN 1 ELSE 0 END
    FROM 
        ContaBancaria b
    LEFT JOIN 
        ContaPoupanca p ON b.num_conta = p.num_conta
    LEFT JOIN 
        ContaEspecial e ON b.num_conta = e.num_conta
    WHERE 
        b.num_conta = @num_conta;

    IF @saldo_atual IS NULL
    BEGIN
        RAISERROR('Conta não encontrada.', 16, 1);
        RETURN;
    END;

    -- Regra 1: Conta Poupança (@is_poupanca = 1)
    IF @is_poupanca = 1
    BEGIN
        IF (@saldo_atual - @valor) < 0
        BEGIN
            RAISERROR('Saldo insuficiente para saque na Conta Poupança.', 16, 1);
            RETURN;
        END;
    END
    -- Regra 2: Conta Especial (@is_especial = 1)
    ELSE IF @is_especial = 1
    BEGIN
        -- Verifica se o saque ultrapassa o saldo + limite
        IF (@saldo_atual - @valor) < -@limite_conta
        BEGIN
            RAISERROR('Valor do saque ultrapassa o limite da Conta Especial.', 16, 1);
            RETURN;
        END;
    END
    -- Regra 3: (Opcional) ContaBancaria "base" (nem poupança, nem especial)
    ELSE
    BEGIN
        IF (@saldo_atual - @valor) < 0
        BEGIN
             RAISERROR('Saldo insuficiente.', 16, 1);
             RETURN;
        END;
    END;

    -- Se todas as regras passaram, efetua o saque (só mexe na tabela base)
    UPDATE ContaBancaria
    SET saldo = saldo - @valor
    WHERE num_conta = @num_conta;

    PRINT 'Saque realizado com sucesso.';
END;
GO

CREATE PROCEDURE sp_calcularrendimento (
    @taxa_rendimento FLOAT -- Ex: 0.005 para 0.5%
)
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE b
    SET b.saldo = b.saldo * (1 + @taxa_rendimento)
    FROM 
        ContaBancaria b
    INNER JOIN 
        ContaPoupanca p ON b.num_conta = p.num_conta;
END;
GO

CREATE TRIGGER t_insrtsaque
ON ContaBancaria
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    IF EXISTS (SELECT 1 FROM inserted i JOIN deleted d ON i.num_conta = d.num_conta WHERE i.saldo < d.saldo)
    BEGIN
        
        -- Regra 1: Verifica violações de Conta Poupança
        IF EXISTS (
            SELECT 1
            FROM inserted i
            INNER JOIN ContaPoupanca p ON i.num_conta = p.num_conta -- Garante que é poupança
            WHERE i.saldo < 0 -- Regra da poupança
        )
        BEGIN
            ROLLBACK TRANSACTION;
            RAISERROR ('Saldo da Conta Poupança não pode ficar negativo.', 16, 1);
            RETURN;
        END;

        -- Regra 2: Verifica violações de Conta Especial
        IF EXISTS (
            SELECT 1
            FROM inserted i
            INNER JOIN ContaEspecial e ON i.num_conta = e.num_conta -- Garante que é especial
            WHERE i.saldo < -e.limite -- Regra da conta especial
        )
        BEGIN
            ROLLBACK TRANSACTION;
            RAISERROR ('Saque ultrapassa o limite da Conta Especial.', 16, 1);
            RETURN;
        END;
    END;
END;
GO


CREATE FUNCTION f_saldodisponivel (
    @num_conta INT
)
RETURNS FLOAT
AS
BEGIN
    DECLARE @saldo_atual FLOAT;
    DECLARE @limite_conta FLOAT;

    -- Pega o saldo da tabela base
    SELECT @saldo_atual = saldo
    FROM ContaBancaria
    WHERE num_conta = @num_conta;

    -- Pega o limite (se existir) da tabela especial
    SELECT @limite_conta = limite
    FROM ContaEspecial
    WHERE num_conta = @num_conta;

    -- Se não encontrou limite, é 0
    IF @limite_conta IS NULL
        SET @limite_conta = 0;

    -- Retorna saldo + limite (que será 0 para contas poupança)
    RETURN @saldo_atual + @limite_conta;
END;
GO