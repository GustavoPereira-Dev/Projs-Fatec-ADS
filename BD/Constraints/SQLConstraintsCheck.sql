CREATE DATABASE Livraria;
USE Livraria;

CREATE TABLE Livro(
	Codigo INT NOT NULL IDENTITY(100001, 100),
	Nome varchar(200) NOT NULL,
	Lingua varchar(10) NOT NULL DEFAULT('PT-BR'),
	Ano INT NOT NULL CHECK(Ano >= 1990)
	PRIMARY KEY(Codigo)
);
GO
CREATE TABLE Autor(
	ID_Autor INT NOT NULL IDENTITY (2351, 1), 
	Nome varchar(100) NOT NULL UNIQUE,
	Data_nasc date NOT NULL,
	Pais_Nasc varchar(50) NOT NULL,
	Biografia varchar(255) NOT NULL
	PRIMARY KEY(ID_Autor),
	CONSTRAINT chk_Pais_Nasc CHECK(Pais_Nasc = 'Brasil' OR Pais_Nasc = 'Estados Unidos' 
	OR Pais_Nasc = 'Inglaterra' OR Pais_Nasc = 'Alemanha')
);
GO
CREATE TABLE Livro_Autor(
	LivroCodigo INT NOT NULL,
	AutorID_Autor INT NOT NULL
	PRIMARY KEY (LivroCodigo, AutorID_Autor)
	FOREIGN KEY (LivroCodigo) REFERENCES Livro(Codigo),
	FOREIGN KEY (AutorID_Autor) REFERENCES Autor(ID_Autor)
);

CREATE TABLE Editora(
	ID_Editora INT NOT NULL IDENTITY(491, 16),
	Nome varchar(70) NOT NULL UNIQUE,
	Telefone varchar(11) NOT NULL CHECK(LEN(Telefone) = 10),
	Logradouro_Endereco varchar(200) NOT NULL,
	Numero_Endereco INT NOT NULL CHECK (Numero_Endereco >= 0),
	CEP_Endereco char(8) NOT NULL CHECK(LEN(CEP_Endereco) = 8), 
	Complemento_Endereco varchar(255) NOT NULL
	PRIMARY KEY(ID_Editora)
);
GO
CREATE TABLE Edicao(
	ISBN char(13) NOT NULL CHECK(LEN(ISBN) = 13),
	Preco DECIMAL(4, 2) NOT NULL CHECK (Preco > 0),
	Ano INT NOT NULL CHECK (Ano >= 1993),
	Numero_Paginas INT NOT NULL CHECK(Numero_Paginas > 15),
	Qtd_Estoque INT NOT NULL
	PRIMARY KEY(ISBN)
);
GO
CREATE TABLE Editora_Edicao_Livro(
	EditoraID_Editora INT NOT NULL,
	EdicaoISBN char(13) NOT NULL,
	LivroCodigo INT NOT NULL
	PRIMARY KEY(EditoraID_Editora, EdicaoISBN, LivroCodigo)
	FOREIGN KEY(EditoraID_Editora) REFERENCES Editora(ID_Editora),
	FOREIGN KEY(EdicaoISBN) REFERENCES Edicao(ISBN),
	FOREIGN KEY(LivroCodigo) REFERENCES Livro(Codigo)
);

