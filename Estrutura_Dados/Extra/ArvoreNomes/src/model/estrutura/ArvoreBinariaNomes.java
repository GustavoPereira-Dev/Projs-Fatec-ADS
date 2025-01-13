package model.estrutura;

import java.util.ArrayList;
import java.util.List;

public class ArvoreBinariaNomes<T extends Comparable<T>> {
    private No<T> raiz;

    private int altura(No<T> no) {
        return no == null ? 0 : no.getAltura();
    }

    private int fatorBalanceamento(No<T> no) {
        return no == null ? 0 : altura(no.getEsquerdo()) - altura(no.getDireito());
    }

    private No<T> rotacaoDireita(No<T> y) {
        No<T> x = y.getEsquerdo();
        No<T> T2 = x.getDireito();
        x.setDireito(y);
        y.setEsquerdo(T2);
        y.setAltura(Math.max(altura(y.getEsquerdo()), altura(y.getDireito())) + 1);
        x.setAltura(Math.max(altura(x.getEsquerdo()), altura(x.getDireito())) + 1);
        return x;
    }

    private No<T> rotacaoEsquerda(No<T> x) {
        No<T> y = x.getDireito();
        No<T> T2 = y.getEsquerdo();
        y.setEsquerdo(x);
        x.setDireito(T2);
        x.setAltura(Math.max(altura(x.getEsquerdo()), altura(x.getDireito())) + 1);
        y.setAltura(Math.max(altura(y.getEsquerdo()), altura(y.getDireito())) + 1);
        return y;
    }

    private No<T> rotacaoDuplaEsquerda(No<T> z) {
        z.setDireito(rotacaoDireita(z.getDireito()));
        return rotacaoEsquerda(z);
    }

    private No<T> rotacaoDuplaDireita(No<T> z) {
        z.setEsquerdo(rotacaoEsquerda(z.getEsquerdo()));
        return rotacaoDireita(z);
    }

    private No<T> inserir(No<T> no, T valor) {
        if (no == null) return new No<>(valor);
        if (valor.compareTo(no.getValor()) < 0) no.setEsquerdo(inserir(no.getEsquerdo(), valor));
        else if (valor.compareTo(no.getValor()) > 0) no.setDireito(inserir(no.getDireito(), valor));
        else return no; // Não permite valores duplicados

        no.setAltura(1 + Math.max(altura(no.getEsquerdo()), altura(no.getDireito())));

        int balanceamento = fatorBalanceamento(no);

        // Caso 1 - Rotação à direita
        if (balanceamento > 1 && valor.compareTo(no.getEsquerdo().getValor()) < 0) return rotacaoDireita(no);

        // Caso 2 - Rotação à esquerda
        if (balanceamento < -1 && valor.compareTo(no.getDireito().getValor()) > 0) return rotacaoEsquerda(no);

        // Caso 3 - Rotação dupla à direita
        if (balanceamento > 1 && valor.compareTo(no.getEsquerdo().getValor()) > 0) {
            no.setEsquerdo(rotacaoEsquerda(no.getEsquerdo()));
            return rotacaoDireita(no);
        }

        // Caso 4 - Rotação dupla à esquerda
        if (balanceamento < -1 && valor.compareTo(no.getDireito().getValor()) < 0) {
            no.setDireito(rotacaoDireita(no.getDireito()));
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public void adicionarNome(String nome) {
        nome = nome.toLowerCase(); // Converte o nome para minúsculas
        raiz = inserirNome(raiz, nome, 0);
    }

    private No<T> inserirNome(No<T> no, String nome, int indice) {
        if (indice == nome.length()) return no;
        T valor = (T) Character.valueOf(nome.charAt(indice));
        no = inserir(no, valor);
        no = inserirNome(no, nome, indice + 1);
        return no;
    }

    public boolean encontrarNome(No<T> no, String nome) {
        nome = nome.toLowerCase(); // Converte o nome para minúsculas
        return buscarNome(no, nome, 0);
    }

    private boolean buscarNome(No<T> no, String nome, int indice) {
        if (no == null || indice == nome.length()) return no != null;
        T valor = (T) Character.valueOf(nome.charAt(indice));
        no = buscar(no, valor);
        return buscarNome(no, nome, indice + 1);
    }

    private No<T> buscar(No<T> no, T valor) {
        if (no == null || no.getValor().compareTo(valor) == 0) return no;
        if (valor.compareTo(no.getValor()) < 0) return buscar(no.getEsquerdo(), valor);
        return buscar(no.getDireito(), valor);
    }

    public List<String> nomesComCaractere(No<T> no, String prefixo) {
        List<String> nomes = new ArrayList<>();
        encontrarNomes(no, prefixo.toLowerCase(), "", nomes); // Converte o prefixo para minúsculas
        return nomes;
    }

    private void encontrarNomes(No<T> no, String prefixo, String atual, List<String> nomes) {
        if (no != null) {
            String novoAtual = atual + no.getValor();
            if (novoAtual.startsWith(prefixo)) {
                nomes.add(novoAtual);
            }
            encontrarNomes(no.getEsquerdo(), prefixo, novoAtual, nomes);
            encontrarNomes(no.getDireito(), prefixo, novoAtual, nomes);
        }
    }

    public void emOrdem(No<T> no) {
        if (no != null) {
            emOrdem(no.getEsquerdo());
            System.out.print(no.getValor() + " ");
            emOrdem(no.getDireito());
        }
    }

    public void preOrdem(No<T> no) {
        if (no != null) {
            System.out.print(no.getValor() + " ");
            preOrdem(no.getEsquerdo());
            preOrdem(no.getDireito());
        }
    }

    public void posOrdem(No<T> no) {
        if (no != null) {
            posOrdem(no.getEsquerdo());
            posOrdem(no.getDireito());
            System.out.print(no.getValor() + " ");
        }
    }

    public No<T> getRaiz() {
        return raiz;
    }
}