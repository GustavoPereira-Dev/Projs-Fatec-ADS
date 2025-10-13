package com.example.fateczl.ClinicaSpringData.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fateczl.ClinicaSpringData.model.Medico;

@Repository
public interface IMedicoRepository extends JpaRepository<Medico, Long> {
	
     // c. Query JPQL que retorna a lista de médicos por tipo de especialidade.
	 @Query("SELECT m FROM Medico m WHERE m.especialidade.nome = :nomeEspecialidade")
	 List<Medico> findByEspecialidadeNome(@Param("nomeEspecialidade") String nomeEspecialidade);

}