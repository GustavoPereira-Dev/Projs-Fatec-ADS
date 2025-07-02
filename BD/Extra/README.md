# Exercícios Extras

## Exercicio1.sql

<strong> Estrutura das tabelas </strong>

### Aluno

| RA    | Nome   | Sobrenome         | Rua                    | Nº   | Bairro           | CEP     | Telefone  |
|-------|--------|-------------------|------------------------|------|------------------|---------|-----------|
| 12345 | José   | Silva             | Almirante Noronha      | 236  | Jardim São Paulo | 1589000 | 69875287  |
| 12346 | Ana    | Maria Bastos      | Anhaia                 | 1568 | Barra Funda      | 3569000 | 25698526  |
| 12347 | Mario  | Santos            | XV de Novembro         | 1841 | Centro           | 1020030 |           |
| 12348 | Marcia | Neves             | Voluntários da Patria  | 225  | Santana          | 2785090 | 78964152  |

### Cursos
| Código | Nome        | Carga Horária | Turno |
|--------|-------------|----------------|--------|
| 1      | Informática | 2800           | Tarde  |
| 2      | Informática | 2800           | Noite  |
| 3      | Logística   | 2650           | Tarde  |
| 4      | Logística   | 2650           | Noite  |
| 5      | Plásticos   | 2500           | Tarde  |
| 6      | Plásticos   | 2500           | Noite  |

### Disciplinas
| Código | Nome                | Carga Horária | Turno | Semestre |
|--------|---------------------|----------------|--------|-----------|
| 1      | Informática         | 4              | Tarde  | 1         |
| 2      | Informática         | 4              | Noite  | 1         |
| 3      | Química             | 4              | Tarde  | 1         |
| 4      | Química             | 4              | Noite  | 1         |
| 5      | Banco de Dados I    | 2              | Tarde  | 3         |
| 6      | Banco de Dados I    | 2              | Noite  | 3         |
| 7      | Estrutura de Dados  | 4              | Tarde  | 4         |
| 8      | Estrutura de Dados  | 4              | Noite  | 4         |

- Consultar:
  - Nome e sobrenome, como nome completo dos Alunos Matriculados
  - Rua, nº , Bairro e CEP como Endereço do aluno que não tem telefone
  - Telefone do aluno com RA 12348
  - Nome e Turno dos cursos com 2800 horas
  - O semestre do curso de Banco de Dados I noite


## Exercicio2.sql

<strong> Estrutura das tabelas </strong>

### Cliente
| Nome           | Logradouro           | Nº    | Bairro        | Telefone   | Carro (PK e FK)   |
|----------------|-----------------------|-------|----------------|------------|---------|
| João Alves     | R. Pereira Barreto    | 1258  | Jd. Oliveiras  | 2154-9658  | DXO9876 |
| Ana Maria      | R. 7 de Setembro      | 259   | Centro         | 9658-8541  | LKM7380 |
| Clara Oliveira | Av. Nações Unidas     | 10254 | Pinheiros      | 2458-9658  | EGT4631 |
| José Simões    | R. XV de Novembro     | 36    | Água Branca    | 7895-2459  | BCD7521 |
| Paula Rocha    | R. Anhaia             | 548   | Barra Funda    | 6958-2548  | AFT9087 |


### Carro
| Placa (PK)   | Marca   | Modelo | Cor    | Ano  |
|---------|---------|--------|--------|------|
| AFT9087 | VW      | Gol    | Preto  | 2007 |
| DXO9876 | Ford    | Ka     | Azul   | 2000 |
| EGT4631 | Renault | Clio   | Verde  | 2004 |
| LKM7380 | Fiat    | Palio  | Prata  | 1997 |
| BCD7521 | Ford    | Fiesta | Preto  | 1999 |

### Peças
| Código (PK) | Nome             | Valor |
|--------|------------------|--------|
| 1      | Vela             | 70     |
| 2      | Correia Dentada  | 125    |
| 3      | Trambulador      | 90     |
| 4      | Filtro de Ar     | 30     |

### Serviço

| Carro (PK e FK)   | Peça (PK e FK) | Quantidade | Valor | Data (PK)       |
|---------|------|------------|-------|------------|
| DXO9876 | 1    | 4          | 280   | 2020-08-01 |
| DXO9876 | 4    | 1          | 30    | 2020-08-01 |
| EGT4631 | 3    | 1          | 90    | 2020-08-02 |
| DXO9876 | 2    | 1          | 125   | 2020-08-07 |

- Consultar em Subqueries
  - Telefone do dono do carro Ka, Azul
  - Endereço concatenado do cliente que fez o serviço do dia 2020-08-02
- Consultar:
  - Placas dos carros de anos anteriores a 2001 Marca, modelo e cor, concatenado dos carros posteriores a 2005
  - Código e nome das peças que custam menos de R$80,00

## Exercicio3.sql

<strong> Estrutura das tabelas </strong>

### Pacientes

| CPF (PK)          | Nome           | Rua                | Nº   | Bairro       | Telefone | Data_Nasc  |
|---------------|----------------|---------------------|------|--------------|----------|------------|
| 35454562890   | José Rubens    | Campos Salles       | 2750 | Centro       | 21450998 | 1954-10-18 |
| 29865439810   | Ana Claudia    | Sete de Setembro    | 178  | Centro       | 97382764 | 1960-05-29 |
| 82176534800   | Marcos Aurélio | Timóteo Penteado    | 236  | Vila Galvão  | 68172651 | 1980-09-24 |
| 12386758770   | Maria Rita     | Castello Branco     | 7765 | Vila Rosália | NULL     | 1975-03-30 |
| 92173458910   | Joana de Souza | XV de Novembro      | 298  | Centro       | 21276578 | 1944-04-24 |

### Médicos
| Código (PK) | Nome               | Especialidade    |
|--------|--------------------|------------------|
| 1      | Wilson Cesar       | Pediatra         |
| 2      | Marcia Matos       | Geriatra         |
| 3      | Carolina Oliveira  | Ortopedista      |
| 4      | Vinicius Araujo    | Clínico Geral    |

### Prontuário
| Data (PK)      | CPF Paciente (PK e FK) | Código Médico (PK e FK) | Diagnóstico             | Medicamento         |
|------------|---------------|----------------|--------------------------|----------------------|
| 2020-09-10 | 35454562890   | 2              | Reumatismo               | Celebra              |
| 2020-09-10 | 92173458910   | 2              | Renite Alérgica          | Allegra              |
| 2020-09-12 | 29865439810   | 1              | Inflamação de garganta   | Nimesulida           |
| 2020-09-13 | 35454562890   | 2              | H1N1                     | Tamiflu              |
| 2020-09-15 | 82176534800   | 4              | Gripe                    | Resprin              |
| 2020-09-15 | 12386758770   | 3              | Braço Quebrado           | Dorflex + Gesso      |

- Consultar:
  - Nome e Endereço (concatenado) dos pacientes com mais de 50 anos
  - Qual a especialidade de Carolina Oliveira
  - Qual medicamento receitado para reumatismo
- Consultar em subqueries:
  - Diagnóstico e Medicamento do paciente José Rubens em suas consultas
  - Nome e especialidade do(s) Médico(s) que atenderam José Rubens. Caso a especialidade tenha mais de 3 letras, mostrar apenas as 3 primeiras letras concatenada com um ponto final (.)
  - CPF (Com a máscara XXX.XXX.XXX-XX), Nome, Endereço completo (Rua, nº - Bairro), Telefone (Caso nulo, mostrar um traço (-)) dos pacientes do médico Vinicius
  - Quantos dias fazem da consulta de Maria Rita até hoje

- Alterar o telefone da paciente Maria Rita, para 98345621
- Alterar o Endereço de Joana de Souza para Voluntários da Pátria, 1980, Jd. Aeroporto


## Exercicio4.sql

<strong> Estrutura das tabelas </strong>

### Cliente
| CPF   (PK)         | Nome          | Telefone   |
|----------------|---------------|------------|
| 345789092-90   | Julio Cesar   | 8273-6541  |
| 251865337-10   | Maria Antonia | 8765-2314  |
| 876273154-16   | Luiz Carlos   | 6128-9012  |
| 791826398-00   | Paulo Cesar   | 9076-5273  |

### Produto
| Código (PK) | Descrição                                                   | Fornecedor (FK) | Preço   |
|--------|--------------------------------------------------------------|------------|---------|
| 1      | Monitor 19 pol.                                              | 1          | 449,99  |
| 2      | Netbook 1GB Ram 4 Gb HD                                      | 2          | 699,99  |
| 3      | Gravador de DVD - Sata                                       | 1          | 99,99   |
| 4      | Leitor de CD                                                 | 1          | 49,99   |
| 5      | Processador - Phenom X3 - 2.1GHz                              | 3          | 349,99  |
| 6      | Mouse                                                        | 4          | 19,99   |
| 7      | Teclado                                                      | 4          | 25,99   |
| 8      | Placa de Video - Nvidia 9800 GTX - 256MB/256 bits            | 5          | 599,99  |

### Fornecedor
| ID (PK) | Nome        | Logradouro           | Nº     | Complemento | Cidade     |
|----|-------------|----------------------|--------|-------------|------------|
| 1  | LG          | Rod. Bandeirantes    | 70000  | Km 70       | Itapeva    |
| 2  | Asus        | Av. Nações Unidas    | 10206  | Sala 225    | São Paulo  |
| 3  | AMD         | Av. Nações Unidas    | 10206  | Sala 1095   | São Paulo  |
| 4  | Leadership  | Av. Nações Unidas    | 10206  | Sala 87     | São Paulo  |
| 5  | Inno        | Av. Nações Unidas    | 10206  | Sala 34     | São Paulo  |

### Venda
| Código (PK) | Produto (PK e FK) | Cliente (PK e FK)         | Quantidade | Valor total | Data       |
|--------|---------|------------------|------------|--------------|------------|
| 1      | 1       | 251865337-10     | 1          | 449,99       | 03/09/2009 |
| 1      | 4       | 251865337-10     | 1          | 49,99        | 03/09/2009 |
| 1      | 5       | 251865337-10     | 1          | 349,99       | 03/09/2009 |
| 2      | 6       | 791826398-00     | 4          | 79,96        | 06/09/2009 |
| 3      | 8       | 876273154-16     | 1          | 599,99       | 06/09/2009 |
| 3      | 3       | 876273154-16     | 1          | 99,99        | 06/09/2009 |
| 3      | 7       | 876273154-16     | 1          | 25,99        | 06/09/2009 |
| 4      | 2       | 345789092-90     | 2          | 1399,98      | 08/09/2009 |

- Inserir na tabela Fornecedor, a coluna Telefone
e os seguintes dados:
  - 1	7216-5371
  - 2	8715-3738
  - 4	3654-6289



- Consultar:
  - Consultar no formato dd/mm/aaaa: 
    - Data da Venda 4
  - Consultar por ordem alfabética de nome, o nome, o enderço concatenado e o telefone dos fornecedores
  - Produto, quantidade e valor total do comprado por Julio Cesar
  - Data, no formato dd/mm/aaaa e valor total do produto comprado por  Paulo Cesar
  - Consultar, em ordem decrescente, o nome e o preço de todos os produtos

## Exercicio5.sql

<strong> Estrutura das tabelas </strong>

### Produto
| Código (PK) | Nome                        | Valor Unitário | Quantidade Estoque | Descrição              | Código Fornecedor (FK) |
|--------|-----------------------------|----------------|---------------------|-------------------------|--------------------|
| 1      | Banco Imobiliário           | 65.00          | 15                  | Versão Super Luxo       | 1001               |
| 2      | Puzzle 5000 peças           | 50.00          | 5                   | Mapas Mundo             | 1005               |
| 3      | Faqueiro                    | 350.00         | 0                   | 120 peças               | 1004               |
| 4      | Jogo para churrasco         | 75.00          | 3                   | 7 peças                 | 1004               |
| 5      | Tablet                      | 750.00         | 29                  | Tablet                  | 1003               |
| 6      | Detetive                    | 49.00          | 0                   | Nova Versão do Jogo     | 1001               |
| 7      | Chocolate com Paçoquinha    | 6.00           | 0                   | Barra                   | 1002               |
| 8      | Galak                       | 5.00           | 65                  | Barra                   | 1002               |

### Fornecedor
| Código (PK) | Nome       | Atividade              | Telefone  |
|--------|------------|-------------------------|-----------|
| 1001   | Estrela    | Brinquedo               | 41525898  |
| 1002   | Lacta      | Chocolate               | 42698596  |
| 1003   | Asus       | Informática             | 52014596  |
| 1004   | Tramontina | Utensílios Domésticos   | 50563985  |
| 1005   | Grow       | Brinquedos              | 47896325  |
| 1006   | Mattel     | Bonecos                 | 59865898  |

### Cliente
| Código (PK) | Nome           | Logradouro                 | Número | Telefone  | Data_Nasc  |
|--------|----------------|----------------------------|--------|-----------|------------|
| 33601  | Maria Clara     | R. 1° de Abril             | 870    | 96325874  | 2000-08-15 |
| 33602  | Alberto Souza   | R. XV de Novembro          | 987    | 95873625  | 1985-02-02 |
| 33603  | Sonia Silva     | R. Voluntários da Pátria   | 1151   | 75418596  | 1957-08-23 |
| 33604  | José Sobrinho   | Av. Paulista               | 250    | 85236547  | 1986-12-09 |
| 33605  | Carlos Camargo  | Av. Tiquatira              | 9652   | 75896325  | 1971-03-25 |

### Pedido
| Código (PK) | Código_Cliente (PK e FK) | Código_Produto (PK e FK) | Quantidade | Previsão_Entrega |
|--------|----------------|----------------|------------|------------------|
| 99001  | 33601          | 1              | 1          | 2012-06-07       |
| 99001  | 33601          | 2              | 1          | 2012-06-07       |
| 99001  | 33601          | 8              | 3          | 2012-06-07       |
| 99002  | 33602          | 2              | 1          | 2012-06-09       |
| 99002  | 33602          | 4              | 3          | 2012-06-09       |
| 99003  | 33605          | 5              | 1          | 2012-06-15       |

- Consultar a quantidade, valor total e valor total com desconto (25%) dos itens comprados par Maria Clara.
- Verificar quais brinquedos não tem itens em estoque.
- Alterar para reduzir em 10% o valor das barras de chocolate.
- Alterar a quantidade em estoque do faqueiro para 10 peças.
- Consultar quantos clientes tem mais de 40 anos.
- Consultar Nome e telefone dos fornecedores de Brinquedos e Chocolate.
- Consultar nome e desconto de 25% no preço dos produtos que custam menos de R$50,00
- Consultar nome e aumento de 10% no preço dos produtos que custam mais de R$100,00
- Consultar desconto de 15% no valor total de cada produto da venda 99001.
- Consultar Código do pedido, nome do cliente e idade atual do cliente

## Exercicio6.sql

<strong> Estrutura das tabelas </strong>

### Motorista

| Código (PK) | Nome             | Data_nascimento | Naturalidade |
|--------|------------------|------------------|---------------|
| 12341  | Julio Cesar      | 1978-04-18       | São Paulo     |
| 12342  | Mario Carmo      | 2002-07-29       | Americana     |
| 12343  | Lucio Castro     | 1969-12-01       | Campinas      |
| 12344  | André Figueiredo | 1999-05-14       | São Paulo     |
| 12345  | Luiz Carlos      | 2001-01-09       | São Paulo     |

### Ônibus
| Placa (PK)   | Marca    | Ano  | Descrição         |
|----------|----------|------|-------------------|
| adf0965  | Mercedes | 1999 | Leito             |
| bhg7654  | Mercedes | 2002 | Sem Banheiro      |
| dtr2093  | Mercedes | 2001 | Ar Condicionado   |
| gui7625  | Volvo    | 2001 | Ar Condicionado   |

### Viagem
| Código (PK) | Ônibus (FK)   | Motorista (FK) | Hora de Saída | Hora de Chegada | Destino       |
|--------|----------|-----------|----------------|------------------|----------------|
| 101    | adf0965  | 12343     | 10h            | 12h              | Campinas       |
| 102    | gui7625  | 12341     | 7h             | 12h              | Araraquara     |
| 103    | bhg7654  | 12345     | 14h            | 22h              | Rio de Janeiro |
| 104    | dtr2093  | 12344     | 18h            | 21h              | Sorocaba       |

- Consultar, da tabela viagem, todas as horas de chegada e saída, convertidas em formato HH:mm (108) e seus destinos
- Consultar, com subquery, o nome do motorista que viaja para Sorocaba
- Consultar, com subquery, a descrição do ônibus que vai para o Rio de Janeiro
- Consultar, com Subquery, a descrição, a marca e o ano do ônibus dirigido por Luiz Carlos
Consultar o nome, a idade e a naturalidade dos motoristas com mais de 30 anos

## Exercicio7.sql

<strong> Estrutura das tabelas </strong>

### Clientes

| RG         | CPF          | Nome           | Logradouro     | Número |
|------------|--------------|----------------|----------------|--------|
| 29531844   | 34519878040  | Luiz André     | R. Astorga     | 500    |
| 13514996x  | 84984285630  | Maria Luiza    | R. Piauí       | 174    |
| 121985541  | 23354997310  | Ana Barbara    | Av. Jaceguai   | 1141   |
| 23987746x  | 43587669920  | Marcos Alberto | R. Quinze      | 22     |

### Pedido
| Nota Fiscal | Valor | Data       | RG_Cliente  |
|-------------|--------|------------|-------------|
| 1001        | 754    | 2018-04-01 | 121985541   |
| 1002        | 350    | 2018-04-02 | 121985541   |
| 1003        | 30     | 2018-04-02 | 29531844    |
| 1004        | 1500   | 2018-04-03 | 13514996x   |

### Mercadoria
| Código | Descrição     | Preço | Qtd | Cod_Fornecedor |
|--------|---------------|-------|-----|----------------|
| 10     | Mouse         | 24    | 30  | 1              |
| 11     | Teclado       | 50    | 20  | 1              |
| 12     | Cx. De Som    | 30    | 8   | 2              |
| 13     | Monitor 17    | 350   | 4   | 3              |
| 14     | Notebook      | 1500  | 7   | 4              |

### Fornecedor
| Código | Nome      | Logradouro                 | Número | País | Área | Telefone   | CNPJ            | Cidade     | Transporte | Moeda |
|--------|-----------|-----------------------------|--------|------|------|-------------|------------------|------------|------------|--------|
| 1      | Clone     | Av. Nações Unidas, 12000    | 12000  | BR   | 55   | 1141487000 | NULL             | São Paulo  | NULL       | R$     |
| 2      | Logitech  | 28th Street, 100            | 100    | USA  | 1    | 2127695100 | NULL             | NULL       | Avião      | US$    |
| 3      | LG        | Rod. Castello Branco        | NULL   | BR   | 55   | 800664400  | 4159978100001    | Sorocaba   | NULL       | R$     |
| 4      | PcChips   | Ponte da Amizade            | NULL   | PY   | 595  | NULL       | NULL             | NULL       | Navio      | US$    |

FK: Cliente em Pedido - Fornecedor em Mercadoria

- Pede-se: 
  - Quando o endereço concatenado não tiver número, colocar só o logradouro e o país, quando tiver colocar, também o número)
  - Nota: (CPF deve vir sempre mascarado no formato XXX.XXX.XXX-XX e RG Sempre com um traçao antes do último dígito (Algo como XXXXXXXX-X), mas alguns tem 8 e outros 9 dígitos)

- Consultas
  - Consultar 10% de desconto no pedido 1003
  - Consultar 5% de desconto em pedidos com valor maior de R$700,00
  - Consultar e atualizar aumento de 20% no valor de marcadorias com estoque menor de 10
  - Data e valor dos pedidos do Luiz
CPF, Nome e endereço concatenado do cliente de nota 1004
  - País e meio de transporte da Cx. De som
  - Nome e Quantidade em estoque dos produtos fornecidos pela Clone
  - Endereço concatenado e telefone dos fornecedores do monitor. (Telefone brasileiro (XX)XXXX-XXXX ou XXXX-XXXXXX (Se for 0800), Telefone Americano (XXX)XXX-XXXX)
  - Tipo de moeda que se compra o notebook
  - Considerando que hoje é 03/02/2019, há quantos dias foram feitos os pedidos e, criar uma coluna que escreva "Pedido antigo" para pedidos feitos há mais de 6 meses e pedido recente para os outros
  - Nome e Quantos pedidos foram feitos por cada cliente
  - RG,CPF,Nome e Endereço dos cliente cadastrados que Não Fizeram pedidos

## Exercicio8.sql

<strong> Estrutura das tabelas </strong>

| Código (PK) | Nome             | Endereço                                | Telefone  | Telefone Comercial |
|--------|------------------|------------------------------------------|-----------|---------------------|
| 1      | Luis Paulo       | R. Xv de Novembro, 100                   | 45657878  |                     |
| 2      | Maria Fernanda   | R. Anhaia, 1098                          | 27289098  | 40040090            |
| 3      | Ana Claudia      | Av. Voluntários da Pátria, 876          | 21346548  |                     |
| 4      | Marcos Henrique  | R. Pantojo, 76                           | 51425890  | 30394540            |
| 5      | Emerson Souza    | R. Pedro Álvares Cabral, 97             | 44236545  | 39389900            |
| 6      | Ricardo Santos   | Trav. Hum, 10                            | 98789878  |                     |

| Código (PK) | Nome            | Corredor (FK) | Tipo (FK)  | Valor |
|--------|------------------|----------|--------|--------|
| 1001   | Pão de Forma     | 101      | 10001  | 3,5    |
| 1002   | Presunto         | 101      | 10002  | 2,0    |
| 1003   | Cream Cracker    | 103      | 10003  | 4,5    |
| 1004   | Água Sanitária   | 104      | 10004  | 6,5    |
| 1005   | Maçã             | 105      | 10005  | 0,9    |
| 1006   | Palha de Aço     | 106      | 10006  | 1,3    |
| 1007   | Lasanha          | 107      | 10007  | 9,7    |

### Corredores
| Código (PK) | Tipo (FK)   | Nome       |
|--------|--------|------------|
| 101    | 10001  | Padaria    |
| 102    | 10002  | Calçados   |
| 103    | 10003  | Biscoitos  |
| 104    | 10004  | Limpeza    |
| 105    |        |            |
| 106    |        |            |
| 107    | 10007  | Congelados |

### Tipos de Mercadoria
| Código (PK)  | Nome      |
|---------|-----------|
| 10001   | Pães      |
| 10002   | Frios     |
| 10003   | Bolacha   |
| 10004   | Clorados  |
| 10005   | Frutas    |
| 10006   | Esponjas  |
| 10007   | Massas    |
| 10008   | Molhos    |

### Compra
| Nota Fiscal (PK) | Código Cliente (FK) | Valor |
|-------------|----------------|--------|
| 1234        | 2              | 200    |
| 2345        | 4              | 156    |
| 3456        | 6              | 354    |
| 4567        | 3              | 19     |

- Pede-se:
  - Valor da Compra de Luis Paulo
  - Valor da Compra de Marcos Henrique
  - Endereço e telefone do comprador de Nota Fiscal = 4567
  - Valor da mercadoria cadastrada do tipo " Pães"
  - Nome do corredor onde está a Lasanha
  - Nome do corredor onde estão os clorados

## Exercicio10.sql

<strong> Estrutura das tabelas </strong>

### Medicamentos
| CÓDIGO (PK) | NOME                          | APRESENTAÇÃO         | UNIDADE DE CADASTRO | PREÇO PROPOSTO (R$) |
|--------|-------------------------------|-----------------------|----------------------|----------------------|
| 1      | Acetato de medroxiprogesterona | 150 mg/ml            | Ampola               | 6,700                |
| 2      | Aciclovir                      | 200mg/comp.          | Comprimido           | 0,280                |
| 3      | Ácido Acetilsalicílico         | 500mg/comp.          | Comprimido           | 0,035                |
| 4      | Ácido Acetilsalicílico         | 100mg/comp.          | Comprimido           | 0,030                |
| 5      | Ácido Fólico                   | 5mg/comp.            | Comprimido           | 0,054                |
| 6      | Albendazol                     | 400mg/comp. mastigável | Comprimido         | 0,560                |
| 7      | Alopurinol                     | 100mg/comp.          | Comprimido           | 0,080                |
| 8      | Amiodarona                     | 200mg/comp.          | Comprimido           | 0,200                |
| 9      | Amitriptilina (Cloridrato)     | 25mg/comp.           | Comprimido           | 0,220                |
| 10     | Amoxicilina                    | 500mg/cáps.          | Cápsula              | 0,190                |

### Cliente
| CPF (PK)          | Nome          | Rua                    | Nº  | Bairro       | Telefone  |
|---------------|---------------|-------------------------|-----|--------------|-----------|
| 34390898700   | Maria Zélia   | Anhaia                  | 65  | Barra Funda  | 92103762  |
| 21345986290   | Roseli Silva  | Xv. De Novembro         | 987 | Centro       | 82198763  |
| 86927981825   | Carlos Campos | Voluntários da Pátria   | 1276| Santana      | 98172361  |
| 31098120900   | João Perdizes | Carlos de Campos        | 90  | Pari         | 61982371  |

### Venda
| Nota Fiscal (PK) | CPF_cliente (PK e FK)   | Código_Medicamento (PK e FK) | Quantidade | Valor Total | Data       |
|-------------|----------------|---------------------|------------|--------------|------------|
| 31501       | 86927981825    | 10                  | 3          | 0,57         | 2020-11-01 |
| 31501       | 86927981825    | 2                   | 10         | 2,80         | 2020-11-01 |
| 31501       | 86927981825    | 5                   | 30         | 1,05         | 2020-11-01 |
| 31501       | 86927981825    | 8                   | 30         | 6,60         | 2020-11-01 |
| 31502       | 34390898700    | 8                   | 15         | 3,00         | 2020-11-01 |
| 31502       | 34390898700    | 2                   | 10         | 2,80         | 2020-11-01 |
| 31502       | 34390898700    | 9                   | 10         | 2,20         | 2020-11-01 |
| 31503       | 31098120900    | 1                   | 20         | 134,00       | 2020-11-02 |

- Consultar
  - Nome, apresentação, unidade e valor unitário dos remédios que ainda não foram vendidos. Caso a unidade de cadastro seja comprimido, mostrar Comp.
  - Nome dos clientes que compraram Amiodarona
  - CPF do cliente, endereço concatenado, nome do medicamento (como nome de remédio),  apresentação do remédio, unidade, preço proposto, quantidade vendida e valor total dos remédios vendidos a Maria Zélia
  - Data de compra, convertida, de Carlos Campos

- Alterar o nome da  Amitriptilina(Cloridrato) para Cloridrato de Amitriptilina

## Exercicio11.sql

<strong> Estrutura das tabelas </strong>

### Planos de Saúde
| Código | Nome            | Telefone  |
|--------|------------------|-----------|
| 1234   | Amil             | 41599856  |
| 2345   | Sul América      | 45698745  |
| 3456   | Unimed           | 48759836  |
| 4567   | Bradesco Saúde   | 47265897  |
| 5678   | Intermédica      | 41415269  |

### Paciente

| CPF           | Nome         | Rua                      | Número | Bairro       | Telefone  | Plano de Saúde |
|---------------|--------------|---------------------------|--------|--------------|-----------|----------------|
| 85987458920   | Maria Paula  | R. Voluntários da Pátria  | 589    | Santana      | 98458741  | 2345           |
| 87452136900   | Ana Julia    | R. XV de Novembro         | 657    | Centro       | 69857412  | 5678           |
| 23659874100   | João Carlos  | R. Sete de Setembro       | 12     | República    | 74859632  | 1234           |
| 63259874100   | José Lima    | R. Anhaia                 | 768    | Barra Funda  | 96524156  | 2345           |

### Médico
| Código | Nome     | Especialidade             | Plano de Saúde |
|--------|----------|---------------------------|----------------|
| 1      | Claudio  | Clínico Geral             | 1234           |
| 2      | Larissa  | Ortopedista               | 2345           |
| 3      | Juliana  | Otorrinolaringologista    | 4567           |
| 4      | Sérgio   | Pediatra                  | 1234           |
| 5      | Julio    | Clínico Geral             | 4567           |
| 6      | Samara   | Cirurgião                 | 1234           |

- Consultas
  - Consultar Nome e especialidade dos médicos da Amil
  - Consultar Nome, Endereço concatenado, Telefone e Nome do Plano de Saúde de todos os pacientes
  - Consultar Telefone do Plano de  Saúde de Ana Júlia
  - Consultar Plano de Saúde que não tem pacientes cadastrados
  - Consultar Planos de Saúde que não tem médicos cadastrados
  - Consultar Data da consulta, Hora da consulta, nome do médico, nome do paciente e diagnóstico de todas as consultas
  - Consultar Nome do médico, data e hora de consulta e diagnóstico de José Lima
  - Consultar Diagnóstico e Quantidade de consultas que aquele diagnóstico foi dado (Coluna deve chamar qtd)
  - Consultar Quantos Planos de Saúde que não tem médicos cadastrados


- Alterar o nome de João Carlos para João Carlos da Silva
- Deletar o plano de Saúde Unimed
- Renomear a coluna Rua da tabela Paciente para Logradouro
- Inserir uma coluna, na tabela Paciente, de nome data_nasc e inserir os valores (1990-04-18,1981-03-25,2004-09-04 e 1986-06-18) respectivamente

## Exercicio12.sql

<strong> Estrutura das tabelas </strong>

### Plano
| CodPlano | NomePlano     | ValorPlano |
|----------|---------------|------------|
| 1        | 100 Minutos   | 80         |
| 2        | 150 Minutos   | 130        |
| 3        | 200 Minutos   | 160        |
| 4        | 250 Minutos   | 220        |
| 5        | 300 Minutos   | 260        |
| 6        | 600 Minutos   | 350        |

### Serviços
| CodServico | NomeServico       | ValorServico |
|------------|-------------------|--------------|
| 1          | 100 SMS           | 10           |
| 2          | SMS Ilimitado     | 30           |
| 3          | Internet 500 MB   | 40           |
| 4          | Internet 1 GB     | 60           |
| 5          | Internet 2 GB     | 70           |

### Cliente
| CodCliente | NomeCliente | DataInicio  |
|------------|-------------|-------------|
| 1234       | Cliente A   | 2012-10-15  |
| 2468       | Cliente B   | 2012-11-20  |
| 3702       | Cliente C   | 2012-11-25  |
| 4936       | Cliente D   | 2012-12-01  |
| 6170       | Cliente E   | 2012-12-18  |
| 7404       | Cliente F   | 2013-01-20  |
| 8638       | Cliente G   | 2013-01-25  |

### Contatos
| CodCliente | CodPlano | CodServico | Status | Data       |
|------------|----------|------------|--------|------------|
| 1234       | 3        | 1          | E      | 2012-10-15 |
| 1234       | 3        | 3          | E      | 2012-10-15 |
| 1234       | 3        | 3          | A      | 2012-10-16 |
| 1234       | 3        | 1          | A      | 2012-10-16 |
| 2468       | 4        | 4          | E      | 2012-11-20 |
| 2468       | 4        | 4          | A      | 2012-11-21 |
| 6170       | 6        | 2          | E      | 2012-12-18 |
| 6170       | 6        | 5          | E      | 2012-12-19 |
| 6170       | 6        | 2          | A      | 2012-12-20 |
| 6170       | 6        | 5          | A      | 2012-12-21 |
| 1234       | 3        | 1          | D      | 2013-01-10 |
| 1234       | 3        | 3          | D      | 2013-01-10 |
| 1234       | 2        | 1          | E      | 2013-01-10 |
| 1234       | 2        | 1          | A      | 2013-01-11 |
| 2468       | 4        | 4          | D      | 2013-01-25 |
| 7404       | 2        | 1          | E      | 2013-01-20 |
| 7404       | 2        | 5          | E      | 2013-01-20 |
| 7404       | 2        | 5          | A      | 2013-01-21 |
| 7404       | 2        | 1          | A      | 2013-01-22 |
| 8638       | 6        | 5          | E      | 2013-01-25 |
| 8638       | 6        | 5          | A      | 2013-01-26 |
| 7404       | 2        | 5          | D      | 2013-02-03 |

- Status de contrato A(Ativo), D(Desativado), E(Espera)	
- Um plano só é válido se existe pelo menos um serviço associado a ele	

- Consultas    
  - Consultar o nome do cliente, o nome do plano, a quantidade de estados de contrato (sem repetições) por contrato, dos planos cancelados, ordenados pelo nome do cliente	
  - Consultar o nome do cliente, o nome do plano, a quantidade de estados de contrato (sem repetições) por contrato, dos planos não cancelados, ordenados pelo nome do cliente	
  - Consultar o nome do cliente, o nome do plano, e o valor da conta de cada contrato que está ou esteve ativo, sob as seguintes condições:	
    - A conta é o valor do plano, somado à soma dos valores de todos os serviços
    - Caso a conta tenha valor superior a R$400.00, deverá ser incluído um desconto de 8%
    - Caso a conta tenha valor entre R$300,00 a R$400.00, deverá ser incluído um desconto de 5%
    - Caso a conta tenha valor entre R$200,00 a R$300.00, deverá ser incluído um desconto de 3%
    - Contas com valor inferiores a R$200,00 não tem desconto
  - Consultar o nome do cliente, o nome do serviço, e a duração, em meses (até a data de hoje) do serviço, dos cliente que nunca cancelaram nenhum plano	
