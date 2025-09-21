package br.edu.fateczl.TransacaoProduto.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
	
	private long codigo;
	private String nome;
	private float valor;

}
