package lista3.model;

import lista2.model.Cliente;

public class Musica implements Comparable<Musica> {
    private String nome;
    private String nomeArtista;
    private int duracao;

    public Musica(String nome, String nomeArtista, int duracao) {
        this.nome = nome;
        this.nomeArtista = nomeArtista;
        this.duracao = duracao;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeArtista() {
        return nomeArtista;
    }

    public int getDuracao() {
        return duracao;
    }

    @Override
    public String toString() {
        return "Música: " + nome + " – Artista: " + nomeArtista + " – Duração: " + duracao + "s";
    }
    
    @Override
	public int compareTo(Musica o) {
		// TODO Auto-generated method stub
		return 0;
	}
}

