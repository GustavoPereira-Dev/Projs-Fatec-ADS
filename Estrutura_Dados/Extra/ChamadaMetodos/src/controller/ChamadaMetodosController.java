package controller;

import model.Funcao;
import model.pilha.Stack;
import java.util.*;

import javax.swing.JOptionPane;

public class ChamadaMetodosController {
    private static Map<String, Funcao> funcoes = new HashMap<>();

    public void principal(){
        Scanner scanner = new Scanner(System.in);
        Stack<Funcao> pilhaDeFuncoes = new Stack<>();
        Set<Funcao> historico = new HashSet<>();

        while (true) {
        	
            int escolha = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma opção: (1) Adicionar uma função, (2) Executar a função principal, (3) Sair"));

            if (escolha == 1) {
                adicionarFuncao(scanner);
            } else if (escolha == 2) {
                String nomeFuncao = JOptionPane.showInputDialog("Digite o nome da função principal: ");
                Funcao funcao = funcoes.get(nomeFuncao);
                if (funcao != null) {
                    try {
                        funcao.executar(pilhaDeFuncoes, historico);
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                } else {
                	JOptionPane.showMessageDialog(null, "Função não encontrada.");
                }
            } else if (escolha == 3) {
            	JOptionPane.showMessageDialog(null, "Saindo...");
                break;
            }
        }
        scanner.close();
    }

    private static void adicionarFuncao(Scanner scanner) {
        String nome = JOptionPane.showInputDialog("Digite o nome da nova função/personagem: ");

        String chamadasInput = JOptionPane.showInputDialog("Digite os nomes das funções chamadas/ações (separados por vírgula): ");
        String[] chamadasNomes = chamadasInput.split(",");

        List<Funcao> chamadas = new ArrayList<>();
        for (String chamadaNome : chamadasNomes) {
            chamadaNome = chamadaNome.trim();
            if (!chamadaNome.isEmpty()) {
                Funcao chamada = funcoes.get(chamadaNome);
                if (chamada != null) {
                    chamadas.add(chamada);
                } else {
                	JOptionPane.showMessageDialog(null, "Função/ação " + chamadaNome + " não encontrada. Adicione-a antes de referenciá-la.");
                }
            }
        }

        boolean recursiva = JOptionPane.showConfirmDialog(null, "A função/ação é recursiva? (true/false): ") == 0;

        int parametro = 0;
        if (recursiva) {
            parametro = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor do parâmetro inicial: "));
        }

        Funcao novaFuncao = new Funcao(nome, recursiva, parametro, chamadas.toArray(new Funcao[0]));
        funcoes.put(nome, novaFuncao);
        JOptionPane.showMessageDialog(null, "Função/personagem " + nome + " adicionada com sucesso.");
    }
}

