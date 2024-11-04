package model.estrutura.arvore;

import model.estrutura.lista.ListaEncadeadaSimples;

public class No<T> {
    private T valor;
    private No<T> pai;
    private ListaEncadeadaSimples<No<T>> filhos;
    private ListaEncadeadaSimples<String> funcionarios;

    public No(T valor) {
        this.valor = valor;
        this.filhos = new ListaEncadeadaSimples<>();
        this.funcionarios = new ListaEncadeadaSimples<>();
    }

    public T getValor() {
        return valor;
    }

    public No<T> getPai() {
        return pai;
    }

    public void setPai(No<T> pai) {
        this.pai = pai;
    }

    public ListaEncadeadaSimples<No<T>> getFilhos() {
        return filhos;
    }

    public ListaEncadeadaSimples<String> getFuncionarios() {
        return funcionarios;
    }

    public void addFilho(No<T> filho) {
        filho.setPai(this);
        filhos.append(filho);
    }

    public void addFuncionario(String funcionario) {
        funcionarios.append(funcionario);
    }

    public void removeFuncionario(String funcionario) {
        funcionarios.remove(funcionarios.index(funcionario));
    }
}
