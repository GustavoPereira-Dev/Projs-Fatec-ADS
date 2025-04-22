CREATE DATABASE Clinica;
GO
USE Clinica;
-- Tabela Paciente
CREATE TABLE Paciente (
  Num_Beneficiario integer NOT NULL,
  Nome varchar(100) NOT NULL,
  Lograouro varchar(200) NOT NULL,
  Numero integer NOT NULL,
  CEP char(8) NOT NULL,
  Complemento varchar(255) NOT NULL,
  Telefone varchar(11) NOT NULL,
  PRIMARY KEY (Num_Beneficiario)
);
GO
-- Tabela Especialidade
CREATE TABLE Especialidade (
  ID integer NOT NULL,
  Especialidade varchar(100) NOT NULL,
  PRIMARY KEY (ID)
);
GO
-- Tabela Medico
CREATE TABLE Medico (
  Codigo integer NOT NULL,
  Nome varchar(100) NOT NULL,
  Lograouro varchar(200) NOT NULL,
  Numero integer NOT NULL,
  CEP char(8) NOT NULL,
  Complemento varchar(255) NOT NULL,
  Contato varchar(11) NOT NULL,
  EspecialidadeID integer NOT NULL,
  PRIMARY KEY (Codigo),
  FOREIGN KEY (EspecialidadeID) REFERENCES Especialidade(ID)
);
GO

-- Tabela Consulta
CREATE TABLE Consulta (
  PacienteNum_Beneficiario integer NOT NULL,
  MedicoCodigo integer NOT NULL,
  Data_hora timestamp NOT NULL,
  Observacao varchar(255) NOT NULL,
  PRIMARY KEY (PacienteNum_Beneficiario, MedicoCodigo, Data_hora),
  FOREIGN KEY (PacienteNum_Beneficiario) REFERENCES Paciente(Num_Beneficiario),
  FOREIGN KEY (MedicoCodigo) REFERENCES Medico(Codigo)
);
GO
-- Inserindo dados na Tabela Paciente
INSERT INTO Paciente VALUES 
(99901, 'Washington Silva', 'R. Anhaia', 150, '02345000', 'Casa', '922229999'),
(99902, 'Luis Ricardo', 'R. Voluntários da Pátria', 2251, '03254010', 'Bloco B. Apto 25', '923450987'),
(99903, 'Maria Elisa', 'Av. Aguia de Haia', 1188, '06987020', 'Apto 1208', '912348765'),
(99904, 'José Araújo', 'R. XV de Novembro', 18, '03678000', 'Casa', '945674312'),
(99905, 'Joana Paula', 'R. 7 de Abril', 97, '01214000', 'Conjunto 3 - Apto 801', '912095674');
GO
-- Inserindo dados na Tabela Especialidade
INSERT INTO Especialidade VALUES 
(1, 'Otorrinolaringologista'),
(2, 'Urologista'),
(3, 'Geriatra'),
(4, 'Pediatra');
GO

-- Inserindo dados na Tabela Medico
INSERT INTO Medico VALUES 
(100001, 'Ana Paula', 'R. 7 de Setembro', 256, '03698000', 'Casa', '915689456', 1),
(100002, 'Maria Aparecida', 'Av. Brasil', 32, '02145070', 'Casa', '923235454', 1),
(100003, 'Lucas Borges', 'Av. do Estado', 3210, '05241000', 'Apto 205', '963698585', 2),
(100004, 'Gabriel Oliveira', 'Av. Dom Helder Camara', 350, '03145000', 'Apto 602', '932458745', 3);
GO

-- Inserindo dados na Tabela Consulta
INSERT INTO Consulta VALUES
(99901, 100002, "2021-09-04 13:20", "Infecção Urina"),
(99902, 100003, "2021-09-04 13:15", "Gripe"),
(99901, 100001, "2021-09-04 12:30", "Infecção Garganta")

-- EXEC sp_help Paciente
-- EXEC sp_help Especialidade
-- EXEC sp_help Medico
-- EXEC sp_help Consulta

-- Selecionando todos os dados da tabela antes da próxima atualização
SELECT * FROM Paciente;
SELECT * FROM Especialidade;
SELECT * FROM Medico;
SELECT * FROM Consulta;

-- Alterando Tabela Medico para adicionar coluna dia_atendimento
ALTER TABLE Medico
ADD dia_atendimento varchar(10);
GO
-- Atualizando dias de atendimento
UPDATE Medico SET dia_atendimento = '2ª feira' WHERE Codigo = 100001;
UPDATE Medico SET dia_atendimento = '4ª feira' WHERE Codigo = 100002;
UPDATE Medico SET dia_atendimento = '2ª feira' WHERE Codigo = 100003;
UPDATE Medico SET dia_atendimento = '5ª feira' WHERE Codigo = 100004;

-- Adicionando o "not null" na coluna adicionada após a adição de dados
ALTER TABLE Medico
ALTER COLUMN dia_atendimento varchar(10) NOT NULL;
GO

-- Mostrando a coluna dia_atendimento de Medico
SELECT * FROM Medico;

-- Excluindo especialidade
DELETE FROM Especialidade WHERE ID = 4;

-- Renomear a coluna dia_atendimento para dia_semana_atendimento
EXEC sp_rename 'dbo.Medico.dia_atendimento', 'dia_semana_atendimento', 'COLUMN'

-- Atualizando endereço do médico
UPDATE Medico 
SET Lograouro = 'Av. Bras Leme', Numero = 876, Complemento = 'Apto 504', CEP = '02122000' 
WHERE Codigo = 100003;

-- Alterando Tabela Medico para adicionar coluna Observacao
ALTER TABLE Consulta
ALTER COLUMN Observacao varchar(200) NOT NULL;

-- Selecionando todos os dados das tabelas alteradas depois da atualização
SELECT * FROM Especialidade;
SELECT * FROM Medico;
SELECT * FROM Consulta;