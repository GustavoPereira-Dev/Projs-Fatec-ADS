# Roteamento Protocolos

Em um processo de roteamento de protocolos de rede de computadores há uma necessidade de equilibrar a saída de protocolos, de tal forma que não importa a sequência de entrada de protocolos a saída sempre será equilibrada. 

![image](https://github.com/user-attachments/assets/06f3f628-f6db-4dbf-a2d1-cd9039c6a2a8)

Veja que na entrada temos uma sequência qualquer, mas na saída é equilibrado, a não ser que faltem dados, então seu algoritmo tem que testar isso e então repetir, como é o caso do protocolo http na imagem acima. Para resolver este problema primeiro você terá que fazer uma classificação da entrada, então cada protocolo tem um campo STRING chamado TYPE e um campo de dados, que pode ser uma STRING chamado DADOS.

![image](https://github.com/user-attachments/assets/c7b69a62-c9ac-4f48-9723-235114582cac)

Então a solução é criar uma lista circular (dupla) com uma FIFO dentro de cada nó da lista circular (dupla). Toda saída de protocolo da estrutura de dados é realizada do nó que está sendo apontado, então o elemento é retirado de dentro da FIFO e então o ponteiro avança para a próxima posição. Se não tem elemento na FIFO então mantenha-se o nó na lista circular e avance para o próximo nó que possui elemento na FIFO. O PONTEIRO SÓ AVANÇA PARA O PRÓXIMO EM UM ÚNICO SENTIDO. 

Para adicionar é simples, se não existir o TYPE de protocolo de entrada na lista circular, então você deverá inserir um novo nó na lista circular atrás do elemento posicionado, ou seja, do elemento que está sendo referenciado pelo ponteiro acima. Naturalmente criar o nó na lista circular e criar a FIFO. Mas se existe um nó na lista circular referente ao TYPE do protocolo, então você deve localizar este nó na lista circular e adicionar na FIFO interna.
