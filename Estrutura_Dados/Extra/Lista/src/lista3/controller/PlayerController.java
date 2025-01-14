package lista3.controller;

import lista3.model.Musica;
import lista3.model.lista.circular.ListaCircular;
import lista3.model.lista.circular.No;

// Exercicio 5
public class PlayerController {

    public void adicionaMusica(ListaCircular<Musica> lista, String musica) {
        String[] dados = musica.split(";");
        Musica novaMusica = new Musica(dados[0], dados[1], Integer.parseInt(dados[2]));
        if (lista.total == 0) {
            lista.append(novaMusica);
        } else {
            lista.append(novaMusica); 
        }
    }

    public void removeMusica(ListaCircular<Musica> lista, int posicao) throws IllegalArgumentException {
        if (lista.total == 0) {
            throw new IllegalArgumentException("A lista está vazia.");
        }
        lista.remove(posicao);
    }

    public void executaPlaylist(ListaCircular<Musica> lista) throws IllegalArgumentException {
        if (lista.total == 0) {
            throw new IllegalArgumentException("A lista está vazia.");
        }
        No<Musica> atual = lista.get(0);
        for (int i = 0; i < lista.total; i++) {
            Musica musica = atual.getValor();
            System.out.println(musica.toString());
            try {
                Thread.sleep(musica.getDuracao() * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            atual = atual.getProximo();
        }
    }
}
