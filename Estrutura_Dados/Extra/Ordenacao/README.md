# Ordenação

[Ordenar Vetor](./OrdenarVetor/): Códigos e pequenos exercícios sobre a ordenação de vetores inteiros

[Ordenação de Objetos](./Pessoa/): Exercício com estruturação melhor e prática de ordenação de vetores de objetos (da classe Pessoa) pelos seus atribuitos contidos (nome, sobrenome e idade)

## Contextualização das Ordenações

### Bubble Sort

O Bubble Sort é baseado em um algoritmo simples, com um comportamento de percorrer o vetor diversas vezes, podendo ser de bom desempenho com vetores pequenos e menos desordenados. Perde desempenho em vetores médios ou grandes

#### Funcionamento (passo a passo)

- Pegar um vetor
- O número de repetições deverá ser igual ao tamanho do vetor
- A dinâmica se dá como segue:
  - Da primeira posição do vetor, até a penúltima posição;
  - Verificar se a posição atual é maior que a próxima;
  - Se for, elas devem trocar de posição;
  - O controle de posição deve ser incrementado;
- Ao final, o vetor estará ordenado crescentemente

<b>Exemplo em vídeo: </b>

[![Exemplo do Bubble Sort](https://img.youtube.com/vi/Iv3vgjM8Pv4/hqdefault.jpg)](https://youtu.be/Iv3vgjM8Pv4)

### Merge Sort

O Merge Sort tem bom desempenho em qualquer situação, seja referente ao tamanho do vetor ou à ordenação inicial dos dados. Baseado na técnica de divisão para conquista, utiliza a definição de uma posição central do vetor para dividir o vetor em sub vetor da esquerda e da direita e intercala os dados. Utiliza recursividade

#### Funcionamento (passo a passo)
- Pegar um vetor (ou parte dele)
- Identificar a posição central do vetor e dividi-lo em 2 (posição inicial – meio; meio + 1 – posicao final)
- Recursivamente, ir dividindo em sub vetores de esquerda e direita até que se encontre um vetor de 1 posição, retornando o próprio vetor;
- Nesse ponto, se retorna ao vetor anterior que deve intercalar os valores a fim de ordená-los
- A função de intercalar precisa conhecer a posição inicial, a posição final e o meio do vetor (ou sub vetor)
- Para cada posição do novo vetor:
  - Se o ponteiro da esquerda é maior que a posição do meio, o vetor inicial na posição do contador, recebe o valor do novo vetor na posição do ponteiro à direita. Incrementa-se o ponteiro da direita;
  - Senão, se o ponteiro da direta é maior que a posição do fim, o vetor inicial na posição do contador, recebe o valor do novo vetor na posição do ponteiro à esquerda. Incrementa-se o ponteiro da esquerda;
  - Senão, se o valor do novo vetor na posição do ponteiro à esquerda é menor que o valor do novo vetor na posição do ponteiro à direita, o vetor inicial na posição do contador, recebe o valor do novo vetor na posição do ponteiro à esquerda. Incrementa-se o ponteiro da esquerda;
  - Senão, o vetor inicial na posição do contador, recebe o valor do novo vetor na posição do ponteiro à direita. Incrementa-se o ponteiro da direita;

<b>Exemplo em vídeo: </b>

[![Exemplo do Merge Sort](https://img.youtube.com/vi/dENca26N6V4/hqdefault.jpg)](https://youtu.be/dENca26N6V4)

### Quick Sort

O Quick Sort é considerado um dos algoritmos com melhor desempenho. Pode ter menor desempenho que o Bubble Sort para vetores pequenos, mas tem um ótimo desempenho para vetores médios ou grande. É baseado na mesma técnica do Merge Sort (divisão para conquista), sendo que a utilizada dessa vez é denominada particionamento, utilizando um pivó (pivot), tal que, os números à esquerda são menores ou iguais ao pivó e os números à direita são maiores. Para melhor desenvolvimento, usa-se recursividade

#### Funcionamento (passo a passo)
- Pegar um vetor (Ou parte dele)
- Verifica se é um vetor de mais de 1 posição
  - Caso seja, já está ordenado
- Defina, arbitrariamente, a primeira posição como um pivô, portanto, a validação será feita da 2ª posição até a última
Marcar a segunda posição com um ponteiro da esquerda e a última com um ponteiro da direita
- Enquanto o ponteiro da esquerda se mantiver à esquerda do ponteiro da direita, validar:
  - Enquanto o ponteiro da esquerda se mantiver à esquerda do ponteiro da direita, validar:
  - Enquanto o valor do ponteiro da esquerda for menor ou igual ao valor do pivô e o ponteiro da esquerda continue à esquerda do ponteiro da direita, a posição da esquerda incrementa 1. Se alguma condição falha, o incremento cessa;
  - Se incremento do ponteiro da esquerda e decremento do ponteiro da direita cessarem, mas o ponteiro da esquerda continuar à esquerda do ponteiro da direita, os valores listados índices primeiro, ponteiro da esquerda e ponteiro da direita, mudam de lugar;
- Quando o ponteiro da direita, passar à esquerda do ponteiro da esquerda, o valor do pivô troca de lugar com o valor cujo índice é o ponteiro da direita
- A partir desse momento, com o pivô em algum lugar entre o início e o fim do vetor inicial, este valor já está na sua posição definitiva restando um (sub)vetor à esquerda e um (sub)vetor à direita.

<b>Exemplo em vídeo: </b>

[![Exemplo do Merge Sort](https://img.youtube.com/vi/3San3uKKHgg/hqdefault.jpg)](https://youtu.be/3San3uKKHgg)