package model.estrutura;

import java.util.ArrayList;
import java.util.List;

// Classe que representa um nó de um grafo ou árvore
public class No<T> {
    T value;
    public List<No<T>> children;

    public No(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }
}

