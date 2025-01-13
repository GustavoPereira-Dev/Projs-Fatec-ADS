package model.estrutura.arvorebinaria;

public class ArvoreBinaria<T extends Comparable<T>> {
    private NoBinario<T> raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void adicionar(T valor) {
        raiz = adicionarRecursivo(raiz, valor);
    }

    private NoBinario<T> adicionarRecursivo(NoBinario<T> atual, T valor) {
        if (atual == null) {
            return new NoBinario<>(valor);
        }

        if (valor.compareTo(atual.getValor()) < 0) {
            atual.setEsquerdo(adicionarRecursivo(atual.getEsquerdo(), valor));
        } else if (valor.compareTo(atual.getValor()) > 0) {
            atual.setDireito(adicionarRecursivo(atual.getDireito(), valor));
        }

        return atual;
    }

    public void imprimirEmOrdem() {
        imprimirEmOrdemRecursivo(raiz);
    }

    private void imprimirEmOrdemRecursivo(NoBinario<T> no) {
        if (no != null) {
            imprimirEmOrdemRecursivo(no.getEsquerdo());
            System.out.print(no.getValor() + " ");
            imprimirEmOrdemRecursivo(no.getDireito());
        }
    }
}
