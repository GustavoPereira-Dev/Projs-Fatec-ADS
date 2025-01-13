package model.estrutura.arvorenaobinaria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArvoreArquivos {
    private No<String> raiz;
    private StringBuilder s;

    public ArvoreArquivos() {
        this.raiz = new No<>("/");
    }

    public void criarEstrutura(String[] caminhos) {
        for (String caminho : caminhos) {
            adicionarCaminho(caminho);
        }
    }

    public void adicionarCaminho(String caminho) {
        String[] partes = caminho.split("/");
        No<String> atual = raiz;

        for (String parte : partes) {
            if (!parte.isEmpty()) {
                No<String> filho = atual.getFilho(parte);
                if (filho == null) {
                    filho = new No<>(parte);
                    atual.addFilho(filho);
                }
                atual = filho;
            }
        }
    }

    public String imprimirArvore() {
        s = new StringBuilder();
        imprimirNo(raiz, "");
        return s.toString();
    }

    private void imprimirNo(No<String> no, String prefixo) {
        s.append(prefixo).append(no.getValor()).append('\n');
        for (int i = 0; i < no.getFilhos().total(); i++) {
            imprimirNo(no.getFilhos().get(i).getValor(), prefixo + "  ");
        }
    }

    public List<String> getExtensoesOrdenadas(String caminho) {
        No<String> no = encontrarNo(raiz, caminho.split("/"), 1);
        Set<String> extensoes = new HashSet<>();
        if (no != null) {
            coletarExtensoes(no, extensoes);
        }
        List<String> extensoesOrdenadas = new ArrayList<>(extensoes);
        Collections.sort(extensoesOrdenadas);
        return extensoesOrdenadas;
    }

    private No<String> encontrarNo(No<String> no, String[] partes, int indice) {
        if (indice >= partes.length || no == null) {
            return no;
        }
        No<String> filho = no.getFilho(partes[indice]);
        return encontrarNo(filho, partes, indice + 1);
    }

    private void coletarExtensoes(No<String> no, Set<String> extensoes) {
        String valor = no.getValor();
        if (valor.contains(".")) {
            String extensao = valor.substring(valor.lastIndexOf(".") + 1);
            extensoes.add(extensao);
        }

        for (int i = 0; i < no.getFilhos().total(); i++) {
            coletarExtensoes(no.getFilhos().get(i).getValor(), extensoes);
        }
    }

	public No<String> getRaiz() {
		return raiz;
	}

	public void setRaiz(No<String> raiz) {
		this.raiz = raiz;
	}
    
    
}
