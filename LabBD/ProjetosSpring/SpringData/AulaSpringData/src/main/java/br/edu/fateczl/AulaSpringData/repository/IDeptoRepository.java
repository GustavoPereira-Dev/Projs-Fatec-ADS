package br.edu.fateczl.AulaSpringData.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import br.edu.fateczl.AulaSpringData.model.Depto;

public interface IDeptoRepository extends JpaRepository<Depto, Integer> {

	@Query(value = "SELECT codigo, nome_depto, sigla FROM fn_depto() ORDER BY nome_depto ASC", nativeQuery = true)
	public List<Depto> findDeptosSigla();

	@Query(value = "SELECT codigo, nome_depto, sigla FROM fn_sigla_depto(?1)", nativeQuery = true)
	public Depto findDeptoSigla(int codigo);
	
	@Procedure(name = "Depto.sp_depto_sigla")
	public String sp_depto_sigla(@Param("codigo") int codigo);

}
