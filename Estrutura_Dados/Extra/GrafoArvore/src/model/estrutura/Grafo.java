package model.estrutura;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Classe que representa um grafo
public class Grafo<T> {
    List<No<T>> nodes;

    public Grafo() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(No<T> node) {
        nodes.add(node);
    }

    public void removeNode(T value) {
        nodes.removeIf(node -> node.value.equals(value));
        for (No<T> node : nodes) {
            node.children.removeIf(child -> child.value.equals(value));
        }
    }

    public Optional<No<T>> findNode(T value) {
        return nodes.stream().filter(node -> node.value.equals(value)).findFirst();
    }
}

