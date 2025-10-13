package com.example.fateczl.ClinicaSpringData.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "endereco_cep", nullable = false)
    private Endereco endereco;

    @Column(name = "telefone", length = 11, nullable = false)
    private String telefone;
    
    @Column(name = "numero_beneficiario", nullable = false)
    private String numeroBeneficiario;
    
}