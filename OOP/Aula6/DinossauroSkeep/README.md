# Dinossauro Skeep

- O Skeep é um dinossauro e será o personagem do nosso jogo, ele possui as seguintes características e comportamentos:

  - Características
    - energia
    - velocidade
    - temperatura
    - humor
  - Comportamentos 
    - pular()
    - correr()
    - comer()
    - cantar()
    - tomarSol()
    - ficarNaSombra()
  - Regras:
    - O dinossauro precisa: 
    - Tomar Sol para aumentar a velocidade, a temperatura, além de deixa-lo com o humor feliz
    - Comer para aumentar a energia, diminui a velocidade, e deixar o humor feliz
    - Correr gasta energia e velocidade, mas deixa o humor feliz
    - ficarNaSombra recupera energia deixa o humor triste e diminui a temperatura
    - cantar gasta energia e deixa o humor feliz
    - pular gasta energia e velocidade e deixa o humor feliz


  - Faça uma classe chamada Dinossauro, contendo as características e comportamentos do Skeep.
  - Crie uma classe de Teste com o método main, para executar as atividades abaixo:
  - Crie uma instância do classe Dinossauro chamada de skeep
  - Crie uma instância da classe Scanner chamada de scan
  - Em um loop inifinito mostre na tela as características do objeto skeep
  - Mostre um menu de opções para o usuário com as seguintes opções:
    - (P)ular           
    - (C)orrer               
    - Co(M)er
    - C(A)ntar        
    - Tomar (S)ol          
    - Ficar na S(O)mbra
  - Pegue a primeira letra do que o usuário digitar como sendo a opção escolhida
    - String textoMaiusculo = scan.nextLine().toUpperCase();
    - char letra = textoMaiusculo.charAt(0);
    - Conforme a opção escolhida execute o método correspondente no objeto skeep
