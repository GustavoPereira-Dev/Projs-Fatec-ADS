package com.example.fateczl.ClinicaSpringData.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fateczl.ClinicaSpringData.model.Paciente;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {

    
    // a. Método findBy para consultar pacientes por nome, com ordenação ascendente.
    // Busca pacientes cujo nome contenha o texto fornecido, ignorando maiúsculas/minúsculas.
    List<Paciente> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);
    
    //b. Método findBy para consultar o primeiro paciente de um dado telefone.
    Optional<Paciente> findFirstByTelefone(String telefone);
    
}