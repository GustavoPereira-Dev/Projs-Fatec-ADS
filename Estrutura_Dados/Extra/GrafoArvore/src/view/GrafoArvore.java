package view;

import model.estrutura.Grafo;
import model.estrutura.GrafoUtils;
import model.estrutura.No;

public class GrafoArvore {
    public static void main(String[] args) {
        Grafo<Integer> graph = new Grafo<>();
        No<Integer> node1 = new No<>(1);
        No<Integer> node2 = new No<>(2);
        No<Integer> node3 = new No<>(3);
        No<Integer> node4 = new No<>(4);
        No<Integer> node5 = new No<>(5);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        // Adicionando nós filhos
        node1.children.add(node2);
        node1.children.add(node3);
        node2.children.add(node4);
        node4.children.add(node5);

        // Imprimindo o grafo original
        System.out.println("Grafo Original:");
        GrafoUtils.printGraph(graph);

        No<Integer> root = GrafoUtils.graphToTree(graph, 1);

        // Adicionando e removendo nós na árvore
        GrafoUtils.addNodeToTree(root, 1, 5);
        GrafoUtils.removeNodeFromTree(root, 3);

        // Imprimindo a árvore convertida
        System.out.println("\nÁrvore Convertida:");
        GrafoUtils.printTree(root, "");

        Grafo<Integer> graphFromTree = GrafoUtils.treeToGraph(root);

        // Imprimindo o grafo convertido de volta da árvore
        System.out.println("\nGrafo Convertido de Volta:");
        GrafoUtils.printGraph(graphFromTree);

        System.out.println("Conversão e manipulação completadas.");
    }
}

