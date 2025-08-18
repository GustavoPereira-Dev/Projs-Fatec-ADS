CREATE DATABASE Faculdade;
USE Faculdade;

-- Considerando um diagrama abaixo, criar, em SQL Server, a database e gerar dados para preencher as tabelas.

-- Estruturação e Criação de Tabelas
CREATE TABLE curso(
	codigo_curso int PRIMARY KEY,
	nome varchar(70) NOT NULL,
	sigla varchar(10) NOT NULL
);
GO
CREATE TABLE aluno(
	ra char(7) PRIMARY KEY,
	nome varchar(250) NOT NULL,
	codigo_curso int NOT NULL
 	FOREIGN KEY(codigo_curso) REFERENCES curso(codigo_curso)
);
GO
CREATE TABLE palestrante(
	codigo_palestrante int PRIMARY KEY,
	nome varchar(250) NOT NULL,
	empresa varchar(100) NOT NULL
);
GO
CREATE TABLE palestra(
	codigo_palestra int PRIMARY KEY,
	titulo varchar(MAX) NOT NULL,
	carga_horaria int NOT NULL,
	data datetime NOT NULL,
	codigo_palestrante int NOT NULL
	FOREIGN KEY(codigo_palestrante) REFERENCES palestrante(codigo_palestrante)
);
GO
CREATE TABLE alunos_inscritos(
	ra char(7) NOT NULL,
	codigo_palestra int NOT NULL
	PRIMARY KEY(ra, codigo_palestra)
	FOREIGN KEY(ra) REFERENCES aluno(ra),
	FOREIGN KEY(codigo_palestra) REFERENCES palestra(codigo_palestra)
);
GO
CREATE TABLE nao_alunos(
	rg varchar(9) NOT NULL,
	orgao_exp char(5) NOT NULL,
	nome varchar(250) NOT NULL
	PRIMARY KEY(rg, orgao_exp)
);
GO
CREATE TABLE nao_alunos_inscritos (
	rg VARCHAR(9) NOT NULL,
	orgao_exp CHAR(5) NOT NULL,
	codigo_palestra INT NOT NULL
	PRIMARY KEY (rg, orgao_exp, codigo_palestra)
	FOREIGN KEY(codigo_palestra) REFERENCES palestra (codigo_palestra),
	FOREIGN KEY(rg, orgao_exp) REFERENCES nao_alunos (rg, orgao_exp)
)

-- Adição de valores na base de dados
INSERT INTO curso (codigo_curso, nome, sigla) VALUES
(1, 'Curso de Computação', 'CC'),
(2, 'Curso de Administração', 'AD');
GO
INSERT INTO aluno (ra, nome, codigo_curso) VALUES
('1234567', 'João Silva', 1),
('2345678', 'Maria Oliveira', 2);
GO
INSERT INTO palestrante (codigo_palestrante, nome, empresa) VALUES
(1, 'Carlos Souza', 'Tech Corp'),
(2, 'Ana Lima', 'Admin Solutions');
GO
INSERT INTO palestra (codigo_palestra, titulo, carga_horaria, data, codigo_palestrante) VALUES
(1, 'Introdução à Programação', 4, '20/08/2025 14:00:00', 1),
(2, 'Gestão Empresarial', 3, '22/08/2025 10:00:00', 2);
GO
INSERT INTO alunos_inscritos (ra, codigo_palestra) VALUES
('1234567', 1),
('2345678', 2);
GO
INSERT INTO nao_alunos (rg, orgao_exp, nome) VALUES
('123456789', 'SSP', 'Ricardo Pereira'),
('987654321', 'SSP', 'Juliana Costa');
GO
INSERT INTO nao_alunos_inscritos (rg, orgao_exp, codigo_palestra) VALUES
('123456789', 'SSP', 1),
('987654321', 'SSP', 2);

-- Consulta com Union e VIEW
-- O domínio trata de palestras em uma Faculdade. Palestrantes apresentarão palestras para alunos e não alunos. Para o caso de 
-- alunos, seus dados já são referenciáveis em outro sistema, portanto, basta saber seu RA, no entanto, para não alunos, para 
-- prover certificados, é importante saber seu RG e Órgão Expedidor. O problema está no momento de gerar a lista de presença. 
-- A lista de presença deverá vir de uma consulta que retorna (Num_Documento, Nome_Pessoa, Titulo_Palestra, Nome_Palestrante,
-- Carga_Horária, Data(dd/mm/aaaa) e Hora (HH:mm)). A lista deverá ser uma só, por palestra (A condição
-- da consulta é o código da palestra) e contemplar alunos e não alunos (O Num_Documento se referencia
-- ao RA para alunos e RG + Orgao_Exp para não alunos) e estar ordenada pelo Nome_Pessoa.

CREATE VIEW v_participacao_palestra AS
SELECT ai.ra AS Num_Documento, a.nome AS Nome_Pessoa, p.titulo AS Titulo_Palestra, plt.nome AS Nome_Palestrante, p.carga_horaria AS Carga_Horaria, 
FORMAT(p.data, 'dd/MM/yy') AS Data, FORMAT(p.data, 'HH:mm') AS Hora FROM
palestra p INNER JOIN palestrante plt ON p.codigo_palestrante = plt.codigo_palestrante INNER JOIN alunos_inscritos ai ON p.codigo_palestra = ai.codigo_palestra 
INNER JOIN aluno a ON ai.ra = a.ra
UNION
SELECT na.rg + '-' + na.orgao_exp AS Num_Documento, na.nome AS Nome_Pessoa, p.titulo AS Titulo_Palestra, plt.nome AS Nome_Palestrante, p.carga_horaria AS Carga_Horaria, 
FORMAT(p.data, 'dd/MM/yy') AS Data, FORMAT(p.data, 'HH:mm') AS Hora FROM
palestra p INNER JOIN palestrante plt ON p.codigo_palestrante = plt.codigo_palestrante INNER JOIN nao_alunos_inscritos nai ON p.codigo_palestra = nai.codigo_palestra 
INNER JOIN nao_alunos na ON nai.rg = na.rg AND nai.orgao_exp = na.orgao_exp;

-- Uso da VIEW
SELECT * FROM v_participacao_palestra;
