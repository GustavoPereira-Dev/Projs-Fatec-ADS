# Triggers (Gatilhos)

## Triggers1.sql


1) Considere um quadrangular final de times de volei com 4 times
Time 1, Time 2 Time 3 e Time 4
Todos jogarão contra todos.
Os resultados dos jogos serão armazenados em uma tabela

### Tabela times
| Cod Time | Nome Time |
|----------|-----------|
| 1        | Time 1    |
| 2        | Time 2    |
| 3        | Time 3    |
| 4        | Time 4    |

### Jogos
(Cod Time A | Cod Time B | Set Time A | Set Time B)


- Considera-se vencedor o time que fez 3 de 5 sets.
- Se a vitória for por 3 x 2, o time vencedor ganha 2 pontos e o time perdedor ganha 1.
- Se a vitória for por 3 x 0 ou 3 x 1, o vencedor ganha 3 pontos e o perdedor, 0.
- Fazer uma UDF que apresente:
  - (Nome Time | Total Pontos | Total Sets Ganhos | Total Sets Perdidos | Set Average (Ganhos - perdidos))
- Fazer uma trigger que verifique se os inserts dos sets estão corretos (Máximo 5 sets por jogo, sendo que o vencedor tem no máximo 3 sets)

## Triggers2.sql

Uma empresa vende produtos alimentícios
- A empresa dá pontos, para seus clientes, que podem ser revertidos em prêmios
- Para não prejudicar a tabela venda, nenhum produto pode ser deletado, mesmo que não
venha mais a ser vendido
- Para não prejudicar os relatórios e a contabilidade, a tabela venda não pode ser alterada.
- Ao invés de alterar a tabela venda deve-se exibir uma tabela com o nome do último cliente que comprou e o valor da última compra
- Após a inserção de cada linha na tabela venda, 10% do total deverá ser transformado em pontos.
- Se o cliente ainda não estiver na tabela de pontos, deve ser inserido automaticamente após sua primeira compra
- Se o cliente atingir 1 ponto, deve receber uma mensagem (PRINT SQL Server) dizendo que ganhou e remove esse 1 ponto da tabela de pontos

```sql
CREATE DATABASE ex_triggers_07
GO
USE ex_triggers_07
GO
CREATE TABLE cliente (
    codigo INT NOT NULL,
    nome VARCHAR(70) NOT NULL
    PRIMARY KEY(codigo)
)
GO
CREATE TABLE venda (
    codigo_venda INT NOT NULL,
    codigo_cliente INT NOT NULL,
    valor_total DECIMAL(7,2) NOT NULL
    PRIMARY KEY (codigo_venda)
    FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo)
)
GO
CREATE TABLE pontos (
    codigo_cliente INT NOT NULL,
    total_pontos DECIMAL(4,1) NOT NULL
    PRIMARY KEY (codigo_cliente)
    FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo)
)
```

## Triggers3.sql
Considere o seguinte cenário:

### Produto
(Codigo | Nome | Descrição | Valor Unitário)

### Estoque
(Codigo_Produto | Qtd_Estoque | Estoque_Minimo)

### Venda
(Nota_Fiscal | Codigo_Produto | Quantidade )

- Fazer uma TRIGGER AFTER na tabela Venda que, uma vez feito um INSERT, verifique se a quantidade
está disponível em estoque. Caso esteja, a venda se concretiza, caso contrário, a venda deverá ser
cancelada e uma mensagem de erro deverá ser enviada. A mesma TRIGGER deverá validar, caso a
venda se concretize, se o estoque está abaixo do estoque mínimo determinado ou se após a venda,
ficará abaixo do estoque considerado mínimo e deverá lançar um print na tela avisando das duas
situações.
- Fazer uma UDF (User Defined Function) Multi Statement Table, que apresente, para uma dada nota
fiscal, a seguinte saída:
(Nota_Fiscal | Codigo_Produto | Nome_Produto | Descricao_Produto | Valor_Unitario | Quantidade
| Valor_Total*)

- <i> Considere que Valor_Total = Valor_Unitário * Quantidade </i>