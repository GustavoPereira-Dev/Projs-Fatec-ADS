CREATE DATABASE Farmacia;

CREATE TABLE Medicamento (
    Codigo INT PRIMARY KEY,
    Nome VARCHAR(100),
    Apresentacao VARCHAR(100),
    UnidadeCadastro VARCHAR(50),
    PrecoProposto DECIMAL(6,3)
);


CREATE TABLE Cliente (
    CPF CHAR(11) PRIMARY KEY,
    Nome VARCHAR(100),
    Rua VARCHAR(100),
    Numero INT,
    Bairro VARCHAR(100),
    Telefone VARCHAR(15)
);


CREATE TABLE Venda (
    NotaFiscal INT,
    CPF_Cliente CHAR(11),
    Codigo_Medicamento INT,
    Quantidade INT,
    ValorTotal DECIMAL(6,2),
    Data DATE,
    PRIMARY KEY (NotaFiscal, Codigo_Medicamento),
    FOREIGN KEY (CPF_Cliente) REFERENCES Cliente(CPF),
    FOREIGN KEY (Codigo_Medicamento) REFERENCES Medicamento(Codigo)
);

INSERT INTO Medicamento (Codigo, Nome, Apresentacao, UnidadeCadastro, PrecoProposto) VALUES
(1, 'Acetato de medroxiprogesterona', '150 mg/ml', 'Ampola', 6.700),
(2, 'Aciclovir', '200mg/comp.', 'Comprimido', 0.280),
(3, 'Ácido Acetilsalicílico', '500mg/comp.', 'Comprimido', 0.035),
(4, 'Ácido Acetilsalicílico', '100mg/comp.', 'Comprimido', 0.030),
(5, 'Ácido Fólico', '5mg/comp.', 'Comprimido', 0.054),
(6, 'Albendazol', '400mg/comp. mastigável', 'Comprimido', 0.560),
(7, 'Alopurinol', '100mg/comp.', 'Comprimido', 0.080),
(8, 'Amiodarona', '200mg/comp.', 'Comprimido', 0.200),
(9, 'Amitriptilina(Cloridrato)', '25mg/comp.', 'Comprimido', 0.220),
(10, 'Amoxicilina', '500mg/cáps.', 'Cápsula', 0.190);

INSERT INTO Cliente (CPF, Nome, Rua, Numero, Bairro, Telefone) VALUES
('34390898700', 'Maria Zélia', 'Anhaia', 65, 'Barra Funda', '92103762'),
('21345986290', 'Roseli Silva', 'Xv. De Novembro', 987, 'Centro', '82198763'),
('86927981825', 'Carlos Campos', 'Voluntários da Pátria', 1276, 'Santana', '98172361'),
('31098120900', 'João Perdizes', 'Carlos de Campos', 90, 'Pari', '61982371');

INSERT INTO Venda (NotaFiscal, CPF_Cliente, Codigo_Medicamento, Quantidade, ValorTotal, Data) VALUES
(31501, '86927981825', 10, 3, 0.57, '2020-11-01'),
(31501, '86927981825', 2, 10, 2.80, '2020-11-01'),
(31501, '86927981825', 5, 30, 1.05, '2020-11-01'),
(31501, '86927981825', 8, 30, 6.60, '2020-11-01'),
(31502, '34390898700', 8, 15, 3.00, '2020-11-01'),
(31502, '34390898700', 2, 10, 2.80, '2020-11-01'),
(31502, '34390898700', 9, 10, 2.20, '2020-11-01'),
(31503, '31098120900', 1, 20, 134.00, '2020-11-02');

SELECT Nome, Apresentacao, CASE 
								WHEN UnidadeCadastro = 'Comprimido' THEN 'Comp.'
								ELSE UnidadeCadastro
							END AS Unidade,
PrecoProposto FROM Medicamento WHERE Codigo NOT IN ( SELECT DISTINCT Codigo_Medicamento FROM Venda);

SELECT DISTINCT c.Nome FROM Cliente c INNER JOIN Venda v ON c.CPF = v.CPF_Cliente INNER JOIN Medicamento m ON v.Codigo_Medicamento = m.Codigo WHERE m.Nome = 'Amiodarona';

SELECT v.CPF_Cliente AS CPF, c.Rua + ', ' + CAST(c.Numero AS VARCHAR) + ' - ' + c.Bairro AS Endereco, m.Nome AS NomeRemedio, m.Apresentacao, m.UnidadeCadastro,
m.PrecoProposto, v.Quantidade, v.ValorTotal FROM Venda v INNER JOIN Cliente c ON v.CPF_Cliente = c.CPF INNER JOIN Medicamento m ON v.Codigo_Medicamento = m.Codigo
WHERE c.Nome = 'Maria Zélia';

SELECT DISTINCT FORMAT(v.Data, 'dd/MM/yyyy') AS DataCompra FROM Venda v INNER JOIN Cliente c ON v.CPF_Cliente = c.CPF WHERE c.Nome = 'Carlos Campos';

UPDATE Medicamento SET Nome = 'Cloridrato de Amitriptilina' WHERE Nome = 'Amitriptilina(Cloridrato)';