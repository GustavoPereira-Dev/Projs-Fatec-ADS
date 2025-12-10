# Spring Data

## [Exemplo Empresa](./AulaSpringData/)
Primeiro uso do Spring Data desenvolvido na aula pelo contexto de empresa (Departamento, Projeto, Empregado, Chefe, etc.) somente no testes e uso repositorys da implementação do Hibernate

## [Clínica](./ClinicaSpringData/)


Baseado nas descrições abaixo, fazer a modelagem ER com tipo de domínio, inicializar um
Projeto Java Web pelo Spring Initializr [Spring Initializr](https://start.spring.io) e criar a camada model com todos os objetos e suas anotações para inicializar o banco de dados. Criar a camada Repository de
todas as entidades (Considerando que Categoria é uma entidade com código e tipo) e criar:
a. Método findBy para consultar pacientes por nome, com ordenação ascendente
b. Método findBy para consultar o primerio paciente de um dado telefone
c. Query JPQL que retorne a lista de médicos por tipo de especialidade
d. Query JPQL que retorne a lista de consultas de um determinado dia
e. Query nativa que retorne a quantidade de consultas de um determinado dia


"Pretende-se criar uma base de dados que permita administrar uma parte da informação de uma clínica de saúde. Fundamentalmente a base de dados deve guardar a informação relativa aos doentes que frequentam a clínica (nome, endereço (Rua, número, cep e complemento), telefone e número de beneficiários) e aos médicos que lá trabalham (código, nome, endereço (Rua, número, cep e complemento), contacto e especialidade).

Para além disso o sistema deverá registrar as marcações de consultas de cada paciente para um determinado médico, num dia e hora específicos. No entanto, na clinica um paciente pode obviamente consultar diferentes médicos para a mesma ou para diferentes especialidades. O sistema deverá ainda para cada paciente e por especialidade permitir organizar uma ficha de informações que é atualizada sempre que um médico da especialidade observa esse paciente."

- Considere que um paciente pode consultar a quantidade de dias que faltam para uma
determinada consulta.

- O projeto deve ser carregado no Github e o arquivo com o DER deve estar na subpasta static
dos resources do projeto.

## [Agência](./AgenciaSpringData/)

[Enunciado](../../SQLServer/) (em ProceduresFunctionsTriggers)

Desenvolver nesse contexto, um projeto usando Spring Data