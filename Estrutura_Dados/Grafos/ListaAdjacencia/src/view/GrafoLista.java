package view;

import controller.GrafoListaController;

public class GrafoLista{
	public static void main(String[] args){
		try{
			GrafoListaController obj = new GrafoListaController();
			System.out.println(obj.teste());
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}