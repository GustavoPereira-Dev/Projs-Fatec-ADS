package model.estrutura_dupla;

import model.Pessoa;
import model.estrutura_simples.ListaSimples;

class NoDuplo {
    char letra;
    ListaSimples<Pessoa> listaPessoas;
    NoDuplo proximo;
    NoDuplo anterior;

    public NoDuplo(char letra) {
        this.letra = letra;
        this.listaPessoas = new ListaSimples<>();
    }
    
    
}
