CREATE DATABASE Plano;
USE Plano;

CREATE TABLE PlanoSaude (
    Codigo INT PRIMARY KEY,
    Nome VARCHAR(100),
    Telefone VARCHAR(15)
);


CREATE TABLE Paciente (
    CPF CHAR(11) PRIMARY KEY,
    Nome VARCHAR(100),
    Rua VARCHAR(100),
    Numero INT,
    Bairro VARCHAR(100),
    Telefone VARCHAR(15),
    CodigoPlano INT FOREIGN KEY REFERENCES PlanoSaude(Codigo)
);


CREATE TABLE Medico (
    Codigo INT PRIMARY KEY,
    Nome VARCHAR(100),
    Especialidade VARCHAR(100),
    CodigoPlano INT FOREIGN KEY REFERENCES PlanoSaude(Codigo)
);


CREATE TABLE Consulta (
    CodigoMedico INT FOREIGN KEY REFERENCES Medico(Codigo),
    CPF_Paciente CHAR(11) FOREIGN KEY REFERENCES Paciente(CPF),
    DataHora DATETIME,
    Diagnostico VARCHAR(100),
    PRIMARY KEY (CodigoMedico, CPF_Paciente, DataHora)
);

INSERT INTO PlanoSaude (Codigo, Nome, Telefone) VALUES
(1234, 'Amil', '41599856'),
(2345, 'Sul América', '45698745'),
(3456, 'Unimed', '48759836'),
(4567, 'Bradesco Saúde', '47265897'),
(5678, 'Intermédica', '41415269');

INSERT INTO Paciente (CPF, Nome, Rua, Numero, Bairro, Telefone, CodigoPlano) VALUES
('85987458920', 'Maria Paula', 'R. Voluntários da Pátria', 589, 'Santana', '98458741', 2345),
('87452136900', 'Ana Julia', 'R. XV de Novembro', 657, 'Centro', '69857412', 5678),
('23659874100', 'João Carlos', 'R. Sete de Setembro', 12, 'República', '74859632', 1234),
('63259874100', 'José Lima', 'R. Anhaia', 768, 'Barra Funda', '96524156', 2345);

INSERT INTO Medico (Codigo, Nome, Especialidade, CodigoPlano) VALUES
(1, 'Claudio', 'Clínico Geral', 1234),
(2, 'Larissa', 'Ortopedista', 2345),
(3, 'Juliana', 'Otorrinolaringologista', 4567),
(4, 'Sérgio', 'Pediatra', 1234),
(5, 'Julio', 'Clínico Geral', 4567),
(6, 'Samara', 'Cirurgião', 1234);

INSERT INTO Consulta (CodigoMedico, CPF_Paciente, DataHora, Diagnostico) VALUES
(1, '85987458920', '2021-02-10 10:30:00', 'Gripe'),
(2, '23659874100', '2021-02-10 11:00:00', 'Pé Fraturado'),
(4, '85987458920', '2021-02-11 14:00:00', 'Pneumonia'),
(1, '23659874100', '2021-02-11 15:00:00', 'Asma'),
(3, '87452136900', '2021-02-11 16:00:00', 'Sinusite'),
(5, '63259874100', '2021-02-11 17:00:00', 'Rinite'),
(4, '23659874100', '2021-02-11 18:00:00', 'Asma'),
(5, '63259874100', '2021-02-12 10:00:00', 'Rinoplastia');

SELECT m.Nome, m.Especialidade FROM Medico m JOIN PlanoSaude p ON m.CodigoPlano = p.Codigo WHERE p.Nome = 'Amil';

SELECT p.Nome, p.Rua + ', ' + CAST(p.Numero AS VARCHAR) + ' - ' + p.Bairro AS Endereco, p.Telefone, ps.Nome AS Plano FROM Paciente p
INNER JOIN PlanoSaude ps ON p.CodigoPlano = ps.Codigo;

SELECT ps.Telefone FROM Paciente p INNER JOIN PlanoSaude ps ON p.CodigoPlano = ps.Codigo WHERE p.Nome = 'Ana Julia';

SELECT ps.Nome FROM PlanoSaude ps LEFT OUTER JOIN Paciente p ON ps.Codigo = p.CodigoPlano WHERE p.CPF IS NULL;

SELECT ps.Nome FROM PlanoSaude ps LEFT OUTER JOIN Medico m ON ps.Codigo = m.CodigoPlano WHERE m.Codigo IS NULL;

SELECT c.DataHora, FORMAT(c.DataHora, 'HH:mm') AS Hora, m.Nome AS Medico, p.Nome AS Paciente, c.Diagnostico FROM Consulta c
INNER JOIN Medico m ON c.CodigoMedico = m.Codigo INNER JOIN Paciente p ON c.CPF_Paciente = p.CPF;

SELECT m.Nome AS Medico, c.DataHora, c.Diagnostico FROM Consulta c INNER JOIN Medico m ON c.CodigoMedico = m.Codigo 
INNER JOIN Paciente p ON c.CPF_Paciente = p.CPF WHERE p.Nome = 'José Lima';

SELECT Diagnostico, COUNT(*) AS qtd FROM Consulta GROUP BY Diagnostico;

SELECT COUNT(*) AS TotalSemMedicos FROM PlanoSaude ps LEFT OUTER JOIN Medico m ON ps.Codigo = m.CodigoPlano WHERE m.Codigo IS NULL;

UPDATE Paciente SET Nome = 'João Carlos da Silva' WHERE Nome = 'João Carlos';

DELETE FROM PlanoSaude WHERE Nome = 'Unimed';

EXEC sp_rename 'Paciente.Rua', 'Logradouro', 'COLUMN';

ALTER TABLE Paciente ADD data_nasc DATE;

UPDATE Paciente SET data_nasc = '1990-04-18' WHERE Nome = 'Maria Paula';
UPDATE Paciente SET data_nasc = '1981-03-25' WHERE Nome = 'Ana Julia';
UPDATE Paciente SET data_nasc = '2004-09-04' WHERE Nome = 'João Carlos da Silva';
UPDATE Paciente SET data_nasc = '1986-06-18' WHERE Nome = 'José Lima';