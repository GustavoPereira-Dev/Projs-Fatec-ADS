package model.estrutura.arvorebinaria;

public class NoBinario<T extends Comparable<T>> {
    private T valor;
    private NoBinario<T> esquerdo;
    private NoBinario<T> direito;

    public NoBinario(T valor) {
        this.valor = valor;
        this.esquerdo = null;
        this.direito = null;
    }

    public T getValor() {
        return valor;
    }

    public NoBinario<T> getEsquerdo() {
        return esquerdo;
    }

    public void setEsquerdo(NoBinario<T> esquerdo) {
        this.esquerdo = esquerdo;
    }

    public NoBinario<T> getDireito() {
        return direito;
    }

    public void setDireito(NoBinario<T> direito) {
        this.direito = direito;
    }
}
