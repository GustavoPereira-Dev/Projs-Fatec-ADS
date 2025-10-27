package br.edu.fateczl.AgenciaSpringData.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ContaBancaria")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ContaBancaria {

    @Id
    @Column(name = "num_conta")
    private int numConta;

    @Column(name = "nome_cliente")
    private String nomeCliente;

    @Column(name = "saldo")
    private float saldo;

    public void setSaldo(float saldo) throws NumberFormatException {
    	if(saldo < 0) {
    		throw new NumberFormatException("Saldo nao pode ser negativo");
    	}
    	this.saldo = saldo;
    }
}