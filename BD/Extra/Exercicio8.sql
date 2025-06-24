CREATE DATABASE Mercado;
USE Mercado;


CREATE TABLE Cliente (
    Codigo INT PRIMARY KEY,
    Nome VARCHAR(100),
    Endereco VARCHAR(150),
    Telefone VARCHAR(20),
    TelefoneComercial VARCHAR(20) NULL
);


CREATE TABLE TiposMercadoria (
    Codigo INT PRIMARY KEY,
    Nome VARCHAR(100)
);


CREATE TABLE Corredores (
    Codigo INT PRIMARY KEY,
    Tipo INT FOREIGN KEY REFERENCES TiposMercadoria(Codigo),
    Nome VARCHAR(100)
);


CREATE TABLE Mercadoria (
    Codigo INT PRIMARY KEY,
    Nome VARCHAR(100),
    Corredor INT FOREIGN KEY REFERENCES Corredores(Codigo),
    Tipo INT FOREIGN KEY REFERENCES TiposMercadoria(Codigo),
    Valor DECIMAL(5,2)
);


CREATE TABLE Compra (
    NotaFiscal INT PRIMARY KEY,
    CodigoCliente INT FOREIGN KEY REFERENCES Cliente(Codigo),
    Valor DECIMAL(7,2)
);

INSERT INTO Cliente (Codigo, Nome, Endereco, Telefone, TelefoneComercial) VALUES
(1, 'Luis Paulo', 'R. Xv de Novembro, 100', '45657878', NULL),
(2, 'Maria Fernanda', 'R. Anhaia, 1098', '27289098', '40040090'),
(3, 'Ana Claudia', 'Av. Voluntários da Pátria, 876', '21346548', NULL),
(4, 'Marcos Henrique', 'R. Pantojo, 76', '51425890', '30394540'),
(5, 'Emerson Souza', 'R. Pedro Álvares Cabral, 97', '44236545', '39389900'),
(6, 'Ricardo Santos', 'Trav. Hum, 10', '98789878', NULL);


INSERT INTO TiposMercadoria (Codigo, Nome) VALUES
(10001, 'Pães'),
(10002, 'Frios'),
(10003, 'Bolacha'),
(10004, 'Clorados'),
(10005, 'Frutas'),
(10006, 'Esponjas'),
(10007, 'Massas'),
(10008, 'Molhos');

INSERT INTO Corredores (Codigo, Tipo, Nome) VALUES
(101, 10001, 'Padaria'),
(102, 10002, 'Calçados'),
(103, 10003, 'Biscoitos'),
(104, 10004, 'Limpeza'),
(105, 10005, 'Frutas'),
(106, 10006, 'Esponjas'),
(107, 10007, 'Congelados');


INSERT INTO Mercadoria (Codigo, Nome, Corredor, Tipo, Valor) VALUES
(1001, 'Pão de Forma', 101, 10001, 3.50),
(1002, 'Presunto', 101, 10002, 2.00),
(1003, 'Cream Cracker', 103, 10003, 4.50),
(1004, 'Água Sanitária', 104, 10004, 6.50),
(1005, 'Maçã', 105, 10005, 0.90),
(1006, 'Palha de Aço', 106, 10006, 1.30),
(1007, 'Lasanha', 107, 10007, 9.70);


INSERT INTO Compra (NotaFiscal, CodigoCliente, Valor) VALUES
(1234, 2, 200.00),
(2345, 4, 156.00),
(3456, 6, 354.00),
(4567, 3, 19.00);

SELECT c.Nome, co.Valor FROM Compra co INNER JOIN Cliente c ON co.CodigoCliente = c.Codigo WHERE c.Nome = 'Luis Paulo';

SELECT c.Nome, co.Valor FROM Compra co INNER JOIN Cliente c ON co.CodigoCliente = c.Codigo WHERE c.Nome = 'Marcos Henrique';

SELECT c.Nome, c.Endereco, c.Telefone, c.TelefoneComercial FROM Compra co INNER JOIN Cliente c ON co.CodigoCliente = c.Codigo WHERE co.NotaFiscal = 4567;

SELECT m.Nome, m.Valor FROM Mercadoria m INNER JOIN TiposMercadoria t ON m.Tipo = t.Codigo WHERE t.Nome = 'Pães';

SELECT c.Nome AS Corredor FROM Mercadoria m INNER JOIN Corredores c ON m.Corredor = c.Codigo WHERE m.Nome = 'Lasanha';

SELECT DISTINCT c.Nome AS Corredor FROM Corredores c INNER JOIN TiposMercadoria t ON c.Tipo = t.Codigo WHERE t.Nome = 'Clorados';