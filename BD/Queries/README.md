# Queries

- [Condicionais](./Condicionais/): Consultas com Condições (WHERE) usando alias (AS), operadores lógicos (AND, OR), Conversões de dados (CONVERT) e LIKE para análise de textos
- [Subqueries e Selects CASES](./SubqueriesSelectsCases/): Consultas com CASEs (CASE WHEN), uma forma de imprimir os dados na tela em um determinado formato de acordo com condições adicionadas, Subqueries (SELECT que tem outro(s) SELECTs) e uso de outros procedimentos nativos do SQLServer (DATEADD, DATEDIFF e SUBSTRING)
- [Junções (JOINS)](./Joins/): Consultas com junções entre tabelas (INNER JOIN) e de dados que podem não estar disponíveis na tabela que não possuí a chave referenciada (estrangeira) da PRIMARY KEY da conexão com a anterior (LEFT/RIGHT OUTER JOIN)
- [Funções Agregadas](./Agregacoes/): Uso de funções agregadas (funções que desenvolvem cálculos em SELECTs que possuem junções) e uso do HAVING (WHERE para funções agregadas) e GROUP BY (Agrupamento dos dados para as funções)

## Exercicios extras sobre consultas

### Mecanica.sql

- Consultar o nome do cliente e a quantidade de carros por cliente, ordenados pelo nome do cliente ascendentemente
- Consultar o nome do cliente e a média do Ano_modelo (alias media_modelo) de carros por cliente, ordenados pelo media_modelo em ordem descrescente
- Consultar o nome do cliente e a soma do custo_total do reparo (soma_reparos), ordenados pelo nome do cliente ascendentemente
- Consultar o nome do cliente, a marca, o modelo e o ano_modelo do veículo e o tempo e o custo_total do reparo de maior valor dentre os cadastrados

### ExercicioExtra1.sql

1) Apresentar marca e modelo de carro e a soma total da distância percorrida pelos carros, em viagens, de uma dada empresa, ordenado pela distância percorrida
2) Apresentar nome das empresas cuja soma total da distância percorrida pelos carros, em viagens, é superior a 50000 km
3) Apresentar nome das empresas cuja soma total da distância percorrida pelos carros e a media das distâncias percorridas por seus carros em viagens.
- A média deve ser exibida em uma coluna chamada mediaDist e com 2 casas decimais apenas.
- Deve-se ordenar a saída pela média descrescente
4) Apresentar nome das empresas cujos carro percorreram a maior distância dentre as cadastradas
5) Apresentar nome das empresas e a quantidade de carros cadastrados para cada empresa 
- Se a empresa tiver 3 ou mais carros
- A saída deve ser ordenada pela quantidade de carros, descrescente
6) Consultar Nomes das empresas que não tem carros cadastrados
7) Consultar Marca e modelos dos carros que não fizeram viagens
8) Consultar quantas viagens foram feitas por cada carro (marca e modelo) de cada empresa em ordem ascendente de nome de empresa e descendente de quantidade
9) Consultar o nome da empresa, a marca e o modelo do carro, a distância percorrida e o valor total ganho por viagem, sabendo que para distâncias inferiores a 1000 km, o valor é R$10,00 por km e para viagens superiores a 1000 km, o valor é R$15,00 por km.
10) Apresentar o nome da empresa e a média de distância percorrida por seus carros. A saída da média deve ter até 2 casas decimais e deve ser ordenada pela média da distância percorrida 
11) Apresentar marca e modelo do carro, além do nome da empresa e a data no formato (DD/MM/AAAA) do carro que fez a última viagem dentre os cadastrados 
12) Considerando que hoje é 01/01/2023, apresentar a marca e o modelo do carro, além do nome da empresa e a quantidade de dias da viagem, dos carros que tiveram viagens nos últimos 3 meses. Ordenar (todos ascendentes) por quantidade de dias, marca, modelo e nome da empresa.

### ExercicioExtra2.sql

1) Consultar nome, valor unitário, nome da editora e nome do autor dos livros do estoque que foram vendidos. Não podem haver repetições.	
2) Consultar nome do livro, quantidade comprada e valor de compra da compra 15051	
3) Consultar Nome do livro e site da editora dos livros da Makron books (Caso o site tenha mais de 10 dígitos, remover o www.).	
4) Consultar nome do livro e Breve Biografia do David Halliday	
5) Consultar código de compra e quantidade comprada do livro Sistemas Operacionais Modernos	
6) Consultar quais livros não foram vendidos	
7) Consultar quais livros foram vendidos e não estão cadastrados. 
- Caso o nome dos livros terminem com espaço, fazer o trim apropriado.	
8) Consultar Nome e site da editora que não tem Livros no estoque (Caso o site tenha mais de 10 dígitos, remover o www.)	
9) Consultar Nome e biografia do autor que não tem Livros no estoque (Caso a biografia inicie com Doutorado, substituir por Ph.D.)	
10) Consultar o nome do Autor, e o maior valor de Livro no estoque. Ordenar por valor descendente
11) Consultar o código da compra, o total de livros comprados e a soma dos valores gastos. Ordenar por Código da Compra ascendente.	
12) Consultar o nome da editora e a média de preços dos livros em estoque.Ordenar pela Média de Valores ascendente.	
13) Consultar o nome do Livro, a quantidade em estoque o nome da editora, o site da editora (Caso o site tenha mais de 10 dígitos, remover o www.), criar uma coluna status onde:	
- Caso tenha menos de 5 livros em estoque, escrever Produto em Ponto de Pedido
- Caso tenha entre 5 e 10 livros em estoque, escrever Produto Acabando
- Caso tenha mais de 10 livros em estoque, escrever Estoque Suficiente
- A Ordenação deve ser por Quantidade ascendente
14) Para montar um relatório, é necessário montar uma consulta com a seguinte saída: Código do Livro, Nome do Livro, Nome do Autor, Info Editora (Nome da Editora + Site) de todos os livros	
-  Só pode concatenar sites que não são nulos
15) Consultar Codigo da compra, quantos dias da compra até hoje e quantos meses da compra até hoje	
16) Consultar o código da compra e a soma dos valores gastos das compras que somam mais de 200.00	

### ExercicioExtra3.sql
1) Consultar a quantidade, valor total e valor total com desconto (25%) dos itens comprados par Maria Clara.
2) Consultar quais brinquedos não tem itens em estoque.
3) Consultar quais nome e descrições de produtos que não estão em pedidos
4) Alterar a quantidade em estoque do faqueiro para 10 peças.
5) Consultar Quantos clientes tem mais de 40 anos.
6) Consultar Nome e telefone (Formatado XXXX-XXXX) dos fornecedores de Brinquedos e Chocolate.
7) Consultar nome e desconto de 25% no preço dos produtos que custam menos de R$50,00
8) Consultar nome e aumento de 10% no preço dos produtos que custam mais de R$100,00
9) Consultar desconto de 15% no valor total de cada produto da venda 99001.
10) Consultar Código do pedido, nome do cliente e idade atual do cliente
11) Consultar o nome do fornecedor do produto mais caro
12) Consultar a média dos valores cujos produtos ainda estão em estoque
13) Consultar o nome do cliente, endereço composto por logradouro e número, o valor unitário do produto, o valor total (Quantidade * valor unitario) da compra do cliente de nome Maria Clara
14) Considerando que o pedido de Maria Clara foi entregue 15/03/2023, consultar quantos dias houve de atraso. A cláusula do WHERE deve ser o nome da cliente.
15) Consultar qual a nova data de entrega para o pedido de Alberto% sabendo que se pediu 9 dias a mais. A cláusula do WHERE deve ser o nome do cliente. A data deve ser exibida no formato dd/mm/aaaa.