-- 1) Fazer em SQL Server os seguintes algoritmos:

-- a) Dado um número inteiro. Calcule e mostre o seu fatorial. (Não usar entrada superior a 12)

DECLARE @numFatorial INT,
@totalFatorial INT
SET @totalFatorial = 1
SET @numFatorial = 10

WHILE(@numFatorial > 1)
BEGIN
	SET @totalFatorial = @totalFatorial * @numFatorial
	SET @numFatorial = @numFatorial - 1 
END
PRINT @totalFatorial

-- b) Dados A, B, e C de uma equação do 2º grau da fórmula AX²+BX+C=0. Verifique e mostre a existência de raízes reais e se caso exista, calcule e mostre. Caso não existam, exibir mensagem.
DECLARE @a INT,
@b INT,
@c INT,
@delta INT,
@x1 DECIMAL (4,2),
@x2 DECIMAL (4,2)

SET @a = 1
SET @b = -5
SET @c = 4
SET @delta = (@b * @b) -4 * @a * @c

PRINT(@delta)
IF(@delta >= 0)
BEGIN
	PRINT('Raízes existentes')
	SET @x1 = (-@b + SQRT(@delta)) / (2 * @a)
	IF(@delta != 0)
	BEGIN
		SET @x2 = (-@b - SQRT(@delta)) / (2 * @a)
		PRINT('x1 = ' + CAST(@x1 AS VARCHAR(5)) + '; x2 = ' + CAST(@x2 AS VARCHAR(5)))
	END
	ELSE
	BEGIN
		PRINT('x1 e x2 = ' + CAST(@x1 AS VARCHAR(5)))
	END
END
ELSE
BEGIN
	PRINT('Raízes inexistentes')
END



-- c) Calcule e mostre quantos anos serão necessários para que Ana seja maior que Maria sabendo que Ana tem 1,10 m e cresce 3 cm ao ano e Maria tem 1,5 m e cresce 2 cm ao ano.
DECLARE @ana DECIMAL(4,2),
@maria DECIMAL(4,2),
@quantAnos INT

SET @ana = 1.10
SET @maria = 1.50
SET @quantAnos = 0

WHILE(@ana < @maria)
BEGIN
	SET @ana = @ana + 0.03
	SET @maria = @maria + 0.02
	SET @quantAnos = @quantAnos + 1
END
PRINT('Quantidade de anos para Ana ser maior que Maria: ' + CAST(@quantAnos AS VARCHAR(3)) + ' Anos')
	
-- d) Seja a seguinte série: 1, 4, 4, 2, 5, 5, 3, 6, 6, 4, 7, 7, ...
-- Escreva uma aplicação que a escreva N termos
DECLARE @N INT = 10,
@contador INT = 0,
@base INT = 1, 
@termo INT;

WHILE @contador < @N
BEGIN
    IF @contador < @N
    BEGIN
        SET @termo = @base;
        PRINT @termo;
        SET @contador = @contador + 1;
    END

    IF @contador < @N
    BEGIN
        SET @termo = @base + 3;
        PRINT @termo;
        SET @contador = @contador + 1;
    END

    IF @contador < @N
    BEGIN
        SET @termo = @base + 3;
        PRINT @termo;
        SET @contador = @contador + 1;
    END

    SET @base = @base + 1;
END


DECLARE @codigoProduto INT,
@nomeProduto VARCHAR(30),
@valorProduto DECIMAL(7,2),
@vencimentoProduto DATE

SET @codigoProduto = 50001

WHILE(@codigoProduto <= 50001)
BEGIN
	SET @nomeProduto = 'Produto ' + CAST(@codigoProduto - 50000 AS VARCHAR(3))
	SET @valorProduto = (RAND() * 100.00) + 10.00
	SET @vencimentoProduto = DATEDIFF(DAY, GETDATE(), CAST(RAND() * 7 AS INT) + 3)
	INSERT INTO Produto VALUES (@codigoProduto, @nomeProduto, @valorProduto, @vencimentoProduto)
	SET @codigoProduto = @codigoProduto + 1
END

-- f)
DECLARE @codigoLivro INT,
@tituloLivro VARCHAR(30),
@qtdPaginas DECIMAL(7,2),
@qtdEstoque DATE

SET @codigoLivro = 981101

WHILE(@codigoProduto <= 981150)
BEGIN
	SET @tituloLivro = 'Livro ' + CAST(@codigoLivro AS VARCHAR(3))
	SET @qtdPaginas = (CAST(RAND() * 400 AS INT)) + 100
	SET @qtdEstoque = (CAST(RAND() * 20 AS INT)) + 2 
	INSERT INTO Livro VALUES (@codigoLivro, @tituloLivro, @qtdPaginas, @qtdEstoque)
	SET @codigoLivro = @codigoLivro + 1
END

