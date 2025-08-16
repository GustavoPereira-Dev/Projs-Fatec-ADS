CREATE DATABASE ListaCarros;
USE ListaCarros;

CREATE TABLE Carro (
	Placa char (7) PRIMARY KEY,
	Marca varchar (70),
	Modelo varchar (70),
	Ano int,
	Cor varchar(30)
);