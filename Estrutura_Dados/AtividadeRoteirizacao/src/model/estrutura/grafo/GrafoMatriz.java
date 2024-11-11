package model.estrutura.grafo;

import java.util.*;

public class GrafoMatriz {
    private int[][] matrizDistancias;
    private String[] labels;
    private Map<String, List<String>> centrosLogisticos;

    public GrafoMatriz(String[] labels) {
        this.labels = labels;
        this.matrizDistancias = new int[labels.length][labels.length];
        this.centrosLogisticos = new HashMap<>();

        for (int i = 0; i < labels.length; i++) {
            Arrays.fill(matrizDistancias[i], Integer.MAX_VALUE);
            matrizDistancias[i][i] = 0;
        }
    }

    public void link(String label1, String label2, int km) {
        int indexLabel1 = Arrays.asList(this.labels).indexOf(label1);
        int indexLabel2 = Arrays.asList(this.labels).indexOf(label2);
        this.matrizDistancias[indexLabel1][indexLabel2] = km;
        this.matrizDistancias[indexLabel2][indexLabel1] = km;
    }

    public void adicionarCentroLogistico(String centro, List<String> cidadesAtendidas) {
        centrosLogisticos.put(centro, cidadesAtendidas);
    }

    public String calcularRota(String cidadeDestino) {
        String fabrica = "Belo Horizonte";
        
        // Calcula a rota e distancia pela fabrica
        List<String> rotaFabrica = encontrarMenorCaminho(fabrica, cidadeDestino);
        int distanciaFabrica = calcularDistancia(rotaFabrica);
        
        // Calcula a rota e distancia pelo centro logistico mais proximo
        String centroMaisProximo = null;
        int distanciaCentroMinima = Integer.MAX_VALUE;
        List<String> rotaCentro = new ArrayList<>();

        for (String centro : centrosLogisticos.keySet()) {
            List<String> rotaCentroTemp = encontrarMenorCaminho(centro, cidadeDestino);
            int distanciaCentroTemp = calcularDistancia(rotaCentroTemp);

            if (distanciaCentroTemp < distanciaCentroMinima) {
                centroMaisProximo = centro;
                distanciaCentroMinima = distanciaCentroTemp;
                rotaCentro = rotaCentroTemp;
            }
        }

        // Formata a resposta para mostrar as duas opcoes
        StringBuilder resposta = new StringBuilder();
        
		resposta.append("Rotas para " + cidadeDestino + ":\n");
        // Opcao da fabrica
        resposta.append("Opcao 1 - Entrega pela Fabrica em Belo Horizonte: ");
        for (String cidade : rotaFabrica) {
            resposta.append(cidade).append(", ");
        }
        resposta.append("Distancia total: ").append(distanciaFabrica).append(" KM\n");
        
        // Opcao do centro logistico mais proximo
        if (centroMaisProximo != null) {
            resposta.append("Opcao 2 - Entrega pelo Centro Logistico em ").append(centroMaisProximo).append(": ");
            for (String cidade : rotaCentro) {
                resposta.append(cidade).append(", ");
            }
            resposta.append("Distancia total: ").append(distanciaCentroMinima).append(" KM\n");
        } else {
            resposta.append("Nenhum centro logistico pode atender essa cidade.\n");
        }

        return resposta.toString();
    }

    private List<String> encontrarMenorCaminho(String origem, String destino) {
        int n = labels.length;
        int[] distancias = new int[n];
        boolean[] visitado = new boolean[n];
        int[] predecessores = new int[n];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        Arrays.fill(predecessores, -1);

        int indiceOrigem = Arrays.asList(labels).indexOf(origem);
        distancias[indiceOrigem] = 0;

        for (int i = 0; i < n; i++) {
            int u = minDistancia(distancias, visitado);
            visitado[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visitado[v] && matrizDistancias[u][v] != Integer.MAX_VALUE &&
                        distancias[u] + matrizDistancias[u][v] < distancias[v]) {
                    distancias[v] = distancias[u] + matrizDistancias[u][v];
                    predecessores[v] = u;
                }
            }
        }

        return reconstruirCaminho(predecessores, Arrays.asList(labels).indexOf(destino));
    }

    private int minDistancia(int[] distancias, boolean[] visitado) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < distancias.length; v++) {
            if (!visitado[v] && distancias[v] <= min) {
                min = distancias[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private List<String> reconstruirCaminho(int[] predecessores, int destino) {
        LinkedList<String> caminho = new LinkedList<>();
        for (int i = destino; i != -1; i = predecessores[i]) {
            caminho.addFirst(labels[i]);
        }
        return caminho;
    }

    private int calcularDistancia(List<String> rota) {
        int distanciaTotal = 0;
        for (int i = 0; i < rota.size() - 1; i++) {
            int index1 = Arrays.asList(labels).indexOf(rota.get(i));
            int index2 = Arrays.asList(labels).indexOf(rota.get(i + 1));
            distanciaTotal += matrizDistancias[index1][index2];
        }
        return distanciaTotal;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < labels.length; i++) {
            builder.append(labels[i]).append(": ");
            for (int j = 0; j < labels.length; j++) {
                if (matrizDistancias[i][j] != Integer.MAX_VALUE) {
                    builder.append(labels[j]).append(" (").append(matrizDistancias[i][j]).append(" KM), ");
                }
            }
            builder.append("\r\n");
        }
        return builder.toString();
    }
}
