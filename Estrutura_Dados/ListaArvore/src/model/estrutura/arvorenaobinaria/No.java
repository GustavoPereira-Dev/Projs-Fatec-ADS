package model.estrutura.arvorenaobinaria;
import model.estrutura.lista.ListaEncadeadaSimples;

public class No<T> {
    private T valor;
    private ListaEncadeadaSimples<No<T>> filhos;

    public No(T valor) {
        this.valor = valor;
        this.filhos = new ListaEncadeadaSimples<>();
    }

    public T getValor() {
        return valor;
    }

    public ListaEncadeadaSimples<No<T>> getFilhos() {
        return filhos;
    }

    public void addFilho(No<T> filho) {
        this.filhos.append(filho);
    }

    public No<T> getFilho(T valor) {
        for (int i = 0; i < filhos.total(); i++) {
            No<T> filho = filhos.get(i).getValor();
            if (filho.getValor().equals(valor)) {
                return filho;
            }
        }
        return null;
    }
}
