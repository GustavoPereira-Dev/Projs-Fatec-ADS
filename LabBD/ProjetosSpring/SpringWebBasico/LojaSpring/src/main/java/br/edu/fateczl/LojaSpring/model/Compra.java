package br.edu.fateczl.LojaSpring.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compra {
	
	private long codigo;
	private long codigoCliente;
	private long codigoProduto;
	private String nomeCliente;
	private String nomeProduto;
	private int quantidade;
	private double valorTotal;
	private LocalDate dataCompra;

}
