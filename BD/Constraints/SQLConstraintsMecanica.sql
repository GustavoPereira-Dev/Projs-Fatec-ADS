CREATE DATABASE Mecanica;
USE Mecanica;

CREATE TABLE Cliente(
	ID int IDENTITY(3401, 15),
	Nome varchar(100),
	Logradouro varchar(200),
	Numero int CHECK(Numero > 0),
	CEP char(8) CHECK(LEN(CEP) = 8),
	Complemento varchar(255)
	PRIMARY KEY(ID)
);
GO
CREATE TABLE Telefone_Cliente(
	ClienteID int,
	Telefone varchar(11) CHECK(LEN(Telefone) = 10 OR LEN(Telefone) = 11) 
	PRIMARY KEY(ClienteID, Telefone)
	FOREIGN KEY(ClienteID) REFERENCES Cliente(ID)
);
GO
CREATE TABLE Veiculo(
	Placa char(7),
	Marca varchar(30),
	Modelo varchar(30),
	Cor varchar(30),
	Ano_Fabricacao int CHECK(Ano_Fabricacao > 1997),
	Ano_Modelo int CHECK(Ano_Modelo > 1997),
	Data_Aquisicao int,
	ClienteID int
	PRIMARY KEY(Placa),
	FOREIGN KEY(ClienteID) REFERENCES Cliente(ID),
	CONSTRAINT chk_fabricacao_modelo CHECK(Ano_Modelo - Ano_Fabricacao >= 0)
);
GO
CREATE TABLE Peca(
	ID int IDENTITY(3411, 7),
	Nome varchar(30) UNIQUE,
	Preco decimal(4, 2) CHECK(Preco > 0),
	Estoque int CHECK(Estoque >= 10)
	PRIMARY KEY(ID)
);
GO
CREATE TABLE Categoria(
	ID int IDENTITY,
	Categoria varchar(10),
	Valor_Hora decimal(4, 2)
	PRIMARY KEY(ID),
	CONSTRAINT chk_Categoria_ValorHora CHECK(Categoria = "Estagiário" AND Valor_Hora >= 15.00 OR
	Categoria = "Nível 1" AND Valor_Hora >= 25.00 OR 
	Categoria = "Nível 2" AND Valor_Hora >= 35.00 OR
	Categoria = "Nível 3" AND Valor_Hora >= 50.00)
);
GO
CREATE TABLE Funcionario(
	ID int IDENTITY(101, 1),
	Nome varchar(100),
	Logradouro varchar(200),
	Numero int CHECK(Numero > 0),
	Telefone char(11),
	Categoria_Habilitacao varchar(2),
	CategoriaID int
	PRIMARY KEY(ID),
	FOREIGN KEY(CategoriaID) REFERENCES Categoria(ID),
	CONSTRAINT chk_categoria_habilitacao CHECK(Categoria_Habilitacao = "A" OR
	Categoria_Habilitacao = "B" OR Categoria_Habilitacao = "C" OR
	Categoria_Habilitacao = "D" OR Categoria_Habilitacao = "E")
);
GO
CREATE TABLE Reparo(
	VeiculoPlaca char(7) CHECK(LEN(VeiculoPlaca) = 7),
	FuncionarioID int,
	PecaID int,
	Dt date DEFAULT GETDATE(),
	Custo_Total decimal(4, 2) CHECK(Custo_Total > 0),
	Tempo int CHECK(Tempo > 0)
	PRIMARY KEY(VeiculoPlaca, FuncionarioID, PecaID, Dt),
	FOREIGN KEY(VeiculoPlaca) REFERENCES Veiculo(Placa),
	FOREIGN KEY(FuncionarioID) REFERENCES Funcionario(ID),
	FOREIGN KEY(PecaID) REFERENCES Peca(ID)
);
