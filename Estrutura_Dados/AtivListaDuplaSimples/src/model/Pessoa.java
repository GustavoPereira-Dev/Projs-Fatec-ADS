// Classe que representa uma pessoa
package model;
public class Pessoa {
    String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}