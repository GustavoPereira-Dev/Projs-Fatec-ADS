package view;

import controller.GrafoMatrizController;

public class GrafoMatriz{
	public static void main(String[] args){
		try{
			GrafoMatrizController obj = new GrafoMatrizController();
			System.out.println(obj.teste());	
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}