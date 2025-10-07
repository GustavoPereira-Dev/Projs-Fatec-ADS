package br.edu.fateczl.AulaSpringData.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.fateczl.AulaSpringData.model.Empregado;

public interface IEmpregadoRepository extends JpaRepository<Empregado, Integer> {

	public Empregado findByNomeEmpregado(String nomeEmpregado);
	public List<Empregado> findBySalario(BigDecimal salario);
	public Empregado findFirstByDataAdmissao(LocalDate dataAdmissao);
	public List<Empregado> findBySalarioOrderByNomeEmpregadoAsc(BigDecimal salario);
	public List<Empregado> findBySexoOrderByNomeEmpregadoAsc(String sexo);
	
	@Query("SELECT e FROM Empregado e JOIN e.depto d WHERE d.sigla = ?1 ORDER BY e.nomeEmpregado ASC")
	public List<Empregado> findEmpregadoDepto(String siglaDepto);
	
	
}
