package br.edu.fateczl.AulaSpringData.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fateczl.AulaSpringData.model.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {

}
