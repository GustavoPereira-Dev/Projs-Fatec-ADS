package model.estrutura_simples;

class NoSimples<T> {
    T valor;
    NoSimples<T> proximo;

    public NoSimples(T valor) {
        this.valor = valor;
    }

}