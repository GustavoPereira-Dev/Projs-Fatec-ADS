package controller;

import java.time.LocalTime;
import java.util.Scanner;

import javax.swing.JOptionPane;

import model.Paciente;
import model.TriagemHospitalar;

public class FilaHospitalController {
    public void principal() {
        TriagemHospitalar triagem = new TriagemHospitalar();
        Scanner scanner = new Scanner(System.in);
        
        boolean continuar = true;
        while (continuar) {
            continuar = adicionarPacienteInterativo(triagem, scanner);
        }
        
        Paciente proximoPaciente;
        while ((proximoPaciente = triagem.chamarPaciente()) != null) {
        	JOptionPane.showMessageDialog(null, "Paciente atendido: " + proximoPaciente);
        }
        
        triagem.exibirFilas();

        scanner.close();
    }

    private static boolean adicionarPacienteInterativo(TriagemHospitalar triagem, Scanner scanner) {
        String nome = JOptionPane.showInputDialog("Digite o nome do paciente (ou 'cancelar' para finalizar):");
        
        if (nome.equalsIgnoreCase("cancelar")) {
            return false;
        }

        int prioridade = Integer.parseInt(JOptionPane.showInputDialog("Digite a prioridade do paciente (1-5):"));

        boolean preferencial = JOptionPane.showConfirmDialog(null, "O paciente é preferencial? (true/false):") == 1;

        String tempoEntradaStr = JOptionPane.showInputDialog("Digite o horário de entrada do paciente (HH:MM):");
        LocalTime tempoEntrada = LocalTime.parse(tempoEntradaStr);

        int tempoAtendimentoMinutos = Integer.parseInt(JOptionPane.showInputDialog("Digite o tempo de atendimento do paciente (em minutos):"));
        
        triagem.adicionarPaciente(new Paciente(nome, prioridade, preferencial, tempoEntrada, tempoAtendimentoMinutos));
        return true;
    }
}

