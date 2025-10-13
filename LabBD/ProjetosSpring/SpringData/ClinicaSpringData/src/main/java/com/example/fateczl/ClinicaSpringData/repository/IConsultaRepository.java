package com.example.fateczl.ClinicaSpringData.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fateczl.ClinicaSpringData.model.Consulta;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta, Long> {

     // d. Query JPQL que retorna a lista de consultas de um determinado dia.
     // A busca é feita em um intervalo de 24 horas a partir da data de início.
    @Query("SELECT c FROM Consulta c WHERE c.dataHora >= :inicioDoDia AND c.dataHora < :fimDoDia")
    List<Consulta> findConsultasNoDia(
            @Param("inicioDoDia") LocalDateTime inicioDoDia,
            @Param("fimDoDia") LocalDateTime fimDoDia
    );

    // e. Query nativa que retorna a quantidade de consultas de um determinado dia.
    @Query(value = "SELECT COUNT(*) FROM consulta WHERE data_hora >= :inicioDoDia AND data_hora < :fimDoDia", nativeQuery = true)
    long countConsultasNoDia(
            @Param("inicioDoDia") LocalDateTime inicioDoDia,
            @Param("fimDoDia") LocalDateTime fimDoDia
    );
    
}