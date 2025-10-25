package br.edu.fateczl.AgenciaSpringData.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.fateczl.AgenciaSpringData.model.ContaBancaria;
import br.edu.fateczl.AgenciaSpringData.model.ContaEspecial;
import br.edu.fateczl.AgenciaSpringData.model.ContaPoupanca;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Integer> {

    /*
     * 1. MÉTODOS DE NEGÓCIO (Chamadas às Stored Procedures)
     * Estes métodos executam a lógica de negócio definida no SQL Server.
     */

    @Procedure(procedureName = "sp_depositar")
    void depositar(@Param("num_conta") int numConta, @Param("valor") float valor);

    @Procedure(procedureName = "sp_sacar")
    void sacar(@Param("num_conta") int numConta, @Param("valor") float valor);

    @Procedure(procedureName = "sp_calcularrendimento")
    void calcularRendimento(@Param("taxa_rendimento") float taxaRendimento);


    /*
     * 2. MÉTODOS DE CRUD (CRUD Básico)
     * O JpaRepository já fornece:
     * - save(ContaBancaria conta): Salva ou atualiza. O JPA sabe se deve inserir/atualizar
     * em 1 ou 2 tabelas (ex: salvar ContaPoupanca mexe em ContaBancaria E ContaPoupanca).
     * - findById(Integer numConta): Busca uma conta por ID. O JPA retorna o tipo correto
     * (ContaPoupanca ou ContaEspecial) automaticamente.
     * - deleteById(Integer numConta): Deleta a conta (graças ao ON DELETE CASCADE do SQL Server,
     * ou o JPA gerencia a remoção em ambas as tabelas).
     * - findAll()
     */


    /*
     * 3. MÉTODOS DE CONSULTA (Queries Específicas)
     */

    // Busca apenas Contas Poupança (O JPA cria o JOIN com a tabela ContaPoupanca)
    @Query("SELECT cp FROM ContaPoupanca cp")
    List<ContaPoupanca> findAllPoupanca();

    // Busca apenas Contas Especiais (O JPA cria o JOIN com a tabela ContaEspecial)
    @Query("SELECT ce FROM ContaEspecial ce")
    List<ContaEspecial> findAllEspecial();

    // Busca uma Conta Poupança específica pelo ID
    @Query("SELECT cp FROM ContaPoupanca cp WHERE cp.numConta = :numConta")
    Optional<ContaPoupanca> findPoupancaById(@Param("numConta") int numConta);

    // Busca uma Conta Especial específica pelo ID
    @Query("SELECT ce FROM ContaEspecial ce WHERE ce.numConta = :numConta")
    Optional<ContaEspecial> findEspecialById(@Param("numConta") int numConta);


    /*
     * 4. CONSULTA COM A UDF (Query Nativa)
     */

    // DTO (Data Transfer Object) para receber o resultado da sua UDF.
    // Pode ser uma classe separada ou uma interface interna.
    /*
     * 4. CONSULTA COM A UDF (Query Nativa)
     */

    // DTO (Data Transfer Object) para receber o resultado da sua UDF.
    // Pode ser uma classe separada ou uma interface interna.
    public interface SaldoDisponivelDTO {
        Integer getNumConta();
        String getNomeCliente();
        Float getSaldoAtual();
        Float getSaldoDisponível(); // O Spring Data mapeia o alias do SQL
    }

    // Query Nativa SQL para chamar a UDF que criamos.
    @Query(value = "SELECT " +
                   "    num_conta AS numConta, " +
                   "    nome_cliente AS nomeCliente, " +
                   "    saldo AS saldoAtual, " +
                   // AQUI é a chamada da função:
                   "    dbo.f_saldodisponivel(num_conta) AS saldoDisponível " + 
                   "FROM ContaBancaria " +
                   "WHERE num_conta = :numConta",
           nativeQuery = true)
    Optional<SaldoDisponivelDTO> getSaldoDisponivelCliente(@Param("numConta") int numConta);

}