# Estrutura Sequencial (Lógica de Programação em SQL)

Fazer no SQL Server os seguintes algoritmos:

## ProgramaçãoSQL1.sql

a) Dado um número inteiro. Calcule e mostre seu fatorial. (Não use entrada superior a 12)

b) Dados A, B e C de uma equação do 2º grau da fórmula AX₂+BX+C=0. Verifique e mostre a existência de raízes reais e, se existir, calcule e mostre. Caso não exista, exiba mensagem.

c) Calcule e mostre quantos anos serão necessários para que Ana seja maior que Maria sabendo que Ana tem 1,10 m e cresce 3 cm ao ano e Maria tem 1,5 m e cresce 2 cm ao ano.

d) Seja a seguinte série: 1, 4, 4, 2, 5, 5, 3, 6, 6, 4, 7, 7, ...
- Escreva um aplicativo que escreva N termos

e) Considerando a tabela abaixo, gere um banco de dados, a tabela e crie um algoritmo para inserir uma massa de dados, com 50 registros, para fins de teste, com as regras estabelecidas (Não usar restrições na criação da tabela)

### Produto

| Campo      | Tipo de Dado     | Observação         |
|------------|------------------|--------------------|
| Codigo     | INT              | Chave Primária (PK)|
| Nome       | VARCHAR(30)      |                    |
| Valor      | DECIMAL(7,2)     |                    |
| Vencimento | DATE             |                    |

- Código inicia em 50001 e incrementa de 1 em 1
- Nome padrão segue simples: Produto 1, Produto 2, Produto 3, etc.
- Valor, gerar um número aleatório* entre 10,00 e 100,000
- Vencimento, gerar um número aleatório* entre 3 e 7 e, usando uma função específica para soma de dados no SQL Server, somar o valor gerado aos dados de hoje.

*A função RAND() gera números aleatórios entre 0 e 0,9999...

f) Considerando a tabela abaixo, gere um banco de dados, a tabela e crie um algoritmo para inserir uma massa de dados, com 50 registros, para fins de teste, com as regras estabelecidas. (Não use restrições na criação da tabela)

### Livro

| Campo        | Tipo de Dado     | Observação         |
|--------------|------------------|--------------------|
| ID           | INT              | Chave Primária (PK)|
| Título       | VARCHAR(30)      |                    |
| Qtd_Páginas  | INT              |                    |
| Qtd_Estoque  | INT              |                    |

- Código inicia em 981101 e incrementa de 1 em 1
- Segue-se o título padrão simples: Livro 981101, Livro 981102, Livro 981103, etc.
- Qtd_páginas deve ser um número aleatório entre 100 e 400
- Qtd_Estoque deve ser um número aleatório entre 2 e 20

## ProgramaçãoSQL2.sql

Usar no SQL Server os seguintes algoritmos:

a) Fazer um algoritmo que leia 1 número e mostre se são múltiplos de 2, 3, 5 ou nenhum deles

b) Fazer um algoritmo que leia 3 números e mostre o maior e o menor

c) Fazer um algoritmo que calcule os 15 primeiros termos da série
- 1, 1, 2, 3, 5, 8, 13, 21,...
- E calcular a soma dos 15 termos

d) Fazer um algoritmo que separe uma frase, interpretando todas as letras em menor e em menor (Usar as funções UPPER e LOWER)

e) Fazer um algoritmo que inverta uma palavra (Usar a função SUBSTRING)

f) Calcular a tabela abaixo, gerar uma massa de dados, com 100 registros, para fins de teste com as regras estabelecidas (Não usar restrições na criação da tabela)

### Computador

| Campo     | Tipo de Dado     | Observação         |
|-----------|------------------|--------------------|
| ID        | INT              | Chave Primária (PK)|
| Marca     | VARCHAR(40)      |                    |
| QtdRAM    | INT              |                    |
| TipoHD    | VARCHAR(10)      |                    |
| QtdHD     | INT              |                    |
| FreqCPU   | DECIMAL(7,2)     |                    |

- ID incremental a partir de 10001
- Marca segue o padrão simples, Marca 1, Marca 2, Marca 3, etc.
- QtdRAM é um número aleatório* entre os valores permitidos (2, 4, 8, 16)
- TipoHD segue o padrão:
  - Se o ID dividido por 3 do resto 0, é HDD
  - Se o ID dividido por 3 do resto 1, é SSD
  - Se o ID dividido por 3 do resto 2, é M2 NVME
- QtdHD segue o padrão:
  - Se o TipoHD for HDD, um valor aleatório* entre os valores permitidos (500, 1000 ou 2000)
  - Se o TipoHD for SSD, um valor aleatório* entre os valores permitidos (128, 256, 512)
  - FreqHD é um número aleatório* entre 1,70 e 3,20

*A função RAND() gera números aleatórios entre 0 e 0,9999...