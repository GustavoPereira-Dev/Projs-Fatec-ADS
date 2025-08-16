package model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carro {

	private String placa;
	private String marca;
	private String modelo;
	private int ano;
	private String cor;

}
