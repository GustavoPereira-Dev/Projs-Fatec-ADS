package controller;

import model.estrutura_pilha.Stack;
import model.estrutura_pilha.No;

public class StackController{
	public StackController(){
		super();
	}

	public String teste() throws Exception{
		Stack stack = new Stack();
	
		stack.push(1);
		stack.push(2);
		stack.pop();
		stack.push(3);

		return stack.toString();
	}
}