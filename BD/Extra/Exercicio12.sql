
CREATE TABLE Planos (
    CodPlano INT PRIMARY KEY,
    NomePlano NVARCHAR(50),
    ValorPlano DECIMAL(10,2)
);

CREATE TABLE Servicos (
    CodServico INT PRIMARY KEY,
    NomeServico VARCHAR(50),
    ValorServico DECIMAL(10,2)
);

CREATE TABLE Cliente (
    CodCliente INT PRIMARY KEY,
    NomeCliente VARCHAR(50),
    DataInicio DATE
);


CREATE TABLE Contratos (
    CodCliente INT FOREIGN KEY REFERENCES Cliente(CodCliente),
    CodPlano INT FOREIGN KEY REFERENCES Planos(CodPlano),
    CodServico INT FOREIGN KEY REFERENCES Servicos(CodServico),
    Status CHAR(1), -- E: Em Espera, A: Ativo, D: Desativado
    Data DATE,
    PRIMARY KEY (CodCliente, CodPlano, CodServico, Data)
);

INSERT INTO Planos VALUES
(1, '100 Minutos', 80),
(2, '150 Minutos', 130),
(3, '200 Minutos', 160),
(4, '250 Minutos', 220),
(5, '300 Minutos', 260),
(6, '600 Minutos', 350);

INSERT INTO Servicos VALUES
(1, '100 SMS', 10),
(2, 'SMS Ilimitado', 30),
(3, 'Internet 500 MB', 40),
(4, 'Internet 1 GB', 60),
(5, 'Internet 2 GB', 70);

INSERT INTO Cliente VALUES
(1234, 'Cliente A', '2012-10-15'),
(2468, 'Cliente B', '2012-11-20'),
(3702, 'Cliente C', '2012-11-25'),
(4936, 'Cliente D', '2012-12-01'),
(6170, 'Cliente E', '2012-12-18'),
(7404, 'Cliente F', '2013-01-20'),
(8638, 'Cliente G', '2013-01-25');

INSERT INTO Contratos VALUES
(1234, 3, 1, 'E', '2012-10-15'),
(1234, 3, 3, 'E', '2012-10-15'),
(1234, 3, 3, 'A', '2012-10-16'),
(1234, 3, 1, 'A', '2012-10-16'),
(2468, 4, 4, 'E', '2012-11-20'),
(2468, 4, 4, 'A', '2012-11-21'),
(6170, 6, 2, 'E', '2012-12-18'),
(6170, 6, 5, 'E', '2012-12-19'),
(6170, 6, 2, 'A', '2012-12-20'),
(6170, 6, 5, 'A', '2012-12-21'),
(1234, 3, 1, 'D', '2013-01-10'),
(1234, 3, 3, 'D', '2013-01-10'),
(1234, 2, 1, 'E', '2013-01-10'),
(1234, 2, 1, 'A', '2013-01-11'),
(2468, 4, 4, 'D', '2013-01-25'),
(7404, 2, 1, 'E', '2013-01-20'),
(7404, 2, 5, 'E', '2013-01-20'),
(7404, 2, 5, 'A', '2013-01-21'),
(7404, 2, 1, 'A', '2013-01-22'),
(8638, 6, 5, 'E', '2013-01-25'),
(8638, 6, 5, 'A', '2013-01-26'),
(7404, 2, 5, 'D', '2013-02-03');

SELECT c.NomeCliente, p.NomePlano, COUNT(DISTINCT ct.Status) AS EstadosContrato FROM Contratos ct INNER JOIN Cliente c ON ct.CodCliente = c.CodCliente
INNER JOIN Planos p ON ct.CodPlano = p.CodPlano WHERE ct.Status = 'D' GROUP BY c.NomeCliente, p.NomePlano ORDER BY c.NomeCliente;

SELECT c.NomeCliente, p.NomePlano, COUNT(DISTINCT ct.Status) AS EstadosContrato FROM Contratos ct INNER JOIN Cliente c ON ct.CodCliente = c.CodCliente
JOIN Planos p ON ct.CodPlano = p.CodPlano WHERE ct.Status <> 'D' GROUP BY c.NomeCliente, p.NomePlano ORDER BY c.NomeCliente;

SELECT c.NomeCliente,
    p.NomePlano, CAST(
						CASE 
							WHEN (p.ValorPlano + SUM(s.ValorServico)) > 400 THEN (p.ValorPlano + SUM(s.ValorServico)) * 0.92
							WHEN (p.ValorPlano + SUM(s.ValorServico)) BETWEEN 300 AND 400 THEN (p.ValorPlano + SUM(s.ValorServico)) * 0.95
							WHEN (p.ValorPlano + SUM(s.ValorServico)) BETWEEN 200 AND 299.99 THEN (p.ValorPlano + SUM(s.ValorServico)) * 0.97
							ELSE (p.ValorPlano + SUM(s.ValorServico))
							END AS DECIMAL(10,2)
					 ) AS ValorComDesconto
FROM Contratos ct INNER JOIN Cliente c ON ct.CodCliente = c.CodCliente INNER JOIN Planos p ON ct.CodPlano = p.CodPlano INNER JOIN Servicos s ON ct.CodServico = s.CodServico
WHERE ct.Status = 'A' OR ct.Status = 'D' GROUP BY c.NomeCliente, p.NomePlano, p.ValorPlano;