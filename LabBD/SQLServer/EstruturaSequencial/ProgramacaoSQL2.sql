-- 1) Fazer um algoritmo que leia 1 número e mostre se são múltiplos de 2, 3, 5 ou nenhum deles
DECLARE @numMult INT
SET @numMult = 7

IF(@numMult % 2 = 0)
BEGIN
	PRINT('Número múltiplo de 2')
END

IF(@numMult % 3 = 0)
BEGIN
	PRINT('Número múltiplo de 3')
END

IF(@numMult % 5 = 0)
BEGIN
	PRINT('Número múltiplo de 5')
END

IF(@numMult % 2 != 0 AND @numMult % 3 != 0 AND @numMult % 5 != 0)
BEGIN
	PRINT('Número não múltiplo de 2, 3 ou 5')
END

-- 2) Fazer um algoritmo que leia 3 números e mostre o maior e o menor
DECLARE @num1 INT,
@num2 INT,
@num3 INT

SET @num1 = 4
SET @num2 = 5
SET @num3 = 8

IF(@num1 > @num2 AND @num1 > @num3)
BEGIN
	PRINT('num1 é o maior: ' + CAST(@num1 AS VARCHAR(3)))
	IF(@num2 < @num3)
	BEGIN
		PRINT('num2 é o menor: ' + CAST(@num2 AS VARCHAR(3)))
	END
	ELSE
	BEGIN
		PRINT('num3 é o menor: ' + CAST(@num3 AS VARCHAR(3)))
	END
END
ELSE IF(@num2 > @num1 AND @num2 > @num3)
BEGIN
	PRINT('num2 é o maior: ' + CAST(@num2 AS VARCHAR(3)))
		IF(@num3 < @num1)
	BEGIN
		PRINT('num3 é o menor: ' + CAST(@num3 AS VARCHAR(3)))
	END
	ELSE
	BEGIN
		PRINT('num1 é o menor: ' + CAST(@num1 AS VARCHAR(3)))
	END
END
ELSE
BEGIN
	PRINT('num3 é o maior: ' + CAST(@num3 AS VARCHAR(3)))
		IF(@num1 < @num2)
	BEGIN
		PRINT('num1 é o menor: ' + CAST(@num1 AS VARCHAR(3)))
	END
	ELSE
	BEGIN
		PRINT('num2 é o menor: ' + CAST(@num2 AS VARCHAR(3)))
	END
END

-- 3) Fazer um algoritmo que calcule os 15 primeiros termos da série
-- 1, 1, 2, 3, 5, 8, 13, 21,...
-- E calcule a soma dos 15 termos
DECLARE @nFib INT,
@contFib INT,
@fib INT,
@ant1 INT,
@ant2 INT,
@sumFib INT

SET @nFib = 15
SET @contFib = 1
SET @fib = 0
SET @ant1 = 0
SET @ant2 = 0
SET @sumFib = 0

WHILE(@contFib <= @nFib)
BEGIN
	IF(@contFib <= 2)
	BEGIN
		SET @fib = 1
	END
	ELSE
	BEGIN
		SET @fib = @ant1 + @ant2 
	END

    SET @ant2 = @ant1
	SET @ant1 = @fib
	SET @sumFib = @sumFib + @fib
	SET @contFib = @contFib + 1
END

PRINT(@sumFib)

-- 4) Fazer um algoritmo que separe uma frase, interpretando todas as letras em menor e em menor (Usar as funções UPPER e LOWER)
DECLARE @Frase VARCHAR(100) = 'Fazer um Algoritmo'
DECLARE @Indice INT = 1
DECLARE @Tamanho INT = LEN(@Frase)

CREATE TABLE #Resultado (
    Posicao INT,
    LetraOriginal NCHAR(1),
    LetraMaiuscula NCHAR(1),
    LetraMinuscula NCHAR(1)
)

WHILE @Indice <= @Tamanho
BEGIN
    DECLARE @Letra NCHAR(1) = SUBSTRING(@Frase, @Indice, 1)

    INSERT INTO #Resultado (Posicao, LetraOriginal, LetraMaiuscula, LetraMinuscula)
    VALUES (
        @Indice,
        @Letra,
        UPPER(@Letra),
        LOWER(@Letra)
    )

    SET @Indice = @Indice + 1
END

SELECT * FROM #Resultado

DROP TABLE #Resultado

-- 5) Fazer um algoritmo que inverta uma palavra (Usar a função SUBSTRING)
DECLARE @Palavra NVARCHAR(100) = 'Palavra invertida'
DECLARE @Invertida NVARCHAR(100) = ''
DECLARE @IndiceI INT = LEN(@Palavra)

WHILE @IndiceI > 0
BEGIN
    SET @Invertida = @Invertida + SUBSTRING(@Palavra, @IndiceI, 1)
    SET @IndiceI = @IndiceI- 1
END

PRINT(@Palavra + ' ' + @Invertida)

-- 6) Calcular a tabela abaixo, gerando uma massa de dados, com 100 registros, para fins de teste com as regras estabelecidas (Não usar restrições na criação da tabela)
CREATE TABLE Computador (
    ID INT,
    Marca VARCHAR(40),
    QtdRAM INT,
    TipoHD VARCHAR(10),
    QtdHD INT,
    FreqCPU DECIMAL(7,2)
)

DECLARE @ID INT = 10001
DECLARE @Contador INT = 1

WHILE @Contador <= 100
BEGIN
    DECLARE @Marca VARCHAR(40) = 'Marca ' + CAST(@Contador AS VARCHAR)
    
    DECLARE @QtdRAM INT = 
        CASE FLOOR(RAND() * 4)
            WHEN 0 THEN 2
            WHEN 1 THEN 4
            WHEN 2 THEN 8
            ELSE 16
        END

    DECLARE @TipoHD VARCHAR(10) =
        CASE @ID % 3
            WHEN 0 THEN 'HD'
            WHEN 1 THEN 'SSD'
            ELSE 'NVME'
        END

    DECLARE @QtdHD INT =
        CASE @TipoHD
            WHEN 'HD' THEN 
                CASE FLOOR(RAND() * 3)
                    WHEN 0 THEN 500
                    WHEN 1 THEN 1000
                    ELSE 2000
                END
            WHEN 'SSD' THEN 
                CASE FLOOR(RAND() * 3)
                    WHEN 0 THEN 128
                    WHEN 1 THEN 256
                    ELSE 512
                END
            ELSE 500
        END

    DECLARE @FreqCPU DECIMAL(7,2) = ROUND((RAND() * (3.20 - 1.70)) + 1.70, 2)

    INSERT INTO Computador (ID, Marca, QtdRAM, TipoHD, QtdHD, FreqCPU)
    VALUES (@ID, @Marca, @QtdRAM, @TipoHD, @QtdHD, @FreqCPU)

    SET @ID = @ID + 1
    SET @Contador = @Contador + 1
END