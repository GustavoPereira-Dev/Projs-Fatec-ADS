package model.estrutura.arvore;

import model.estrutura.arvore.No;
import model.estrutura.lista.ListaEncadeadaSimples;

public class ArvoreBinaria<T extends Comparable>{
	private No<T> raiz;
	private int tamanho = 0;
	
	// add: adiciona um novo No
	// ordem: retorna um lista na ordem
	// preOrdem: retorna uma lista na pre-ordem
	// posOrdem: retorn uma lista na pos-ordem
	// remove: remove um No (ordenando os outros depois do removido)
	
	public ArvoreBinaria(){
		this.raiz = null;
	}
	
	public void add(T valor){
		No<T> novo = new No<T>(valor);
		tamanho++;
		if(this.raiz == null){
			this.raiz = novo;
		} else{
			No<T> atual = this.raiz;
			while(true){
				if(novo.getValor().compareTo(atual.getValor()) == -1){
					if(atual.getMenor() != null){
						atual = atual.getMenor();
					} else{
						atual.setMenor(novo);
						break;
					}
				} else{
					if(atual.getMaior() != null){
						atual = atual.getMaior();
					} else{
						atual.setMaior(novo);
						break;
					}
				}
			}
		}
	}
	
	public ListaEncadeadaSimples ordem(){
		ListaEncadeadaSimples lista = new ListaEncadeadaSimples();
		No<T> atual = this.raiz;
		ordem(atual,lista);
		return lista;
	}
	
	public ListaEncadeadaSimples preOrdem(){
		ListaEncadeadaSimples lista = new ListaEncadeadaSimples();
		No<T> atual = this.raiz;
		preOrdem(atual,lista);
		return lista;
	}
	
	public ListaEncadeadaSimples posOrdem(){
		ListaEncadeadaSimples lista = new ListaEncadeadaSimples();
		No<T> atual = this.raiz;
		posOrdem(atual,lista);
		return lista;
	}
	
	private void ordem(No<T> atual, ListaEncadeadaSimples lista){
		if(atual != null){
			ordem(atual.getMenor(), lista);
			lista.append(atual.getValor());
			ordem(atual.getMaior(), lista);
		}
	}
	
	private void preOrdem(No<T> atual, ListaEncadeadaSimples lista){
		if(atual != null){
			lista.append(atual.getValor());
			ordem(atual.getMenor(), lista);
			ordem(atual.getMaior(), lista);
		}
	}
	
	private void posOrdem(No<T> atual, ListaEncadeadaSimples lista){
		if(atual != null){
			ordem(atual.getMenor(), lista);
			ordem(atual.getMaior(), lista);
			lista.append(atual.getValor());
		}
	}
	
	public boolean remove(T valor){
		// buscar o No na árvore
		No<T> atual = this.raiz;
		No<T> paiAtual = null;
		while(atual != null){
			if(atual.getValor().equals(valor)){
				break;
			} else if(valor.compareTo(atual.getValor()) == -1){
				// valor procurado é menor que o atual
				paiAtual = atual;
				atual = atual.getMenor();
			} else{
				// valor procurado é maior que o atual
				paiAtual = atual;
				atual = atual.getMaior();
			}
		}
		
		// verifica se existe o No
		if(atual == null) return false;
		
		// No tem 2 filhos ou No tem somente filho à direita
		if(atual.getMaior() != null){
			No<T> substituto = atual.getMaior();
			No<T> paiSubstituto = substituto.getMenor();
			while(substituto.getMenor() != null){
				paiSubstituto = substituto;
				substituto = substituto.getMenor();
			}
			
			substituto.setMenor(atual.getMenor());
			if(paiAtual != null){
				// verificar se é a raiz
				if(atual.getValor().compareTo(paiAtual.getValor()) == -1){
					paiAtual.setMenor(null);
				} else{
					paiAtual.setMaior(substituto);
				}
			} else{
				// se não tem paiAtual, então é a raiz
				this.raiz = substituto;
				paiSubstituto.setMenor(null);
				this.raiz.setMaior(paiSubstituto);
				this.raiz.setMenor(atual.getMenor());
			}
			
			// removeu o No da árvore
			if(substituto.getValor().compareTo(paiSubstituto.getValor()) == -1){
				paiSubstituto.setMenor(null);
				substituto.setMaior(paiSubstituto);
			} else{
				paiSubstituto.setMaior(null);
			}
				
		} else if(atual.getMenor() != null){
			// tem filho só à esquerda
			No<T> substituto = atual.getMenor();
			No<T> paiSubstituto = atual;
			while(substituto.getMaior() != null){
				paiSubstituto = substituto;
				substituto = substituto.getMaior();
			}
			
			if(paiAtual != null){
				if(atual.getValor().compareTo(paiAtual.getValor()) == -1){
					// atual < paiAtual
					paiAtual.setMenor(substituto);
				} else{
					// atual > paiAtual
					paiAtual.setMaior(substituto);
				}
			} else{
				 // se for a raiz
				 this.raiz = substituto;
			}
			
			// remove o No da arvore
			if(substituto.getValor().compareTo(paiAtual.getValor()) == -1){
				paiSubstituto.setMenor(null);
			} else{
				paiSubstituto.setMenor(null);
			}
		} else{
			// não tem filho
			if(paiAtual != null){
				if(atual.getValor().compareTo(paiAtual.getValor()) == -1){
					// atual < paiAtual
					paiAtual.setMenor(null);
				} else{
					// atual > paiAtual
					paiAtual.setMaior(null);
				}
			} else{
				// é a raiz
				this.raiz = null;
			}
		}
		
		return true;
	}
	
}