package com.example.fateczl.ClinicaSpringData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fateczl.ClinicaSpringData.model.FichaInformacoes;

@Repository
public interface IFichaInformacoesRepository extends JpaRepository<FichaInformacoes, Long> {
	
}