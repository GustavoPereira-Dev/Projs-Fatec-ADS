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
@Table(name = "ContaEspecial")
@PrimaryKeyJoinColumn(name = "num_conta")
public class ContaEspecial extends ContaBancaria {

    @Column(name = "limite")
    private float limite;

}