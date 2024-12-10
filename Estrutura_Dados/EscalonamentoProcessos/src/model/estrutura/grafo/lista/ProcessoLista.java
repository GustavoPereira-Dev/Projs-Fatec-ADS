package model.estrutura.grafo.lista;

import java.util.*;
import java.lang.Exception;


public class ProcessoLista<T> extends Thread {
	
	private Map<String, List<String>> map = new HashMap<>();

	// add: Adiciona um novo elemento
	// has: Verifica se existe um elemento
	// size: Retorna o número de elementos
	// toString

	public void add(String source){
		map.put(source, new LinkedList<String>());
	}

	public void add(String source, String destination, int way){
		if(!has(source)) add(source);
		if(!has(destination)) add(destination);
		
		if(way == 1) {
			map.get(source).add(destination);
		} else if(way == 2) {
			map.get(destination).add(source);
		} else {
			map.get(source).add(destination);
			map.get(destination).add(source);
		}
	}

	public void remove(String s, String d) throws IllegalArgumentException{
		if(!has(s)) throw new IllegalArgumentException("Elemento não encontrado!");
		if(!has(d)) throw new IllegalArgumentException("Destino não encontrado!");
		
		for(int i = 0; i < map.get(s).size(); i++){
			if(map.get(s).get(i) == d){
				map.get(s).remove(i);
			}
		}
			
	}	

	public boolean has(String s){
		return map.containsKey(s);
	}

	public boolean has(String s, String d){
		return map.get(s).contains(d);
	}

	public int size(){
		return map.keySet().size();
	}

	
	public String[] pecorrerSequenciaProcessos() {
		Map<String, List<String>> buffer = map;
		int comp = size();
		String[] sequencia = new String[comp];
		boolean podeExecutar = true;
		int procsExecutados = 0, i = 0;
		boolean[] foiExecutado = new boolean[comp];
		
		while(procsExecutados < comp) {
			for(String v: buffer.keySet()){
				
				for(String w: buffer.get(v)){
					if(has(v,w)) {
						System.out.println("a" + " " + v);
						podeExecutar = false;
						break;
					}
				}
				if(foiExecutado[i] && buffer.get(v).toString() == "[]" ) podeExecutar = false;
				if(podeExecutar) {
					for(String z: buffer.keySet()){
						if(buffer.get(z).contains(v)) buffer.get(z).remove(v);;
					}
					sequencia[procsExecutados] = v.toString();
					foiExecutado[i] = true;
					procsExecutados++;
				}
				podeExecutar = true;
				i++;
			}
			i = 0;
		}
		
		return sequencia;
	}
	
	public String retornarSequencia(String[] sequencia) {
		StringBuilder s = new StringBuilder();
		
		for(int i = 0; i < sequencia.length; i++) {
			s.append((i + 1) + "º" + sequencia[i] + "\n");
		}
		
		return s.toString();
		
	}
	@Override 
	public String toString(){
		StringBuilder builder = new StringBuilder();

		for(String v: map.keySet()){
			builder.append(v.toString() + ": ");
			for(String w: map.get(v)){
				builder.append(w.toString() + " ");
			}
			builder.append("\n");
		}

		return builder.toString();
	}


}