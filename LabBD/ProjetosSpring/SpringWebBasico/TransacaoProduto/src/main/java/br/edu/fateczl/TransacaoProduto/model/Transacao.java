package br.edu.fateczl.TransacaoProduto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {
	
	private long codigo;
	private long codigoProduto;
	private int quantidade;
	private float valorTotal;

}
