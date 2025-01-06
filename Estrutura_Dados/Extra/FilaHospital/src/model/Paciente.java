package model;

import java.time.LocalTime;

public class Paciente {
    private String nome;
    private int prioridade; // Quanto menor o número, maior a prioridade. Ex: 1 (Emergência), 5 (Não Urgente)
    private boolean preferencial;
    private LocalTime tempoEntrada; // Tempo de chegada do paciente
    private int tempoAtendimento; // Tempo que o paciente demora para ser atendido em minutos

    public Paciente(String nome, int prioridade, boolean preferencial, LocalTime tempoEntrada, int tempoAtendimento) {
        this.nome = nome;
        this.prioridade = prioridade;
        this.preferencial = preferencial;
        this.tempoEntrada = tempoEntrada;
        this.tempoAtendimento = tempoAtendimento;
    }
    
    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public boolean isPreferencial() {
		return preferencial;
	}

	public void setPreferencial(boolean preferencial) {
		this.preferencial = preferencial;
	}

	public LocalTime getTempoEntrada() {
		return tempoEntrada;
	}

	public void setTempoEntrada(LocalTime tempoEntrada) {
		this.tempoEntrada = tempoEntrada;
	}

	public int getTempoAtendimento() {
		return tempoAtendimento;
	}

	public void setTempoAtendimento(int tempoAtendimento) {
		this.tempoAtendimento = tempoAtendimento;
	}

	@Override
    public String toString() {
        return nome + " - Prioridade: " + prioridade + " - Preferencial: " + preferencial + " - Tempo de Entrada: " + tempoEntrada;
    }
}


