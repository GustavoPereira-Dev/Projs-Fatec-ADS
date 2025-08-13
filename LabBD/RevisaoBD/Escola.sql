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