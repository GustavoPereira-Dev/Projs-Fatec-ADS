package model.estrutura;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class GrafoUtils {

    // Método para converter um grafo em uma árvore não binária
    public static <T> No<T> graphToTree(Grafo<T> graph, T rootValue) {
        No<T> root = new No<>(rootValue);
        Set<T> visitados = new HashSet<>();
        convertToTree(root, graph, visitados);
        return root;
    }

    private static <T> void convertToTree(No<T> root, Grafo<T> graph, Set<T> visitados) {
        visitados.add(root.value);
        for (No<T> node : graph.nodes) {
            if (!node.value.equals(root.value) && !visitados.contains(node.value)) {
                root.children.add(node);
                convertToTree(node, graph, visitados);
            }
        }
    }

    // Método para converter uma árvore não binária em um grafo
    public static <T> Grafo<T> treeToGraph(No<T> root) {
        Grafo<T> graph = new Grafo<>();
        Set<T> visitados = new HashSet<>();
        convertToGraph(root, graph, visitados);
        return graph;
    }

    private static <T> void convertToGraph(No<T> node, Grafo<T> graph, Set<T> visitados) {
        if (!visitados.contains(node.value)) {
            visitados.add(node.value);
            graph.addNode(node);
            for (No<T> child : node.children) {
                convertToGraph(child, graph, visitados);
            }
        }
    }

    // Método para adicionar um nó à árvore
    public static <T> void addNodeToTree(No<T> root, T parentValue, T value) {
        Optional<No<T>> parentNode = findNode(root, parentValue);
        parentNode.ifPresent(node -> node.children.add(new No<>(value)));
    }

    private static <T> Optional<No<T>> findNode(No<T> root, T value) {
        if (root.value.equals(value)) {
            return Optional.of(root);
        }
        for (No<T> child : root.children) {
            Optional<No<T>> found = findNode(child, value);
            if (found.isPresent()) {
                return found;
            }
        }
        return Optional.empty();
    }

    public static <T> void removeNodeFromTree(No<T> root, T value) {
        removeNodeFromTreeHelper(root, null, value);
    }

    private static <T> void removeNodeFromTreeHelper(No<T> current, No<T> parent, T value) {
        if (current == null) {
            return;
        }

        // Usamos um iterador para evitar a modificação concorrente
        for (Iterator<No<T>> iterator = current.children.iterator(); iterator.hasNext(); ) {
            No<T> child = iterator.next();
            if (child.value.equals(value)) {
                iterator.remove();
                return;
            } else {
                removeNodeFromTreeHelper(child, current, value);
            }
        }

        if (current.value.equals(value)) {
            if (parent != null) {
                parent.children.remove(current);
            } else {
                current.value = null;
                current.children.clear();
            }
        }
    }


    // Método para imprimir a estrutura do grafo
    public static <T> void printGraph(Grafo<T> graph) {
        for (No<T> node : graph.nodes) {
            System.out.print(node.value + " -> ");
            for (No<T> child : node.children) {
                System.out.print(child.value + " ");
            }
            System.out.println();
        }
    }

    // Método para imprimir a estrutura da árvore
    public static <T> void printTree(No<T> root, String prefix) {
        if (root == null || root.value == null) {
            return;
        }
        System.out.println(prefix + root.value);
        for (No<T> child : root.children) {
            printTree(child, prefix + "  ");
        }
    }

}



