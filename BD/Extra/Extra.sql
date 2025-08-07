CREATE DATABASE ex11
GO
USE ex11
GO
CREATE TABLE plano_saude(
codigo			INT				NOT NULL,
nome			VARCHAR(50)		NOT NULL,
telefone		CHAR(8)			NOT NULL
PRIMARY KEY (codigo)
)
GO
CREATE TABLE paciente(
cpf				CHAR(11)		NOT NULL,
nome			VARCHAR(80)		NOT NULL,
rua				VARCHAR(100)	NOT NULL,
numero			INT				NOT NULL,
bairro			VARCHAR(50)		NOT NULL,
telefone		CHAR(8)			NOT NULL,
plano			INT				NOT NULL
PRIMARY KEY (cpf)
FOREIGN KEY (plano) REFERENCES plano_saude(codigo)
)
GO
CREATE TABLE medico(
codigo			INT				NOT NULL,
nome			VARCHAR(80)		NOT NULL,
especialidade	VARCHAR(30)		NOT NULL,
plano			INT				NOT NULL
PRIMARY KEY (codigo)
FOREIGN KEY (plano) REFERENCES plano_saude(codigo)
)
GO
CREATE TABLE consulta(
medico			INT				NOT NULL,
paciente		CHAR(11)		NOT NULL,
datahora		DATETIME		NOT NULL,
diagnostico		VARCHAR(50)		NOT NULL
PRIMARY KEY (medico, paciente, datahora)
FOREIGN KEY (medico) REFERENCES medico(codigo),
FOREIGN KEY (paciente) REFERENCES paciente(cpf)
)
GO
INSERT INTO plano_saude VALUES
(1234,	'Amil',				'41599856'),
(2345,	'Sul América',		'45698745'),
(3456,	'Unimed',			'48759836'),
(4567,	'Bradesco Saúde',	'47265897'),
(5678,	'Intermédica',		'41415269')
GO
INSERT INTO paciente VALUES
('85987458920',	'Maria Paula',	'R. Voluntários da Pátria',	589,	'Santana',		'98458741',	2345),
('87452136900',	'Ana Julia',	'R. XV de Novembro',		657,	'Centro',		'69857412',	5678),
('23659874100',	'João Carlos',	'R. Sete de Setembro',		12,		'República',	'74859632',	1234),
('63259874100',	'José Lima',	'R. Anhaia',				768,	'Barra Funda',	'96524156',	2345)
GO
INSERT INTO medico VALUES
(1,	'Claudio',	'Clínico Geral',			1234),
(2,	'Larissa',	'Ortopedista',				2345),
(3,	'Juliana',	'Otorrinolaringologista',	4567),
(4,	'Sérgio',	'Pediatra',					1234),
(5,	'Julio',	'Clínico Geral',			4567),
(6,	'Samara',	'Cirurgião',				1234)
GO
INSERT INTO consulta VALUES
(1,	'85987458920',	CAST(N'2025-02-10 10:30:00' AS DATETIME), 	'Gripe'),
(2,	'23659874100',	CAST(N'2025-02-10 11:00:00' AS DATETIME), 	'Pé Fraturado'),
(4,	'85987458920',	CAST(N'2025-02-11 14:00:00' AS DATETIME),	'Pneumonia'),
(1,	'23659874100',	CAST(N'2025-02-11 15:00:00' AS DATETIME),	'Asma'),
(3,	'87452136900',	CAST(N'2025-02-11 16:00:00' AS DATETIME),	'Sinusite'),
(5,	'63259874100',	CAST(N'2025-02-11 17:00:00' AS DATETIME),	'Rinite'),
(4,	'23659874100',	CAST(N'2025-02-11 18:00:00' AS DATETIME),	'Asma'),
(5,	'63259874100',	CAST(N'2025-02-12 10:00:00' AS DATETIME),	'Rinoplastia')

-- Gerar as seguintes queries:

-- 1. Consultar Nome e especialidade dos médicos da Amil
SELECT m.nome, m.especialidade FROM medico m JOIN plano_saude p ON m.plano = p.codigo WHERE p.nome = 'Amil';

-- 2. Consultar Nome, Endereço concatenado, Telefone e Nome do Plano de Saúde de todos os pacientes
SELECT pa.nome, pa.rua + ', ' + CAST(pa.numero AS VARCHAR) + ' - ' + pa.bairro AS endereco, pa.telefone,
ps.nome AS plano_saude FROM paciente pa JOIN plano_saude ps ON pa.plano = ps.codigo;

-- 3. Consultar Telefone do Plano de  Saúde de Ana Júlia
SELECT ps.telefone FROM paciente pa JOIN plano_saude ps ON pa.plano = ps.codigo WHERE pa.nome = 'Ana Julia';

-- 4. Consultar Plano de Saúde que não tem pacientes cadastrados
SELECT ps.nome FROM plano_saude ps LEFT JOIN paciente pa ON ps.codigo = pa.plano WHERE pa.cpf IS NULL;

-- 5. Consultar Planos de Saúde que não tem médicos cadastrados
SELECT ps.nome FROM plano_saude ps LEFT JOIN medico m ON ps.codigo = m.plano WHERE m.codigo IS NULL;

-- 6. Consultar Data da consulta, Hora da consulta, nome do médico, nome do paciente e diagnóstico de todas as consultas
SELECT CONVERT(DATE, c.datahora) AS data, CONVERT(TIME, c.datahora) AS hora, m.nome AS medico, 
p.nome AS paciente, c.diagnostico FROM consulta c JOIN medico m ON c.medico = m.codigo JOIN paciente p ON c.paciente = p.cpf;

-- 7. Consultar Nome do médico, data e hora de consulta e diagnóstico de José Lima
SELECT m.nome AS medico, CONVERT(DATE, c.datahora) AS data, CONVERT(TIME, c.datahora) AS hora, c.diagnostico FROM consulta c
JOIN medico m ON c.medico = m.codigo JOIN paciente p ON c.paciente = p.cpf WHERE p.nome = 'José Lima';

-- 8. Consultar Diagnóstico e Quantidade de consultas que aquele diagnóstico foi dado (Coluna deve chamar qtd)
SELECT c.diagnostico, COUNT(*) AS qtd FROM consulta c GROUP BY c.diagnostico;

-- 9. Consultar Quantos Planos de Saúde que não tem médicos cadastrados
SELECT COUNT(*) AS qtd_sem_medicos FROM plano_saude ps LEFT JOIN medico m ON ps.codigo = m.plano WHERE m.codigo IS NULL;