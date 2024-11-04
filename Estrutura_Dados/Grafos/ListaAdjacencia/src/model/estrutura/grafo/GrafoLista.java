package model.estrutura.grafo;

import java.util.*;
import java.lang.Exception;


public class GrafoLista<T> {
	
	private Map<T, List<T>> map = new HashMap<>();

	// add: Adiciona um novo elemento
	// has: Verifica se existe um elemento
	// size: Retorna o número de elementos
	// toString

	public void add(T source){
		map.put(source, new LinkedList<T>());
	}

	public void add(T source, T destination){
		if(!has(source)) add(source);
		if(!has(destination)) add(destination);
		
		map.get(source).add(destination);
		map.get(destination).add(source);
	}

	public void remove(T s, T d) throws IllegalArgumentException{
		if(!has(s)) throw new IllegalArgumentException("Elemento não encontrado!");
		if(!has(d)) throw new IllegalArgumentException("Destino não encontrado!");
		
		for(int i = 0; i < map.get(s).size(); i++){
			if(map.get(s).get(i) == d){
				map.get(s).remove(i);
			}
		}
			
	}	

	public boolean has(T s){
		return map.containsKey(s);
	}

	public boolean has(T s, T d){
		return map.get(s).contains(d);
	}

	public int size(){
		return map.keySet().size();
	}

	@Override 
	public String toString(){
		StringBuilder builder = new StringBuilder();

		for(T v: map.keySet()){
			builder.append(v.toString() + ": ");
			for(T w: map.get(v)){
				builder.append(w.toString() + " ");
			}
			builder.append("\n");
		}

		return builder.toString();
	}


}