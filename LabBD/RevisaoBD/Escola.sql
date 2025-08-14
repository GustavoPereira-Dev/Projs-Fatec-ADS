CREATE DATABASE Escola;
GO
USE Escola;
GO

CREATE TABLE Aluno (
    RA INT NOT NULL CHECK (RA > 0),
    Nome VARCHAR(100) NOT NULL,
    Idade INT NOT NULL CHECK (Idade > 0),
    PRIMARY KEY (RA)
);
GO
CREATE TABLE Disciplina (
    Codigo INT NOT NULL,
    Nome VARCHAR(100) NOT NULL,
    CargaHoraria INT NOT NULL CHECK (CargaHoraria >= 32),
    PRIMARY KEY (Codigo)
);
GO
CREATE TABLE Professor (
    Registro INT NOT NULL,
    Nome VARCHAR(100) NOT NULL,
    Titulacao INT NOT NULL,
    PRIMARY KEY (Registro)
);
GO
CREATE TABLE Titulacao (
    Codigo INT NOT NULL,
    Titulo VARCHAR(50) NOT NULL,
    PRIMARY KEY (Codigo)
);
GO
CREATE TABLE Curso (
    Codigo INT NOT NULL,
    Nome VARCHAR(50) NOT NULL,
    Area VARCHAR(100) NOT NULL,
    PRIMARY KEY (Codigo)
);
GO
CREATE TABLE Aluno_Disciplina (
    Codigo_Disciplina INT NOT NULL,
    RA_Aluno INT NOT NULL,
    PRIMARY KEY (Codigo_Disciplina, RA_Aluno),
    FOREIGN KEY (Codigo_Disciplina) REFERENCES Disciplina(Codigo),
    FOREIGN KEY (RA_Aluno) REFERENCES Aluno(RA)
);
GO
CREATE TABLE Professor_Disciplina (
    Codigo_Disciplina INT NOT NULL,
    Registro_Professor INT NOT NULL,
    PRIMARY KEY (Codigo_Disciplina, Registro_Professor),
    FOREIGN KEY (Codigo_Disciplina) REFERENCES Disciplina(Codigo),
    FOREIGN KEY (Registro_Professor) REFERENCES Professor(Registro)
);
GO
CREATE TABLE Disciplina_Curso (
    Codigo_Disciplina INT NOT NULL,
    Codigo_Curso INT NOT NULL,
    PRIMARY KEY (Codigo_Disciplina, Codigo_Curso),
    FOREIGN KEY (Codigo_Disciplina) REFERENCES Disciplina(Codigo),
    FOREIGN KEY (Codigo_Curso) REFERENCES Curso(Codigo)
);


INSERT INTO Aluno (RA, Nome, Idade) VALUES
(3416, 'DIEGO PIOVESAN DE RAMOS', 18),
(3423, 'LEONARDO MAGALHÃES DA ROSA', 17),
(3434, 'LUIZA CRISTINA DE LIMA MARTINELI', 20),
(3440, 'IVO ANDRÉ FIGUEIRA DA SILVA', 25),
(3443, 'BRUNA LUISA SIMIONI', 37),
(3448, 'THAÍS NICOLINI DE MELLO', 17),
(3457, 'LÚCIO DANIEL TÂMARA ALVES', 29),
(3459, 'LEONARDO RODRIGUES', 25),
(3465, 'ÉDERSON RAFAEL VIEIRA', 19),
(3466, 'DAIANA ZANROSSO DE OLIVEIRA', 21),
(3467, 'DANIELA MAURER', 23),
(3470, 'ALEX SALVADORI PALUDO', 42),
(3471, 'VINÍCIUS SCHVARTZ', 19),
(3472, 'MARIANA CHIES ZAMPIERI', 18),
(3482, 'EDUARDO CAINAN GAVSKI', 19),
(3483, 'REDNALDO ORTIZ DONEDA', 20),
(3499, 'MAYELEN ZAMPIERON', 22);
GO
INSERT INTO Disciplina (Codigo, Nome, CargaHoraria) VALUES
(1, 'Laboratório de Banco de Dados', 80),
(2, 'Laboratório de Engenharia de Software', 80),
(3, 'Programação Linear e Aplicações', 80),
(4, 'Redes de Computadores', 80),
(5, 'Segurança da informação', 40),
(6, 'Teste de Software', 80),
(7, 'Custos e Tarifas Logísticas', 80),
(8, 'Gestão de Estoques', 40),
(9, 'Fundamentos de Marketing', 40),
(10, 'Métodos Quantitativos de Gestão', 80),
(11, 'Gestão do Tráfego Urbano', 80),
(12, 'Sistemas de Movimentação e Transporte', 40);
GO
INSERT INTO Professor (Registro, Nome, Titulacao) VALUES
(1111, 'Leandro', 2),
(1112, 'Antonio', 2),
(1113, 'Alexandre', 3),
(1114, 'Wellington', 2),
(1115, 'Luciano', 1),
(1116, 'Edson', 2),
(1117, 'Ana', 2),
(1118, 'Alfredo', 1),
(1119, 'Celio', 2),
(1120, 'Dewar', 3),
(1121, 'Julio', 1);
GO
INSERT INTO Titulacao (Codigo, Titulo) VALUES
(1, 'Especialista'),
(2, 'Mestre'),
(3, 'Doutor');
GO
INSERT INTO Curso (Codigo, Nome, Area) VALUES
(1, 'ADS', 'Ciências da Computação'),
(2, 'Logística', 'Engenharia Civil');
GO
INSERT INTO Aluno_Disciplina (Codigo_Disciplina, RA_Aluno) VALUES
(1, 3416), (4, 3416),
(1, 3423), (2, 3423), (5, 3423), (6, 3423),
(2, 3434), (5, 3434), (6, 3434),
(1, 3440),
(5, 3443), (6, 3443),
(4, 3448), (5, 3448), (6, 3448),
(2, 3457), (4, 3457), (5, 3457), (6, 3457),
(1, 3459), (6, 3459),
(7, 3465), (11, 3465),
(8, 3466), (11, 3466),
(8, 3467), (12, 3467),
(8, 3470), (9, 3470), (11, 3470), (12, 3470),
(7, 3471),
(7, 3472), (12, 3472),
(9, 3482), (11, 3482),
(8, 3483), (11, 3483), (12, 3483),
(8, 3499);
GO
INSERT INTO Professor_Disciplina (Codigo_Disciplina, Registro_Professor) VALUES
(1, 1111),
(2, 1112),
(3, 1113),
(4, 1114),
(5, 1115),
(6, 1116),
(7, 1117),
(8, 1118),
(9, 1117),
(10, 1119),
(11, 1120),
(12, 1121);
GO
INSERT INTO Disciplina_Curso (Codigo_Disciplina, Codigo_Curso) VALUES
(1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1),
(7, 2), (8, 2), (9, 2), (10, 2), (11, 2), (12, 2);

SELECT * FROM Aluno;
SELECT * FROM Disciplina;
SELECT * FROM Professor;
SELECT * FROM Curso;
SELECT * FROM Titulacao;
SELECT * FROM Aluno_Disciplina;
SELECT * FROM Professor_Disciplina;
SELECT * FROM Disciplina_Curso;