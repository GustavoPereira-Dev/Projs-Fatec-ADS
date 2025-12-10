# Relatórios

## Pessoa IMC

- Considere o cadastro de pessoas com seu identificar (id), nome, altura (cm) e peso (kg).
  - É necessário consultar, das pessoas cadastradas, o valor e a condição de IMC.
- Uma Multi statement table function deve mostrar id, nome, altura em metros [o dado original deve ser
convertido], peso em kgs, imc e situação das pessoas em uma faixa dentro de um valor mínimo e um
valor máximo de IMC.
Deve-se gerar um relatório com a tabela de saída da UDF.
O índice é calculado da seguinte maneira: divide-se o peso do paciente pela sua altura (em metros)
elevada ao quadrado.
A tabela do Índice de Massa Corporal (IMC) é a seguinte:


| Faixa de IMC        | Classificação                  |
|----------------------|--------------------------------|
| Menor que 16         | Magreza grave                  |
| Entre 16 e 16,9      | Magreza moderada               |
| Entre 17 e 18,5      | Magreza leve                   |
| Entre 18,6 e 24,9    | Peso ideal                     |
| Entre 25 e 29,9      | Sobrepeso                      |
| Entre 30 e 34,9      | Obesidade grau I               |
| Entre 35 e 39,9      | Obesidade grau II ou severa    |
| Maior que 40         | Obesidade grau III ou mórbida  |


- Criar a tabela abaixo em uma workspace e insira os valores:


<code> CREATE TABLE pessoa (
id INT NOT NULL,
nome VARCHAR(60) NOT NULL,
altura INT NOT NULL,
peso INT NOT NULL
PRIMARY KEY (id))
<br>
<br>
INSERT INTO pessoa VALUES (1,'Miguel',184,65)
INSERT INTO pessoa VALUES (2,'Arthur',181,85)
INSERT INTO pessoa VALUES (3,'Gael',200,65)
INSERT INTO pessoa VALUES (4,'Théo',162,102)
INSERT INTO pessoa VALUES (5,'Heitor',202,81)
INSERT INTO pessoa VALUES (6,'Ravi',150,91)
INSERT INTO pessoa VALUES (7,'Davi',164,140)
INSERT INTO pessoa VALUES (8,'Bernardo',155,125)
INSERT INTO pessoa VALUES (9,'Noah',155,78)
INSERT INTO pessoa VALUES (10,'Gabriel',191,108)
INSERT INTO pessoa VALUES (11,'Samuel',170,99)
INSERT INTO pessoa VALUES (12,'Pedro',184,85)
INSERT INTO pessoa VALUES (13,'Anthony',205,116)
INSERT INTO pessoa VALUES (14,'Isaac',163,120)
INSERT INTO pessoa VALUES (15,'Benício',205,87)
INSERT INTO pessoa VALUES (16,'Benjamin',171,95)
INSERT INTO pessoa VALUES (17,'Matheus',186,87)
INSERT INTO pessoa VALUES (18,'Lucas',178,134)
INSERT INTO pessoa VALUES (19,'Joaquim',152,136)
INSERT INTO pessoa VALUES (20,'Nicolas',203,116)
INSERT INTO pessoa VALUES (21,'Lucca',170,83)
INSERT INTO pessoa VALUES (22,'Lorenzo',198,76)
INSERT INTO pessoa VALUES (23,'Henrique',192,66)
INSERT INTO pessoa VALUES (24,'João Miguel',203,120)
INSERT INTO pessoa VALUES (25,'Rafael',183,87)
INSERT INTO pessoa VALUES (26,'Henry',161,74)
INSERT INTO pessoa VALUES (27,'Murilo',172,75)
INSERT INTO pessoa VALUES (28,'Levi',164,123)
INSERT INTO pessoa VALUES (29,'Guilherme',204,79)
INSERT INTO pessoa VALUES (30,'Vicente',198,137)
INSERT INTO pessoa VALUES (31,'Felipe',168,70)
INSERT INTO pessoa VALUES (32,'Bryan',170,95)
INSERT INTO pessoa VALUES (33,'Matteo',195,74)
INSERT INTO pessoa VALUES (34,'Bento',179,113)
INSERT INTO pessoa VALUES (35,'João Pedro',152,112)
INSERT INTO pessoa VALUES (36,'Pietro',190,105)
INSERT INTO pessoa VALUES (37,'Leonardo',182,92)
INSERT INTO pessoa VALUES (38,'Daniel',198,83)
INSERT INTO pessoa VALUES (39,'Gustavo',205,95)
INSERT INTO pessoa VALUES (40,'Pedro Henrique',192,75)
</code>