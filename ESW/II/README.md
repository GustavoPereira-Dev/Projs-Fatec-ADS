# Engenharia de Software II

## [MarSol](./MarSol/)

A empresa de Rádio Táxi Mar & Sol precisa de uma aplicação que para cada cliente são cadastrados os seguintes dados: 
  - código (que deve ser gerado pelo sistema)
  - nome
  - endereço completo (logradouro, número, complemento, bairro, município, estado) e 
  - dois telefones de contato. 

O cliente pode se cadastrar apenas com o nome para agilizar o processo. Quando fizer sua primeira chamada por telefone, seus dados serão atualizados. 
  - Para o cooperado (taxista) cadastram-se: 
    - nome 
    - CPF 
    - número da carteira de motorista 
    - categoria 
    - data de validade da carteira 
    - número do táxi na cooperativa (conhecido como número de VR) 
    - número da placa 
    - modelo do veiculo 
    - fabricante 
    - cor do veículo 
    - endereço residencial completo 
    - telefone residencial e celular 
    - data de entrada na Cooperativa. 
Quando o cooperado se desliga, deve ser cadastrada a data de desligamento. Quando o cliente solicitar uma corrida programada (pedidos com antecedência maior do que meia hora), 
  - Cadastra-se no controle de corridas: 
    - o endereço de saída do carro
    - o bairro de destino
    - a data e hora de saída
    - telefone de contato (se local de saída diferente do cadastro). 

Se o cliente não for cadastrado, seu cadastro deve ser feito no momento da solicitação do carro. O status dessa corrida deve ser definido como: "aguardando VR". Uma hora antes da corrida programada, a operadora questiona, pelo rádio, aos cooperados que estejam em trânsito, qual deseja pegar a corrida programada. Deve ser cadastrado na aplicação o número da VR do taxista que se candidatou à corrida. Meia hora antes do horário, o cliente deve ser avisado a respeito do número da VR. Antes de avisar ao cliente, o status deve ser assinalado como: "aguardando aviso". Após o aviso, o status muda para "aviso efetuado". Após ser atendido, o status deve ser alterado para: "tripulado". Em qualquer momento a corrida pode ser cancelada pelo passageiro. Se for uma solicitação de carro imediato, a operadora deve retomar a tela, informando o status dentre as opções: "aguardando aviso", "aviso efetuado", "cancelado pelo passageiro" ou "cancelado pela cooperativa por falta de carro". Se um logradouro não estiver na lista, a solicitação não será atendida. Quando o cliente for atendido, o status deve ser alterado para: "tripulado".

## Clube de Campo

Miguel é gerente de um clube de campo, e para que fosse mais fácil gerenciar as atividades relacionadas aos associados, te contratou para desenvolver um software para melhorar as suas atividades. O clube possui três quadras poliesportivas, duas quadras de futebol de salão, uma sala de jogos, dois salões de festas, 3 piscinas infantil e adulto com tobogã, parque infantil, sala de recreação com monitor (para crianças entre 2 e 7 anos), além de haras, campo de golfe, área para realização de trilhas e quinze churrasqueiras. Há três tipos de associados: Standard, Gold e Black. Para realizar o cadastro dos associados, é necessário: Nome, RG, CPF, endereço, CEP, bairro, cidade, estado, telefone residencial, telefone comercial, celular e data de cadastro. O associado pode cadastrar dependentes, onde para tais, é exigido na hora do cadastro, nome completo e RG. Para a utilização do clube, os associados devem realizar reservas das dependências. Os pagamentos são realizados todos os meses e as cobranças devem ser geradas e administradas pelo sistema. Em caso de atraso nos pagamentos, o valor da mensalidade será acrescido de 5%. Caso a inadimplência se acumule por dois meses, o associado perde o direito de realizar reservas para o haras, campo de golfe e piscina. Quando a inadimplência ultrapassa os três meses, o associado terá acesso apenas as quadras, e ultrapassados quatro meses, a carteirinha é bloqueada. Quando pagamentos são realizados, são registrados o valor, a forma de pagamento, e a data. Miguel necessita também ter controle sobre as reservas das áreas do clube, sendo necessário cadastrar no sistema cada área e a quantidade de itens da mesma disponíveis . Áreas que são reserváveis: churrasqueira e salão de festas. Os passeios no haras, são agendados 10 turmas, com no máximo 20 pessoa. As trilhas duram 1 hora e meia cada, com turmas no máximo de 30 pessoas, agendadas 7 turmas por dia.”

## Sistema de Processo Seletivo dos Professores

A finalidade deste documento é coletar, analisar e definir as necessidades e os recursos de alto nível do Sistema de Processo Seletivo dos Professores. O foco está nos recursos necessários para os envolvidos e os usuários-alvo, além das razões que justificam essas necessidades. Os detalhes sobre como o Sistema de Processo Seletivo dos Professores atenderá a essas necessidades serão apresentadas nos casos de uso e nas especificações suplementares e outros que serão mostrados ao decorrer da documentação. 

Atualmente, o processo de seleção de professores é conduzido de forma manual, o que resulta em desafios como a desorganização de informações, falta de padronização no registro de dados e dificuldades na comunicação entre os envolvidos no processo. A ausência de uma ferramenta centralizada e integrada para gerenciar e automatizar esse processo faz com que a administração perca tempo com tarefas repetitivas e suscetíveis a erros humanos, como o controle de inscrições, acompanhamento de documentos, agendamento de entrevistas e análise de candidatos. 

Além disso, o fluxo de informações entre os coordenadores e a equipe administrativa é ineficiente, além de poder ocorrer problemas e atrasos devido a alterações de documentos dos candidatos e retrabalhos das análises, o que pode levar a atrasos na tomada de decisões e na contratação dos professores. Por isso, surge a necessidade de desenvolver um sistema que facilite e automatize o processo seletivo dos professores, proporcionando maior eficiência, transparência e controle em cada etapa do processo. 

### Ambiente do Usuário

- ATA 
  - 1 pessoa envolvida 
  - A duração de um ciclo de tarefas é razoavelmente longa, pois é a análise de diversos candidatos que por sua contém outros passos que a Assistente dialoga e troca documentos com outros Coordenadores. Pode ter mudado um pouco desde que o entrevistado x entrou, mas ainda é muita coisa para uma só pessoa  
  - Outras informações
    - Faz a gestão dos processos de editais de ampliação de carga horária
    - Faz a gestão dos estágios dos alunos
    - Assegura o processo seletivo simplificado
    - Dispara o edital que os Coordenadores solicitam  
    - Os professores e/ou candidatos enviam para ela a documentação e ela analisa
   - Após o prazo do documento e análise, ela repassa a documentação para os Coordenadores
   - Visualiza os documentos iniciais do candidato pelo site de gestão e analisa eles


- Candidato 
  - Várias pessoas a procura dos mais variados e/ou disciplina envolvido para ministrar aulas (contudo vamos pensar melhor individualmente) 
  - A duração de um ciclo de tarefas é um tempo médio, contudo existe um intervalo de um ciclo de tarefas para o outro devido o tempo de análise dos envolvidos no processo de análise (Coordenador e ATA). Pode ter mudado a quantidade de “papelada” que os candidatos devem mandar aos responsáveis da análise 
  - Outras informações
    - Escolhe, pelo seu conhecimento a área que deseja ser professor e o seu tipo de processo seletivo (Concurso público ou processo seletivo simplificado)
    - Se inscreve no tipo de processo seletivo e manda ao sistema/responsável os documentos comprobatórios para a sua inscrição no processo (caso seja por um oferecimento interno, a maior parte dos outros itens não costumam ser muito considerados)
    - Aguarda o resultado da análise feita pelo Coordenador do curso e a ATA, e parte para o próximo passo (caso tenha passado da primeira fase)
    - Faz uma prova teórica com outros candidatos do mesmo curso ou da mesma disciplina e aguarda o resultado
    - Faz uma aula experimental sobre a sua disciplina para a banca que deverá avaliá-la junto com a de outros candidatos
    - Pelo site de gestão, o Diário oficial (em concurso público) e/ou os editais-de-ampliação, verifica a unidade, disciplina que deseja se inscrever para o processo seletivo e por lá mesmo inicia seu processo dando o upload de seus documentos até quando chegar no ponto que utiliza somente o e-mail e/ou participa presencialmente de algo



- Professor da Banca 
  - Várias pessoas envolvidas em só uma banca (geralmente são 2 professores e um Coordenador do curso em específico) 
  - Duração curta, basicamente só participam da avaliação da apresentação e forma de ministrar de vários candidatos e os avaliam. Por ser completamente presencial a banca não houve nenhuma mudança  
  - Outras informações
    -	Ministra aulas para alunos do curso de Análise e Desenvolvimento de Sistemas
    -	Assegura o aprendizado dos alunos em matérias especificas
    -	Participa da banca de análises dos candidatos
    - Pode ser recomendado de outros processos da Faculdade (o chamado “Oferecimento Interno”) de uma disciplina específica ou de sua própria em outras unidades
    - Se for um concursado da disciplina x em outra unidade, pode se inscrever e entrar direto pela mesma com poucas avaliações

- Coordenador 
  - Várias pessoas envolvidas (mas somente no geral 1 interage com o respectivo curso/disciplina que o Candidato escolheu) 
  - A duração de um ciclo de tarefas é menor que o da ATA por cuidar somente do seu curso de coordenação, mas ainda pode ocorrer imprevistos no decorrer da análise dos documentos de uma forma mais técnica dos candidatos. Pode ter ocorrido mudança, mas foram poucas e bem sutis 
  - Outras informações
    - Avisa que a disciplina x está livre e desenvolve inicialmente um Oferecimento interno (que somente professores internos à CPS podem participar)
    - Caso passe um tempo e o Oferecimento interno não receba nenhum retorno significativo dos professores, é feito assim um externo (o chamado PSS, Processo Seletivo Simplificado) para professore ou candidatos externo à CPS e Fatec
    - Nos oferecimentos, faz a parte de análise do material enviado. 
    - Faz a triagem, seleção e verificação se está tudo certo na documentação após a ATA mandar os documentos
    - É repassado para ATA a lista de pontuação dos candidatos final em cima das contagens feita
    - Se o candidato é interno ou fez um concurso público (com conteúdo que possam ser diferentes da disciplina administrada na unidade), analisa se o candidato realmente é capaz de lesionar da forma que ele aceita
    - Repassa ao site de gestão as disciplinas do curso ao qual coordena que necessitam de professor além de receber quem estar 

- Diretor 
  - Uma pessoa envolvida 
  - A duração de um ciclo de tarefas do diretor é um dos maiores em relação aos outros usuários, por se focar exclusivamente em fiscalizar os processos seletivos de professores que estão ocorrendo na unidade (assim 1 ciclo equivale a quase todo o processo seletivo de uma vaga) 
  - Outras informações
    -	Faz parte da direção acadêmica da unidade
    -	Concentra o poder Executivo da Faculdade (ações de longo prazo)
    -	Fiscaliza os processos seletivos (PSS e especialmente Concurso Público) referente a seleção de novos docentes/professores
    -	Tem responsabilidade sobre as ações dos Coordenadores e de outros autores pelo seu cargo elevado e representação da relação entre a organização e o ambiente da unidade
    -	Raramente interage diretamente na seleção dos professores, tendo casos e casos para que ele possa agir (caso ele também deseje fazer isso)


