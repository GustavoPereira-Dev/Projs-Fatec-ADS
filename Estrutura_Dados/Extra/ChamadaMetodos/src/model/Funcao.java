package model;

import java.util.HashSet;
import java.util.Set;

import model.pilha.Stack;

public class Funcao {
    private String nome;
    private Funcao[] chamadas;
    private boolean recursiva;
    private int parametro;

    public Funcao(String nome, boolean recursiva, int parametro, Funcao... chamadas) {
        this.nome = nome;
        this.chamadas = chamadas;
        this.recursiva = recursiva;
        this.parametro = parametro;
    }

    public String getNome() {
        return nome;
    }

    public Funcao[] getChamadas() {
        return chamadas;
    }

    public void executar(Stack<Funcao> stack, Set<Funcao> historico) throws IllegalArgumentException {
        if (historico.contains(this) && !recursiva) {
            throw new IllegalArgumentException("Loop detectado na função: " + nome);
        }

        stack.push(this);
        historico.add(this);

        String recursao = recursiva ? " com parâmetro " + parametro : "";
        System.out.println("Empilhando Função  " + nome + recursao);

        if (recursiva && parametro > 1) {
            Funcao novaChamada = new Funcao(nome, recursiva, parametro - 1, chamadas);
            novaChamada.executar(stack, historico);
        }

        if (chamadas != null) {
            for (Funcao chamada : chamadas) {
                chamada.executar(stack, historico);
            }
        }

        
        System.out.println("Desempilhando Função " + nome + recursao);
        stack.pop();
        historico.remove(this);
    }

    @Override
    public String toString() {
        return nome;
    }
}
