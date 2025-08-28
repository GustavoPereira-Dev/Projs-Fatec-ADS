# Revisão Banco de Dados

- Faça o exercício de Revisão SQL com base na estrutura das tabelas e consultas pedidas abaixo:

## Alunos (CHECK INT > 0)

| RA    | Nome                                 | Idade |
|-------|--------------------------------------|-------|
| 3416  | DIEGO PIOVESAN DE RAMOS              | 18    |
| 3423  | LEONARDO MAGALHÃES DA ROSA           | 17    |
| 3434  | LUIZA CRISTINA DE LIMA MARTINELI     | 20    |
| 3440  | IVO ANDRÉ FIGUEIRA DA SILVA          | 25    |
| 3443  | BRUNA LUISA SIMIONI                  | 37    |
| 3448  | THAÍS NICOLINI DE MELLO              | 17    |
| 3457  | LÚCIO DANIEL TÂMARA ALVES            | 29    |
| 3459  | LEONARDO RODRIGUES                   | 25    |
| 3465  | ÉDERSON RAFAEL VIEIRA                | 19    |
| 3466  | DAIANA ZANROSSO DE OLIVEIRA          | 21    |
| 3467  | DANIELA MAURER                       | 23    |
| 3470  | ALEX SALVADORI PALUDO                | 42    |
| 3471  | VINÍCIUS SCHVARTZ                    | 19    |
| 3472  | MARIANA CHIES ZAMPIERI               | 18    |
| 3482  | EDUARDO CAINAN GAVSKI                | 19    |
| 3483  | REDNALDO ORTIZ DONEDA                | 20    |
| 3499  | MAYELEN ZAMPIERON                    | 22    |

## Disciplinas (CHECK INT ≥ 32)

| Código | Nome                                     | Carga Horária |
|--------|------------------------------------------|---------------|
| 1      | Laboratório de Banco de Dados            | 80            |
| 2      | Laboratório de Engenharia de Software    | 80            |
| 3      | Programação Linear e Aplicações          | 80            |
| 4      | Redes de Computadores                    | 80            |
| 5      | Segurança da informação                  | 40            |
| 6      | Teste de Software                        | 80            |
| 7      | Custos e Tarifas Logísticas              | 80            |
| 8      | Gestão de Estoques                       | 40            |
| 9      | Fundamentos de Marketing                 | 40            |
| 10     | Métodos Quantitativos de Gestão          | 80            |
| 11     | Gestão do Tráfego Urbano                 | 80            |
| 12     | Sistemas de Movimentação e Transporte    | 40            |

## Professores

| Registro | Nome       | Titulação |
|----------|------------|-----------|
| 1111     | Leandro    | 2         |
| 1112     | Antonio    | 2         |
| 1113     | Alexandre  | 3         |
| 1114     | Wellington | 2         |
| 1115     | Luciano    | 1         |
| 1116     | Edson      | 2         |
| 1117     | Ana        | 2         |
| 1118     | Alfredo    | 1         |
| 1119     | Celio      | 2         |
| 1120     | Dewar      | 3         |
| 1121     | Julio      | 1         |

## Cursos

| Código | Nome      | Área                    |
|--------|-----------|-------------------------|
| 1      | ADS       | Ciências da Computação  |
| 2      | Logística | Engenharia Civil        |

## Titulação

| Código | Título        |
|--------|---------------|
| 1      | Especialista  |
| 2      | Mestre        |
| 3      | Doutor        |

## Relação Aluno/Disciplina

| RA do Aluno | Código da Disciplina |
|-------------|----------------------|
| 3416        | 1                    |
| 3416        | 4                    |
| 3423        | 1                    |
| 3423        | 2                    |
| 3423        | 5                    |
| 3423        | 6                    |
| 3434        | 2                    |
| 3434        | 5                    |
| 3434        | 6                    |
| 3440        | 1                    |
| 3443        | 5                    |
| 3443        | 6                    |
| 3448        | 4                    |
| 3448        | 5                    |
| 3448        | 6                    |
| 3457        | 2                    |
| 3457        | 4                    |
| 3457        | 5                    |
| 3457        | 6                    |
| 3459        | 1                    |
| 3459        | 6                    |
| 3465        | 7                    |
| 3465        | 11                   |
| 3466        | 8                    |
| 3466        | 11                   |
| 3467        | 8                    |
| 3467        | 12                   |
| 3470        | 8                    |
| 3470        | 9                    |
| 3470        | 11                   |
| 3470        | 12                   |
| 3471        | 7                    |
| 3472        | 7                    |
| 3472        | 12                   |
| 3482        | 9                    |
| 3482        | 11                   |
| 3483        | 8                    |
| 3483        | 11                   |
| 3483        | 12                   |
| 3499        | 8                    |

## Relação Professor/Disciplina

| Código da Disciplina | Registro do Professor |
|----------------------|------------------------|
| 1                    | 1111                   |
| 2                    | 1112                   |
| 3                    | 1113                   |
| 4                    | 1114                   |
| 5                    | 1115                   |
| 6                    | 1116                   |
| 7                    | 1117                   |
| 8                    | 1118                   |
| 9                    | 1117                   |
| 10                   | 1119                   |
| 11                   | 1120                   |
| 12                   | 1121                   |

## Relação Disciplina/Curso

| Código da Disciplina | Código do Curso |
|----------------------|-----------------|
| 1                    | 1               |
| 2                    | 1               |
| 3                    | 1               |
| 4                    | 1               |
| 5                    | 1               |
| 6                    | 1               |
| 7                    | 2               |
| 8                    | 2               |
| 9                    | 2               |
| 10                   | 2               |
| 11                   | 2               |
| 12                   | 2               |

1. Fazer uma pesquisa que permita gerar as listas de chamadas, com RA e nome por disciplina													
2. Fazer uma pesquisa que liste o nome das disciplinas e o nome dos professores que as ministram													
3. Fazer uma pesquisa que , dado o nome de uma disciplina, retorne o nome do curso													
4. Fazer uma pesquisa que , dado o nome de uma disciplina, retorne sua área													
5. Fazer uma pesquisa que , dado o nome de uma disciplina, retorne o título do professor que a ministra													
6. Fazer uma pesquisa que retorne o nome da disciplina e quantos alunos estão matriculados em cada uma delas													
7. Fazer uma pesquisa que, dado o nome de uma disciplina, retorne o nome do professor.  Só deve retornar de disciplinas que tenham, no mínimo, 5 alunos matriculados													
8. Fazer uma pesquisa que retorne o nome do curso e a quatidade de professores cadastrados que ministram aula nele. A coluna de ve se chamar quantidade													
