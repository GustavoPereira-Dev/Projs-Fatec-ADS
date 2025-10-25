package br.edu.fateczl.AgenciaSpringData.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ContaPoupanca")
@PrimaryKeyJoinColumn(name = "num_conta") // <-- Define que num_conta é a PK e FK
public class ContaPoupanca extends ContaBancaria {

    @Column(name = "dia_rendimento")
    private int diaDeRendimento;
    
}