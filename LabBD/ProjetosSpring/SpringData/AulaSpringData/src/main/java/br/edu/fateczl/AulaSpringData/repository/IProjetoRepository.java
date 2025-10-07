package br.edu.fateczl.AulaSpringData.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.fateczl.AulaSpringData.model.Projeto;

public interface IProjetoRepository extends JpaRepository<Projeto, Integer> {

	@Query("SELECT p FROM Projeto p WHERE horas >= ?1")
	public List<Projeto> findProjetosMaisHoras(int horasInicial); 
	
}
