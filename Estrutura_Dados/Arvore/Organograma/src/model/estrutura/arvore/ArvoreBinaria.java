package model.estrutura.arvore;

import model.estrutura.arvore.No;
import model.estrutura.lista.ListaEncadeadaSimples;

public class ArvoreBinaria {
    private No<String> raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void criarDepartamento(String nome, String paiNome) throws Exception {
        if (paiNome == null) {
            if (raiz != null) {
                throw new Exception("Já existe um departamento raiz.");
            }
            raiz = new No<>(nome);
        } else {
            No<String> pai = buscarDepartamento(paiNome);
            if (pai == null) {
                throw new Exception("Departamento pai não encontrado.");
            }
            No<String> novoDepartamento = new No<>(nome);
            pai.addFilho(novoDepartamento);
        }
    }

    public void adicionarFuncionario(String nomeDepartamento, String nomeFuncionario) throws Exception {
        No<String> departamento = buscarDepartamento(nomeDepartamento);
        if (departamento == null) {
            throw new Exception("Departamento não encontrado.");
        }
        departamento.addFuncionario(nomeFuncionario);
    }

    public void removerFuncionario(String nomeDepartamento, String nomeFuncionario) throws Exception {
        No<String> departamento = buscarDepartamento(nomeDepartamento);
        if (departamento == null) {
            throw new Exception("Departamento não encontrado.");
        }
        departamento.removeFuncionario(nomeFuncionario);
    }

    public String pesquisarFuncionario(String nomeFuncionario) {
        return buscarFuncionario(raiz, nomeFuncionario);
    }

    private No<String> buscarDepartamento(String nome) {
        return buscarDepartamento(raiz, nome);
    }

    private No<String> buscarDepartamento(No<String> no, String nome) {
        if (no == null) return null;
        if (no.getValor().equals(nome)) return no;

        for (int i = 0; i < no.getFilhos().total(); i++) {
            No<String> encontrado = buscarDepartamento(no.getFilhos().get(i).getValor(), nome);
            if (encontrado != null) return encontrado;
        }

        return null;
    }

    private String buscarFuncionario(No<String> no, String nomeFuncionario) {
        if (no == null) return null;

        for (int i = 0; i < no.getFuncionarios().total(); i++) {
            if (no.getFuncionarios().get(i).getValor().equals(nomeFuncionario)) {
                return no.getValor();
            }
        }

        for (int i = 0; i < no.getFilhos().total(); i++) {
            String resultado = buscarFuncionario(no.getFilhos().get(i).getValor(), nomeFuncionario);
            if (resultado != null) {
                return resultado;
            }
        }

        return null;
    }

    public ListaEncadeadaSimples<String> listarOrdem() { 
    	ListaEncadeadaSimples<String> lista = new ListaEncadeadaSimples<>(); 
    	listarOrdem(raiz, lista); 
    	return lista; 
    } 

    public ListaEncadeadaSimples<String> listarPreOrdem() { 
    	ListaEncadeadaSimples<String> lista = new ListaEncadeadaSimples<>(); 
    	listarPreOrdem(raiz, lista); 
    	return lista; 
    } 

    public ListaEncadeadaSimples<String> listarPosOrdem() { 
    	ListaEncadeadaSimples<String> lista = new ListaEncadeadaSimples<>(); 
    	listarPosOrdem(raiz, lista); 
    	return lista; 
    } 

    private void listarOrdem(No<String> atual, ListaEncadeadaSimples<String> lista) { 
    	if (atual != null) { 
    		listarOrdemFilhos(atual.getFilhos(), 0, lista); 
    		lista.append(atual.getValor()); 
    	} 
    } 

    private void listarOrdemFilhos(ListaEncadeadaSimples<No<String>> filhos, int indice, ListaEncadeadaSimples<String> lista) { 
    	if (filhos != null && indice < filhos.total()) { 
    		listarOrdem(filhos.get(indice).getValor(), lista); 
    		listarOrdemFilhos(filhos, indice + 1, lista); 
    	} 
    }

    private void listarPreOrdem(No<String> atual, ListaEncadeadaSimples<String> lista) { 
    	if (atual != null) { 
    		lista.append(atual.getValor()); 
    		listarPreOrdemFilhos(atual.getFilhos(), 0, lista); 
    	} 
    } 

    private void listarPreOrdemFilhos(ListaEncadeadaSimples<No<String>> filhos, int indice, ListaEncadeadaSimples<String> lista) { 
    	if (filhos != null && indice < filhos.total()) { 
    		listarPreOrdem(filhos.get(indice).getValor(), lista); 
    		listarPreOrdemFilhos(filhos, indice + 1, lista); 
    	} 
    }

    private void listarPosOrdem(No<String> atual, ListaEncadeadaSimples<String> lista) { 
    	if (atual != null) { ; 
    		listarPosOrdemFilhos(atual.getFilhos(), 0, lista); 
    		lista.append(atual.getValor());
    	} 
    } 

    private void listarPosOrdemFilhos(ListaEncadeadaSimples<No<String>> filhos, int indice, ListaEncadeadaSimples<String> lista) { 
    	if (filhos != null && indice < filhos.total()) { 
    		listarPosOrdem(filhos.get(indice).getValor(), lista); 
    		listarPosOrdemFilhos(filhos, indice + 1, lista); 
    	} 
    }
  
  
}
