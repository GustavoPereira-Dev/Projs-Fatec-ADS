package model.estrutura_dupla;

import model.Pessoa;
import model.estrutura_simples.ListaSimples;

class NoDuplo {
    private char letra;
    private ListaSimples<Pessoa> listaPessoas;
    private NoDuplo proximo;
    private NoDuplo anterior;

    public NoDuplo(char letra) {
        this.letra = letra;
        this.listaPessoas = new ListaSimples<>();
    }

	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}

	public ListaSimples<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(ListaSimples<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

	public NoDuplo getProximo() {
		return proximo;
	}

	void setProximo(NoDuplo proximo) {
		this.proximo = proximo;
	}

	public NoDuplo getAnterior() {
		return anterior;
	}

	void setAnterior(NoDuplo anterior) {
		this.anterior = anterior;
	}

	
    
    
}
