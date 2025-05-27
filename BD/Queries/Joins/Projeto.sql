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


-- Novas ações (adições e consultas com JOINS)

-- Novos INSERTS
-- a) Adicionar User (6; Joao; Ti_joao; 123mudar; joao@empresa.com)
INSERT INTO Users (name, username, password, email)
VALUES ("Joao", "Ti_joao", "", "joao@empresa.com");

-- b) Adicionar Project (10004; Atualização de Sistemas; Modificação de Sistemas Operacionais nos PC's; 12/09/2014)
INSERT INTO Projects (name, description, dat)
VALUES ("Atualização de Sistemas", "Modificação de Sistemas Operacionais nos PC's", '12/09/2014');

-- Consultas com JOINS

-- 1) Id, Name e Email de Users, Id, Name, Description e Data de Projects, dos usuários que participaram do projeto Name Re-folha
SELECT usr.id, usr.name, usr.email, proj.name, proj.description, proj.dat FROM Users usr INNER JOIN Users_has_Projects usrprojs ON usr.id = usrprojs.users_id 
INNER JOIN Projects proj ON usrprojs.projects_id = proj.id WHERE proj.name LIKE "Re-folha";

-- 2) Name dos Projects que não tem Users
SELECT proj.name FROM Users_has_Projects usrprojs RIGHT OUTER JOIN Projects proj ON usrprojs.projects_id = proj.id WHERE usrprojs.users_id IS NULL

-- 3) Name dos Users que não tem Projects
SELECT usr.name FROM Users usr LEFT OUTER JOIN Users_has_Projects usrprojs ON usr.id = usrprojs.users_id  WHERE usrprojs.projects_id IS NULL
