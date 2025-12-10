# Procedure (Procedimentos/SP)

## ProcedureSQL1.sql

Criar uma database chamada academia, com 3 tabelas como seguem:

### Aluno
|Codigo_aluno|Nome|


### Atividade
|Codigo|Descrição|IMC|

Atividade
| Código     | Descrição                            | IMC                |
|----------- |--------------------------------------|--------------------|
| 1          | Corrida + Step                       |   18.5             |
| 2          | Biceps + Costas + Pernas             |   24.9             |
| 3          | Esteira + Biceps + Costas + Pernas   |   29.9             |
| 4          | Bicicleta + Biceps + Costas + Pernas |   34.9             |
| 5          | Esteira + Bicicleta                  |   39.9             |

### Atividadesaluno
|Codigo_aluno|Altura|Peso|IMC|Atividade|


<code> IMC = Peso (Kg) / Altura2 (M) </code>

Objetivo: Buscar a PRIMEIRA atividade referente ao IMC imediatamente acima do calculado.
Exemplo, se o IMC for igual a 27, deve-se fazer a atividade para IMC = 29.9
* Caso o IMC seja maior que 40, utilizar o código 5.
Criar uma Stored Procedure (sp_alunoatividades), com as seguintes regras:
- Se, dos dados inseridos, o código for nulo, mas, existirem nome, altura, peso, deve-se inserir um
novo registro nas tabelas aluno e aluno atividade com o imc calculado e as atividades pelas
regras estabelecidas acima.
- Se, dos dados inseridos, o nome for (ou não nulo), mas, existirem código, altura, peso, deve-se
verificar se aquele código existe na base de dados e atualizar a altura, o peso, o imc calculado e
as atividades pelas regras estabelecidas acima.
- Fazer a Stored Procedure atomizada, ou seja, chamando outras Stored Procedures com
responsabilidades específicas.

## ProcedureSQL2.sql
- Um sistema necessita que o usuário cadastre, obrigatoriamente,
um telefone celular e um telefone fixo, ou seja, não podem ser, os dois, nulos.
Um erro deve ser disparado caso haja violação.
- O sistema funciona em São Paulo e não cadastrará pessoas de outros
estados da federação.
- A pessoa pode não ter um dos dois telefones, mas não é permitido
omitir os 2 telefones, bem como cadastrar 2 fixos ou 2 celulares.
Um erro deve ser disparado caso haja violação.
- Telefones celular têm 9 dígitos e telefones fixo têm 8 dígitos, que
devem ser validados. Um erro deve ser disparado caso haja violação.
- Ao inserir uma pessoa, deve-se criar um novo ID incremental com
relação ao último ID inserido. A tabela não terá identity.
- Criar uma procedure que cadastre trate o CRUD da pessoa e as regras de 
negócio acima.

## QuerieDinamico1.sql
- Resolver em SQL Server a situação proposta:
- Considere a tabela Produto com os seguintes atributos:
  - Produto (Codigo | Nome | Valor)
- Considere a tabela ENTRADA e a tabela SAÍDA com os seguintes atributos:
  - (Codigo_Transacao | Codigo_Produto | Quantidade | Valor_Total)
- Cada produto que a empresa compra, entra na tabela ENTRADA. Cada produto que a empresa vende,
entra na tabela SAIDA.
- Criar uma procedure que receba um código (‘e’ para ENTRADA e ‘s’ para SAIDA), criar uma exceção de
erro para código inválido, receba o codigo_transacao, codigo_produto e a quantidade e preencha a
tabela correta, com o valor_total de cada transação de cada produto.