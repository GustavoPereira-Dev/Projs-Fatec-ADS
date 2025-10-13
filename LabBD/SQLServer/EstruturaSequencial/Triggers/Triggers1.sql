-- 1) Considere um quadrangular final de times de volei com 4 times
-- Time 1, Time 2 Time 3 e Time 4
-- Todos jogarão contra todos.
-- Os resultados dos jogos serão armazenados em uma tabela

CREATE DATABASE Volei;
GO
USE Volei;
GO
CREATE TABLE times(
	codigo INT,
	nome VARCHAR(100)
	PRIMARY KEY(codigo)
);
GO
CREATE TABLE jogos(
	codigo_time_a INT,
	codigo_time_b INT,
	set_time_a INT,
	set_time_b INT
	FOREIGN KEY(codigo_time_a) REFERENCES times(codigo),
	FOREIGN KEY(codigo_time_b) REFERENCES times(codigo)
);

INSERT INTO times VALUES
(1, 'Time 1'),
(2, 'Time 2'),
(3, 'Time 3'),
(4, 'Time 4');

-- Considera-se vencedor o time que fez 3 de 5 sets.
-- Se a vitória for por 3 x 2, o time vencedor ganha 2 pontos e o time perdedor ganha 1.
-- Se a vitória for por 3 x 0 ou 3 x 1, o vencedor ganha 3 pontos e o perdedor, 0.

INSERT INTO jogos VALUES
(1, 2, 3, 2),
(1, 3, 3, 0),
(1, 4, 1, 3),
(2, 3, 2, 3),
(2, 4, 3, 1),
(3, 4, 0, 3);

-- Fazer uma UDF que apresente:
-- (Nome Time | Total Pontos | Total Sets Ganhos | Total Sets Perdidos | Set Average (Ganhos - perdidos))
CREATE FUNCTION fn_ranking_times()
RETURNS @tabela TABLE(
	nome_time VARCHAR(100),
	total_pontos INT,
	total_sets_ganhos INT,
	total_sets_perdidos INT,
	set_average INT
)
AS
BEGIN

	INSERT INTO @tabela
		SELECT
			t.nome AS nome_time,
			ISNULL(SUM(
				CASE
					-- Vencedor 3x2 ganha 2 pontos
					WHEN ( (j.set_time_a = 3 AND j.set_time_b = 2 AND j.codigo_time_a = t.codigo)
						OR (j.set_time_b = 3 AND j.set_time_a = 2 AND j.codigo_time_b = t.codigo) ) THEN 2
					-- Vencedor 3x0 ou 3x1 ganha 3 pontos
					WHEN ( (j.set_time_a = 3 AND j.set_time_b IN (0,1) AND j.codigo_time_a = t.codigo)
						OR (j.set_time_b = 3 AND j.set_time_a IN (0,1) AND j.codigo_time_b = t.codigo) ) THEN 3
					-- Perdedor 2x3 ganha 1 ponto
					WHEN ( (j.set_time_a = 2 AND j.set_time_b = 3 AND j.codigo_time_a = t.codigo)
						OR (j.set_time_b = 2 AND j.set_time_a = 3 AND j.codigo_time_b = t.codigo) ) THEN 1
					-- Derrota 0 pontos
					ELSE 0
				END
			), 0) AS total_pontos,
			ISNULL(SUM(
				CASE
					WHEN j.codigo_time_a = t.codigo THEN j.set_time_a
					WHEN j.codigo_time_b = t.codigo THEN j.set_time_b
					ELSE 0
				END
			), 0) AS total_sets_ganhos,
			ISNULL(SUM(
				CASE
					WHEN j.codigo_time_a = t.codigo THEN j.set_time_b
					WHEN j.codigo_time_b = t.codigo THEN j.set_time_a
					ELSE 0
				END
			), 0) AS total_sets_perdidos,
			ISNULL(SUM(
				CASE
					WHEN j.codigo_time_a = t.codigo THEN j.set_time_a - j.set_time_b
					WHEN j.codigo_time_b = t.codigo THEN j.set_time_b - j.set_time_a
					ELSE 0
				END
			), 0) AS set_average
		FROM
			times t
		LEFT JOIN
			jogos j ON t.codigo IN (j.codigo_time_a, j.codigo_time_b)
		GROUP BY
			t.nome

	RETURN
END

SELECT * FROM dbo.fn_ranking_times()
ORDER BY total_pontos DESC, set_average DESC;

-- Fazer uma trigger que verifique se os inserts dos sets estão corretos (Máximo 5 sets por jogo, sendo
-- que o vencedor tem no máximo 3 sets)
CREATE TRIGGER t_validar_sets
ON jogos
INSTEAD OF INSERT, UPDATE
AS
BEGIN
    -- Valida as linhas inseridas/atualizadas
    IF EXISTS (
        SELECT 1 FROM inserted i
        WHERE 
            (i.set_time_a + i.set_time_b) > 5 OR
            i.set_time_a = i.set_time_b OR
            NOT (i.set_time_a = 3 OR i.set_time_b = 3) OR
            (i.set_time_a = 3 AND i.set_time_b > 2) OR
            (i.set_time_b = 3 AND i.set_time_a > 2)
    )
    BEGIN
        RAISERROR ('Sets inválidos: total máximo 5, vencedor com 3 sets, perdedor no máximo 2, sem empate.', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END

    -- Insert para novas linhas
    INSERT INTO jogos (codigo_time_a, codigo_time_b, set_time_a, set_time_b)
    SELECT codigo_time_a, codigo_time_b, set_time_a, set_time_b
    FROM inserted i
    WHERE NOT EXISTS (
        SELECT 1 FROM jogos j
        WHERE j.codigo_time_a = i.codigo_time_a AND j.codigo_time_b = i.codigo_time_b
    );

    -- Update para linhas já existentes
    UPDATE j
    SET j.set_time_a = i.set_time_a,
        j.set_time_b = i.set_time_b
    FROM jogos j
    INNER JOIN inserted i ON j.codigo_time_a = i.codigo_time_a AND j.codigo_time_b = i.codigo_time_b;
END;
