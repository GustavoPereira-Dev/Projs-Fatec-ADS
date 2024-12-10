package model.estrutura.arvore;

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
    	
    	s.append(prefixo + no.getValor() + '\n');
    
        for (int i = 0; i < no.getFilhos().total(); i++) {
            imprimirNo(no.getFilhos().get(i).getValor(), prefixo + "  ");
        }
        
    }
}
