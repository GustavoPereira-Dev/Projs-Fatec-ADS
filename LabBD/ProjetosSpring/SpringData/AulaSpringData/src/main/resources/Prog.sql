CREATE DATABASE aulaspringdata20252
GO
USE aulaspringdata20252

CREATE PROCEDURE [dbo].[sp_depto_sigla](@codigo INT, @sigla VARCHAR(3) OUTPUT)
AS
	DECLARE @cont	INT,
			@nome	VARCHAR(100)
	SET @cont = (SELECT COUNT(codigo) FROM depto WHERE codigo = @codigo AND sigla IS NULL)
	IF (@cont = 1)
	BEGIN
		SET @nome = (SELECT nome_depto FROM depto WHERE codigo = @codigo AND sigla IS NULL)
		SET @sigla = SUBSTRING(@nome, 1, 1) + SUBSTRING(@nome, LEN(@nome) - 1, 2)
		UPDATE depto SET sigla = @sigla WHERE codigo = @codigo AND sigla IS NULL
	END
	
CREATE FUNCTION [dbo].[fn_sigla_depto](@codigo INT)
RETURNS @tabela TABLE (
codigo			INT,
nome_depto		VARCHAR(20),
sigla			VARCHAR(3)
)
AS
BEGIN
	DECLARE @cont	INT
	SELECT @cont = COUNT(codigo) FROM depto WHERE codigo = @codigo

	IF (@cont = 1)
	BEGIN
		INSERT INTO @tabela 
			SELECT codigo, nome_depto, sigla FROM depto WHERE codigo = @codigo

		UPDATE @tabela 
		SET sigla = SUBSTRING(nome_depto, 1, 1) + SUBSTRING(nome_depto, LEN(nome_depto) - 1, 2) 
		WHERE sigla IS NULL 
	END

	RETURN
END

CREATE FUNCTION [dbo].[fn_depto]()
RETURNS @tabela TABLE (
codigo			INT,
nome_depto		VARCHAR(20),
sigla			VARCHAR(3)
)
AS
BEGIN
	INSERT INTO @tabela 
		SELECT codigo, nome_depto, sigla FROM depto

	UPDATE @tabela 
	SET sigla = SUBSTRING(nome_depto, 1, 1) + SUBSTRING(nome_depto, LEN(nome_depto) - 1, 2) 
	WHERE sigla IS NULL 

	RETURN
END
