# Laboratório de Engenharia de Software

## [Autor e Filme utilizando Hibernate](./aulaHibernateAtorFilme/)

Aula inicial do uso prático do Hibernate, um framework de ORM (Object-Relational Mapping) com o objetivo de facilitar a criação e o mapameamento de entidades, relacionamentos e conexões entre a aplicação (Java BackEnd) com o Banco de Dados (que nesse caso foi o MySQL mas poderia ser outros SQL ou NoSQL)

## [AcessoAPI](./AcessoAPI/)

Exemplo da aula de acesso de uso de uma API do OMDb (Open Movie Database)

## [Sistema Logística (FrontEnd)](https://github.com/GustavoPereira-Dev/SistemaLogistica)

- Altere a entidade caminhão para ter o tamanho em Comprimento,  Largura,  Altura e Fator de Cubagem. Adicione um atributo derivado para a metragem cúbica com o fator de cubagem, Fator de cubagem para transporte rodoviário: 300 kg/m³.
- A empresa trabalhará com caixas de papelão padronizadas, que tem Comprimento,  Largura,  Altura e limite de peso.
- A transportadora cobrará por peso ou por caixa, dado que há produtos leves e pesados.
Deve-se calcular o peso cubado, para saber como será cobrado o transporte. 
- A fórmula completa é: Peso Cubado = Volume (m³) x Fator de Cubagem. 

  1. Compare o peso cubado com o peso real: O valor que determinará o custo do frete será sempre o maior entre o peso real da carga e o peso cubado.
  - Por que calcular a cubagem é importante?
   - Otimização de custos: As transportadoras usam o peso cubado para cobrar o frete, pois ele reflete o espaço que a carga ocupa no veículo. 
   - Eficiência logística: O cálculo ajuda a planejar o transporte e a usar o espaço do veículo de forma mais eficiente, evitando o transporte de cargas volumosas com baixo peso ou cargas pesadas que ocupam pouco espaço. 
   - Conformidade: Em alguns casos, o não cumprimento das regras de cubagem pode resultar em multas e apreensão da carga, além de aumentar o custo com combustível e manutenção dos veículos. 
- O cliente solicitará um transporte de um produto, que deverá ser medido e pesado. Crie uma solicitação de transporte, onde o cliente deve especificar o produto que será transportado. Ofereça as opções de caixas para ele. - O produto deverá caber em uma das caixas oferecidas pela empresa. 
Calcule o frete pelo produto, ou por peso ou por caixa. Não se esqueça que a empresa tem um valor a ser cobrado por quilômetros rodado. 
- Utilize uma API para o cálculo da distância como:
  - Google Maps;
  - Haversine;
  - Bing Maps;
  - OpenRouteService.
- Um fator importante no cálculo do frete é o pedágio, pois ele encarecerá o frete. Utilize APIs que conseguem calcular os pedágio, como:
  - Google Routes
  - HERE Technologies
  - Mapplus
  - OpenRouteService

<b>  Integrantes: @GustavoPereira-Dev, [@Joaoftito](https://github.com/Joaoftito), [@NicolasDomingos09](https://github.com/LiaWyllde) e [@LiaWyllde](https://github.com/LiaWyllde) </b>


## [Controle Logístico (API)](https://github.com/NicolasDomingos09/ControleLogistico)
- Sua aplicação deve oferecer APIs para um sistema logístico, com controle da frota, controlando os caminhões, os percursos executados, a quilometragem de saída e da chegada, o total de combustível. Cada caminhão passa por manutenção a cada 10.000 Km, com troca de óleos, filtros e pastilhas. Os pneus são trocados a cada 70.000 Km. O sistema deve controlar toda a manutenção de cada caminhão. 
- As entregas são programadas diariamente, dado que os clientes solicitam pelo site o envio do produto, que deve ser descrito e pesado. Cada produto deve ir em caixas padronizadas pela empresa. Caso o produto não caiba em uma caixa, a solicitação não será atendida. O cliente cadastra a solicitação, indicando a origem e o destino, informando qual é o horário que a mercadoria deve ser retirada. O pagamento deve ser realizado no próprio site. O cliente consegue ver o processo de entrega acessando o site. Utilize pelo menos quatro etapas para a entrega, como coleta, em processamento, a caminho da entrega e entregue. 
- A empresa deve tentar lotar um caminhão para realizar a entrega. O sistema deve apresentar os produtos e os destinos, bem como oferecer um caminhão que ocupe o máximo de espaço interno.
- O motorista deve ter um app que envia as coordenadas dele para o sistema, que envia uma msg pelo WhatsApp do destinatário que o produto está chegando. Após a entrega o motorista deve sinalizar no app que a entrega foi realizada. O cliente pode dar um feedback sobre cada solicitação de entrega. O recebedor pode pontuar a entrega que ele recebeu. 
- Conteúdo extra: fazer um sistema de autenticação/acesso da API utilizando métodos como Redis (o que foi escolhido pelo grupo) ou JWT (JSON Web Tokens)

<b> Integrantes: @GustavoPereira-Dev, [@Joaoftito](https://github.com/Joaoftito), [@NicolasDomingos09](https://github.com/LiaWyllde), [@LiaWyllde](https://github.com/LiaWyllde) , [@LucaspeOliveira](https://github.com/LucaspeOliveira), [@flwbiel](https://github.com/flwbiel), [@julioferrazz](https://github.com/julioferrazz), [@PauIo-Soares](https://github.com/PauIo-Soares) </b>