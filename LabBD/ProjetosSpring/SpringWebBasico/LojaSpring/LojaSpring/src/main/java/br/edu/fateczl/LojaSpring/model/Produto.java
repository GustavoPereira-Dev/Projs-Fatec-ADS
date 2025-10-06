package br.edu.fateczl.LojaSpring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
	
	private long codigo;
	private String nome;
	private double valor;

}
