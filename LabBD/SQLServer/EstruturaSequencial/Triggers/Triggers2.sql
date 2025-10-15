-- Uma empresa vende produtos alimentícios
-- A empresa dá pontos, para seus clientes, que podem ser revertidos em prêmios

CREATE DATABASE ex_triggers_02
GO
USE ex_triggers_02
GO
CREATE TABLE cliente (
codigo INT NOT NULL,
nome VARCHAR(70) NOT NULL
PRIMARY KEY(codigo)
)
GO
CREATE TABLE venda (
codigo_venda INT NOT NULL,
codigo_cliente INT NOT NULL,
valor_total DECIMAL(7,2) NOT NULL
PRIMARY KEY (codigo_venda)
FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo)
)
GO
CREATE TABLE pontos (
codigo_cliente INT NOT NULL,
total_pontos DECIMAL(4,1) NOT NULL
PRIMARY KEY (codigo_cliente)
FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo)
)

INSERT INTO cliente (codigo, nome) VALUES
(1, 'Ana Silva'),
(2, 'Bruno Souza'),
(3, 'Carla Pereira'),
(4, 'Daniel Oliveira'),
(5, 'Elisa Costa'),
(6, 'Fernando Alves'),
(7, 'Gabriela Lima'),
(8, 'Hugo Santos'),
(9, 'Isabela Rocha'),
(10, 'João Fernandes'),
(11, 'Karen Dias'),
(12, 'Lucas Martins'),
(13, 'Mariana Melo'),
(14, 'Nicolas Barros'),
(15, 'Olivia Duarte'),
(16, 'Paulo Henrique'),
(17, 'Queila Ribeiro'),
(18, 'Ricardo Almeida'),
(19, 'Sofia Correia'),
(20, 'Thiago Nunes');
GO
INSERT INTO venda (codigo_venda, codigo_cliente, valor_total) VALUES
(1001, 1, 120.50),
(1002, 2, 75.30),
(1003, 3, 45.00),
(1004, 4, 89.90),
(1005, 5, 150.00),
(1006, 6, 200.00),
(1007, 7, 35.60),
(1008, 8, 180.00),
(1009, 9, 50.00),
(1010, 10, 95.00),
(1011, 11, 130.20),
(1012, 12, 110.00),
(1013, 13, 67.50),
(1014, 14, 145.00),
(1015, 15, 85.30),
(1016, 16, 170.00),
(1017, 17, 25.00),
(1018, 18, 220.00),
(1019, 19, 40.00),
(1020, 20, 60.00),
(1021, 1, 75.00),
(1022, 2, 55.50),
(1023, 3, 90.00),
(1024, 4, 35.40),
(1025, 5, 110.00),
(1026, 6, 85.00),
(1027, 7, 120.00),
(1028, 8, 130.50),
(1029, 9, 95.00),
(1030, 10, 45.00),
(1031, 11, 70.00),
(1032, 12, 100.00),
(1033, 13, 160.00),
(1034, 14, 55.00),
(1035, 15, 125.00),
(1036, 16, 140.00),
(1037, 17, 80.00),
(1038, 18, 75.00),
(1039, 19, 95.00),
(1040, 20, 60.00),
(1041, 1, 150.00),
(1042, 2, 95.00),
(1043, 3, 80.00),
(1044, 4, 120.00),
(1045, 5, 70.00),
(1046, 6, 100.00),
(1047, 7, 130.00),
(1048, 8, 40.00),
(1049, 9, 150.00),
(1050, 10, 110.00);

SELECT * FROM pontos

-- Para não prejudicar a tabela venda, nenhum produto pode ser deletado, mesmo que não venha mais a ser vendido
CREATE TRIGGER t_delvenda
ON venda
INSTEAD OF DELETE
AS
BEGIN
    RAISERROR('Exclusão de registros na tabela venda não é permitida.', 16, 1);
    -- Não executa o DELETE, bloqueando a operação
END;

-- Para não prejudicar os relatórios e a contabilidade, a tabela venda não pode ser alterada.
-- Ao invés de alterar a tabela venda deve-se exibir uma tabela com o nome do último cliente que comprou e o valor 
-- da última compra
CREATE TRIGGER t_updtvenda ON venda FOR UPDATE  
AS  
BEGIN  
	ROLLBACK TRANSACTION
	SELECT c.nome AS nome_cliente, i.valor_total FROM inserted i INNER JOIN cliente c ON i.codigo_cliente = c.codigo;
END   


-- Após a inserção de cada linha na tabela venda, 10% do total deverá ser transformado em pontos.
-- Se o cliente ainda não estiver na tabela de pontos, deve ser inserido automaticamente após sua primeira compra
CREATE TRIGGER t_insrtvenda ON venda FOR INSERT
AS  
BEGIN  
	DECLARE @codigo INT, @pontos INT, @valor DECIMAL(7,2);
	SET @codigo = (SELECT codigo_cliente FROM inserted)
	SET @valor = (SELECT @valor FROM inserted)

	IF((SELECT COUNT(*) FROM pontos WHERE codigo_cliente = @codigo) = 1)
	BEGIN
		SET @pontos = (SELECT total_pontos FROM pontos);
		UPDATE pontos SET total_pontos = @pontos + (@valor * 0.10) WHERE codigo_cliente = @codigo
	END
	ELSE
		INSERT INTO pontos VALUES (@codigo, @valor * 0.10)
END   


-- Se o cliente atingir 1 ponto, deve receber uma mensagem (PRINT SQL Server) dizendo que ganhou e remove esse 1 ponto da tabela 
-- de pontos
CREATE TRIGGER t_insrtupdtpontos
ON pontos
AFTER INSERT, UPDATE
AS
BEGIN
    -- Identificar clientes que atingiram 1 ou mais pontos
    -- Para cada um, imprimir mensagem e remover 1 ponto

    DECLARE @msg NVARCHAR(200);

    -- Atualizar pontos subtraindo 1 para todos que atingiram >= 1 ponto
    UPDATE p
    SET total_pontos = total_pontos - 1
    FROM pontos p
    INNER JOIN inserted i ON p.codigo_cliente = i.codigo_cliente
    WHERE i.total_pontos >= 1;

    -- Gerar mensagens para cada cliente que recebeu prêmio
    SELECT @msg = STRING_AGG('Cliente ' + CAST(codigo_cliente AS VARCHAR(10)) + ' ganhou 1 ponto e recebeu prêmio!', CHAR(13))
    FROM inserted
    WHERE total_pontos >= 1;

    IF @msg IS NOT NULL
        PRINT @msg;
END;
GO
