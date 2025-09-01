CREATE DATABASE academia;
USE academia;

CREATE TABLE Aluno(
	Codigo_aluno INT PRIMARY KEY IDENTITY,
	Nome VARCHAR(100)
);
GO
CREATE TABLE Atividade(
	Codigo INT PRIMARY KEY IDENTITY,
	Descricao VARCHAR(200),
	IMC DECIMAL(5,2)
);
GO
CREATE TABLE Atividadesaluno(
	Codigo_aluno INT,
	Atividade INT,
	Altura DECIMAL(3,2),
	Peso DECIMAL(5,2),
	IMC DECIMAL(5,2)
	PRIMARY KEY(Codigo_aluno, Atividade)
	FOREIGN KEY(Codigo_aluno) REFERENCES Aluno(Codigo_aluno),
	FOREIGN KEY(Atividade) REFERENCES Atividade(Codigo)
);
GO
INSERT INTO Atividade (Descricao, IMC)
VALUES 
('Corrida + Step', 18.5),
('Biceps + Costas + Pernas', 24.9),
('Esteira + Biceps + Costas + Pernas', 29.9),
('Bicicleta + Biceps + Costas + Pernas', 34.9),
('Esteira + Bicicleta', 39.9);


CREATE PROCEDURE sp_calcimc (@altura DECIMAL(3,2), @peso DECIMAL(5, 2), @imcres DECIMAL(5, 2) OUTPUT)
AS
	SET @imcres = @peso / @altura

CREATE PROCEDURE sp_atividadealuno(@imcaluno DECIMAL(5, 2), @idatividade INT OUTPUT)
AS
	IF(@imcaluno <= 18.5)
	BEGIN
		SET @idatividade = 1
	END
	ELSE IF(@imcaluno <= 24.9)
	BEGIN
		SET @idatividade = 2
	END
	ELSE IF(@imcaluno <= 29.9)
	BEGIN
		SET @idatividade = 3
	END
	ELSE IF(@imcaluno <= 34.9)
	BEGIN
		SET @idatividade = 4
	END
	ELSE
	BEGIN
		SET @idatividade = 5
	END

CREATE PROCEDURE sp_atividadeimcaluno(@idaln INT, @nomealn VARCHAR(100), @imcaln DECIMAL(5,2), @alturaaln DECIMAL(3, 2),@pesoaln DECIMAL(3,2)) 
AS
	DECLARE @imcatv DECIMAL(5, 2),
	@nomeatv VARCHAR(200),
	@idatv INT,
	@cont INT

	IF(@idaln IS NOT NULL)
	BEGIN
		INSERT INTO Aluno(Nome) VALUES(@nomealn);
		SET @idaln = (SELECT Codigo_aluno FROM Aluno WHERE Nome LIKE @nomealn)
		EXEC sp_calcimc @alturaaln, @pesoaln, @imcaln OUTPUT 
		EXEC sp_atividadealuno @imcaln, @idatv OUTPUT
		INSERT INTO Atividadesaluno VALUES (@idaln, @idatv, @alturaaln, @pesoaln, @imcaln)
	END
	ELSE
	BEGIN
		EXEC sp_calcimc @alturaaln, @pesoaln, @imcaln OUTPUT 
		EXEC sp_atividadealuno @imcaln, @idatv OUTPUT
		UPDATE Atividadesaluno SET Altura = @alturaaln, Peso = @pesoaln, IMC = @imcaln, Atividade = @idatv;
	END