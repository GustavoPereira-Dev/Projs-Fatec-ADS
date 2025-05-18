CREATE DATABASE Projeto;
USE Projeto;


CREATE TABLE Projects(
	id int IDENTITY(10001, 1),
	name varchar(45),
	description varchar(45),
	dat Date check(dat > '01/09/2014')
	PRIMARY KEY(id)
);

CREATE TABLE Users(
	id int IDENTITY,
	name varchar(45) UNIQUE,
	username varchar(45),
	password varchar(45) DEFAULT "123mudar",
	email varchar(45)
	PRIMARY KEY(id)
);

CREATE TABLE Users_has_Projects(
	users_id int,
	projects_id int
	PRIMARY KEY(users_id, projects_id)
	FOREIGN KEY(users_id) REFERENCES Users(id),
	FOREIGN KEY(projects_id) REFERENCES Projects(id)
);

-- Modificar a coluna username da tabela Users para varchar(10)
-- odificar a coluna password da tabela Users para varchar(8)

INSERT INTO Users (name, username, password, email) VALUES
("Maria", "Rh_maria", "123mudar", "maria@empresa.com"),
("Paulo", "Ti_paulo", "123@456", "paulo@empresa.com"),
("Ana", "Rh_ana", "123mudar", "ana@empresa.com"),
("Clara", "Ti_clara", "123mudar", "clara@empresa.com"),
("Aparecido", "Rh_apareci", "55@!cido", "aparecido@empresa.com")

INSERT INTO Projects (name, description, dat) VALUES
("Re-folha", "Refatoração das Folhas", '05/09/2014'),
("Manutenção PC ́s", "Manutenção PC ́s", '06/09/2014'),
("Auditoria", NULL, '07/09/2014')

INSERT INTO Users_has_Projects VALUES
(1, 10001),
(5, 10001),
(3, 10003),
(4, 10002),
(2, 10002)

-- O projeto de Manutenção atrasou, mudar a data para 12/09/2014
UPDATE Projects SET dat = '12/09/2014' WHERE id = 10002;

-- O username de aparecido (usar o nome como condição de mudança) está feio, mudar para Rh_cido
UPDATE Users SET username = "Rh_cido" WHERE name = "Aparecido";

-- Mudar o password do username Rh_maria (usar o username como condição de mudança) para 888@*, mas a condição deve verificar se o password dela ainda é 123mudar
UPDATE Users SET password = "888@*" WHERE username = "Rh_maria" AND password = "123mudar";

-- O user de id 2 não participa mais do projeto 10002, removê-lo da associativa
DELETE Users_has_Projects WHERE users_id = 2 AND projects_id = 10002


-- Novas consultas - Operações com Dados (Subqueries e Selects Cases)

-- Fazer uma consulta que retorne id, nome, email, username e caso a senha seja diferente de
-- 123mudar, mostrar ******** (8 asteriscos), caso contrário, mostrar a própria senha.
SELECT id, name, email, username, 
	CASE
		WHEN password NOT LIKE '123mudar' THEN '********' 
		ELSE password 
    END AS senha_exibida
FROM Users;

-- Considerando que o projeto 10001 durou 15 dias, fazer uma consulta que mostre o nome do
-- projeto, descrição, data, data_final do projeto realizado por usuário de e-mail
-- aparecido@empresa.com
SELECT name, description, dat, DATEADD(DAY, 15, dat) AS data_final FROM Projects WHERE id IN (SELECT projects_id FROM Users_has_Projects WHERE users_id IN (SELECT id FROM Users WHERE email = "aparecido@empresa.com"));

-- Fazer uma consulta que retorne o nome e o email dos usuários que estão envolvidos no
-- projeto de nome Auditoria
SELECT name, email FROM Users WHERE id IN (SELECT users_id FROM Users_has_Projects WHERE projects_id IN (SELECT id FROM Projects));

-- Considerando que o custo diário do projeto, cujo nome tem o termo Manutenção, é de 79.85
-- e ele deve finalizar 16/09/2014, consultar, nome, descrição, data, data_final e custo_total do
-- projeto
SELECT name, 
		CASE WHEN description IS NULL 
			THEN 
				'descrição indisponível'
			ELSE
				description
		END AS description,
dat, data_final = '16/09/2014', DATEDIFF(DAY, dat, '2014-09-16') * 79.85 AS custo_total FROM Projects;