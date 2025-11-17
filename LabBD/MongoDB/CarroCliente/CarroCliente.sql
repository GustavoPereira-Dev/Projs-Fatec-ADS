CREATE DATABASE exsqlmongo
GO
USE exsqlmongo
GO
CREATE TABLE carro (
placa CHAR(7) NOT NULL,
marca VARCHAR(10) NOT NULL,
modelo VARCHAR(15) NOT NULL,
cor VARCHAR(20) NOT NULL,
ano INT NOT NULL
PRIMARY KEY (placa)
)
GO
CREATE TABLE cliente (
carro CHAR(7) NOT NULL,
nome VARCHAR(50) NOT NULL,
logradouro VARCHAR(100) NOT NULL,
numero INT NOT NULL,
bairro VARCHAR(60) NOT NULL,
telefone VARCHAR(8) NOT NULL
PRIMARY KEY (carro)
FOREIGN KEY (carro) REFERENCES carro(placa)
)
GO
INSERT INTO carro VALUES
('AFT9087', 'VW', 'Gol', 'Preto', 2007),
('DXO9876', 'Ford','Ka', 'Azul',2000),
('EGT4631', 'Renault', 'Clio','Verde', 2004),
('LKM7380', 'Fiat','Palio', 'Prata', 1997),
('BCD7521', 'Ford','Fiesta', 'Preto', 1999)
GO
INSERT INTO cliente (nome, logradouro, numero, bairro, telefone, carro) VALUES
('João Alves', 'R. Pereira Barreto', 1258, 'Jd. Oliveiras', '21549658', 'DXO9876'),
('Ana Maria', 'R. 7 de Setembro', 259, 'Centro', '96588541', 'LKM7380'),
('Clara Oliveira', 'Av. Nações Unidas', 10254, 'Pinheiros', '24589658', 'EGT4631'),
('José Simões', 'R. XV de Novembro', 36, 'Água Branca', '78952459', 'BCD7521'),
('Paula Rocha', 'R. Anhaia', 548, 'Barra Funda', '69582548', 'AFT9087')
GO
SELECT * FROM carro FOR JSON PATH
SELECT * FROM cliente FOR JSON PATH