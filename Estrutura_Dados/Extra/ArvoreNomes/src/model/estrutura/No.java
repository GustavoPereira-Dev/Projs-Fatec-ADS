package model.estrutura;

import java.util.HashMap;
import java.util.Map;

public class No<T extends Comparable<T>> {
    private T valor;
    private No<T> esquerdo;
    private No<T> direito;
    private int altura;

    public No(T valor) {
        this.valor = valor;
        this.altura = 1;
    }

	public T getValor() {
		return valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}

	public No<T> getEsquerdo() {
		return esquerdo;
	}

	public void setEsquerdo(No<T> esquerdo) {
		this.esquerdo = esquerdo;
	}

	public No<T> getDireito() {
		return direito;
	}

	public void setDireito(No<T> direito) {
		this.direito = direito;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

   
}