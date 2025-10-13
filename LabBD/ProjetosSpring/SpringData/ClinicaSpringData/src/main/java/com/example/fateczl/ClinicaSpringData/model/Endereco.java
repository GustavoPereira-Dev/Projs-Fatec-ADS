package com.example.fateczl.ClinicaSpringData.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {

	@Id
	@Column(name = "cep", length = 8, nullable = false)
	private String cep;
	@Column(name = "rua", length = 100, nullable = false)
    private String rua;
	@Column(name = "numero", nullable = false)
    private String numero;
	@Column(name = "complemento", length = 100, nullable = false)
    private String complemento;
	
}