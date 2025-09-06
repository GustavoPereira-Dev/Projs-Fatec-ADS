USE aulamaven

/*STORED PROCEDURES - Procedimentos Armazenados

Entrada de n parâmetros
Saída escalar de n parâmetros
Aceitam DDL
Aceitam DML
Aceitam Raise Error
Aceitam Chamadas de Stored Procedures
Permitem fazer tratamento de regras de negócio diretamente no BD

Criar procedure:

CREATE PROCEDURE sp_nome (variáveis de entrada - com tipo)
AS
.
.
.

Chamar Procedure (CALL)
SQL Server	- EXEC sp_nome param
Oracle		- EXECUTE sp_nome param
MySQL		- CALL sp_nome param
*/
/*Exemplo 1 - Operação com 2 parâmetros de entrada e 1 de saída*/
CREATE PROCEDURE sp_teste1(@num1 INT, @num2 INT, @res INT OUTPUT)
AS
	SET @res = @num1 + @num2
--	PRINT (@res)

DECLARE @resout1	INT
EXEC sp_teste1 10, 20, @resout1 OUTPUT
PRINT @resout1

/*
Criar uma SP que trate regras de negócio de Pessoa e permita fazer
as operações de Insert (I), Update (U) ou Delete (D) já com
regras tratadas
*/

--Pessoa deve ser maior de idade para fazer insert ou update
CREATE PROCEDURE sp_validaidade(@nascimento DATE, 
								@valido BIT OUTPUT)
AS
	DECLARE @idade	INT
	SET @idade = (SELECT DATEDIFF(DAY, @nascimento, GETDATE())) / 365
--	PRINT (@idade)

	IF (@idade >= 18)
	BEGIN
		SET @valido = 1
	END
	ELSE
	BEGIN
		SET @valido = 0
	END

DECLARE @valid1	BIT
EXEC sp_validaidade '2000-01-01', @valid1 OUTPUT
PRINT (@valid1)

DECLARE @valid2	BIT
EXEC sp_validaidade '2015-01-01', @valid2 OUTPUT
PRINT (@valid2)

DECLARE @valid3	BIT
EXEC sp_validaidade NULL, @valid3 OUTPUT
PRINT (@valid3)


CREATE PROCEDURE sp_pessoa(@op CHAR(1), @id INT, @nome VARCHAR(100),
						   @nascimento DATE, @email VARCHAR(80),
						   @saida VARCHAR(100) OUTPUT)
AS
	DECLARE @valido_idade	BIT
	IF (UPPER(@op) = 'D' AND @id IS NOT NULL)
	BEGIN
		DELETE pessoa WHERE id = @id
		SET @saida = 'Pessoa #id '+CAST(@id AS VARCHAR(5)) + ' excluído com sucesso !'
	END
	ELSE
	BEGIN
		IF (UPPER(@op) = 'D' AND @id IS NULL)
		BEGIN
			RAISERROR('Operação requer #ID válido', 16, 1)
		END
		ELSE
		BEGIN
			EXEC sp_validaidade @nascimento, @valido_idade OUTPUT
			IF (@valido_idade = 1)
			BEGIN
				IF (UPPER(@op) = 'I')
				BEGIN
					INSERT INTO pessoa VALUES
					(@id, @nome, @nascimento, @email)

					SET @saida = @nome + ' inserido(a) com sucesso !'
				END
				ELSE
				BEGIN
					IF (UPPER(@op) = 'U')
					BEGIN
						UPDATE pessoa
						SET nome = @nome, nascimento = @nascimento, email = @email
						WHERE id = @id
					
						SET @saida = @nome + ' modificado(a) com sucesso !'
					END
					ELSE
					BEGIN
						RAISERROR('Codigo de operação inválido', 16, 1)
					END
				END
			END
			ELSE
			BEGIN
				RAISERROR('Data de nascimento inválida', 16, 1)
			END
		END
	END

--Erro de exclusão
DECLARE @out1 VARCHAR(100)
EXEC sp_pessoa 'D', NULL, NULL, NULL, NULL, @out1 OUTPUT
PRINT (@out1)

--Erro de operação (op)
DECLARE @out2 VARCHAR(100)
EXEC sp_pessoa 'H', 1000, 'Azukau', '1990-01-01', NULL, @out2 OUTPUT
PRINT (@out2)

DECLARE @out3 VARCHAR(100)
EXEC sp_pessoa 'I', 1000, 'Azukau', '1990-01-01', 
			   'azukau@email.com', @out3 OUTPUT
PRINT (@out3)

DECLARE @out4 VARCHAR(100)
EXEC sp_pessoa 'U', 1000, 'Azukau Wiel', '1990-01-01', 
			   'azukau@email.com', @out4 OUTPUT
PRINT (@out4)

DECLARE @out5 VARCHAR(100)
EXEC sp_pessoa 'D', 1000, NULL, NULL, NULL, @out5 OUTPUT
PRINT (@out5)

SELECT * FROM pessoa