USE PessoaRelatorio;

-- Criação da tabela Pessoa
CREATE TABLE pessoa (
    id INT NOT NULL,
    nome VARCHAR(60) NOT NULL,
    altura INT NOT NULL,
    peso INT NOT NULL,
    PRIMARY KEY (id)
);
GO

INSERT INTO pessoa (id, nome, altura, peso) VALUES
(1, 'Miguel', 184, 65),
(2, 'Arthur', 181, 85),
(3, 'Gael', 200, 65),
(4, 'Théo', 162, 102),
(5, 'Heitor', 202, 81),
(6, 'Ravi', 150, 91),
(7, 'Davi', 164, 140),
(8, 'Bernardo', 155, 125),
(9, 'Noah', 155, 78),
(10, 'Gabriel', 191, 108),
(11, 'Samuel', 170, 99),
(12, 'Pedro', 184, 85),
(13, 'Anthony', 205, 116),
(14, 'Isaac', 163, 120),
(15, 'Benício', 205, 87),
(16, 'Benjamin', 171, 95),
(17, 'Matheus', 186, 87),
(18, 'Lucas', 178, 134),
(19, 'Joaquim', 152, 136),
(20, 'Nicolas', 203, 116),
(21, 'Lucca', 170, 83),
(22, 'Lorenzo', 198, 76),
(23, 'Henrique', 192, 66),
(24, 'João Miguel', 203, 120),
(25, 'Rafael', 183, 87),
(26, 'Henry', 161, 74),
(27, 'Murilo', 172, 75),
(28, 'Levi', 164, 123),
(29, 'Guilherme', 204, 79),
(30, 'Vicente', 198, 137),
(31, 'Felipe', 168, 70),
(32, 'Bryan', 170, 95),
(33, 'Matteo', 195, 74),
(34, 'Bento', 179, 113),
(35, 'João Pedro', 152, 112),
(36, 'Pietro', 190, 105),
(37, 'Leonardo', 182, 92),
(38, 'Daniel', 198, 83),
(39, 'Gustavo', 205, 95),
(40, 'Pedro Henrique', 192, 75);
GO
CREATE ALTER FUNCTION fn_consultaIMC (
    @imc_min DECIMAL(5, 2),
    @imc_max DECIMAL(5, 2)
)
RETURNS @Resultado TABLE (
    id INT PRIMARY KEY,
    nome VARCHAR(60),
    altura_metros DECIMAL(3, 2),
    peso_kg INT,
    imc DECIMAL(5, 2),           
    situacao VARCHAR(50)
)
AS
BEGIN
    INSERT INTO @Resultado (id, nome, altura_metros, peso_kg, imc, situacao)
    
    SELECT
        id,
        nome,
        altura_metros,
        peso_kg,
        CONVERT(DECIMAL(5, 2), imc) AS imc,
        (CASE 
            WHEN CalculoIMC.imc < 16 THEN 'Magreza grave'
            WHEN CalculoIMC.imc BETWEEN 16 AND 16.9 THEN 'Magreza moderada'
            WHEN CalculoIMC.imc BETWEEN 17 AND 18.5 THEN 'Magreza leve'
            WHEN CalculoIMC.imc BETWEEN 18.6 AND 24.9 THEN 'Peso ideal'
            WHEN CalculoIMC.imc BETWEEN 25 AND 29.9 THEN 'Sobrepeso'
            WHEN CalculoIMC.imc BETWEEN 30 AND 34.9 THEN 'Obesidade grau I'
            WHEN CalculoIMC.imc BETWEEN 35 AND 39.9 THEN 'Obesidade grau II ou severa'
            WHEN CalculoIMC.imc >= 40 THEN 'Obesidade grau III ou mórbida'
            ELSE 'N/A'
        END) AS situacao
    FROM (
        SELECT
            PreCalculo.id,
            PreCalculo.nome,
            PreCalculo.peso AS peso_kg,
            PreCalculo.altura_metros,
            (PreCalculo.peso / POWER(PreCalculo.altura_metros, 2)) AS imc
        FROM (
            SELECT
                id,
                nome,
                peso,
                (CONVERT(DECIMAL(5, 2), altura) / 100.0) AS altura_metros
            FROM
                pessoa
        ) AS PreCalculo
    ) AS CalculoIMC
    WHERE
        CalculoIMC.imc >= @imc_min AND CalculoIMC.imc <= @imc_max;
    
    RETURN;
END;

SELECT * FROM fn_consultaIMC(25.0, 29.9) ORDER BY imc;