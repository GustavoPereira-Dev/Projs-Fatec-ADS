package model;

import java.time.LocalTime;
import java.util.Comparator;

import model.fila.FilaFifo;
import javax.swing.JOptionPane;

public class TriagemHospitalar {
    FilaFifo<Paciente> filaPreferenciais;
    FilaFifo<Paciente> filaNaoPreferenciais;
    static LocalTime horarioAtualHospital = LocalTime.now(); // Horário atual do hospital

    public TriagemHospitalar() {
        filaPreferenciais = new FilaFifo<>(new Comparator<Paciente>() {
            public int compare(Paciente p1, Paciente p2) {
                return Integer.compare(p1.getPrioridade(), p2.getPrioridade());
            }
        });

        filaNaoPreferenciais = new FilaFifo<>(new Comparator<Paciente>() {
            public int compare(Paciente p1, Paciente p2) {
                return Integer.compare(p1.getPrioridade(), p2.getPrioridade());
            }
        });
    }

    public void adicionarPaciente(Paciente paciente) {
        if (paciente.isPreferencial()) {
            filaPreferenciais.enqueue(paciente);
        } else {
            filaNaoPreferenciais.enqueue(paciente);
        }
    }

    public Paciente chamarPaciente() {
        Paciente proximoPaciente = null;
        if (!filaPreferenciais.toString().equals("[]")) {
            proximoPaciente = filaPreferenciais.dequeue().getValor();
        } else if (!filaNaoPreferenciais.toString().equals("[]")) {
            proximoPaciente = filaNaoPreferenciais.dequeue().getValor();
        }

        // Atualizando o horário do hospital baseado no tempo de atendimento do paciente
        if (proximoPaciente != null) {
            horarioAtualHospital = horarioAtualHospital.isBefore(proximoPaciente.getTempoEntrada())
                    ? proximoPaciente.getTempoEntrada().plusMinutes(proximoPaciente.getTempoAtendimento())
                    : horarioAtualHospital.plusMinutes(proximoPaciente.getTempoAtendimento());
        }
        
        if (proximoPaciente != null) {
        	JOptionPane.showMessageDialog(null, "Atendendo paciente: " + proximoPaciente.getNome() + "\n" 
        			+ "Tempo de Entrada: " + proximoPaciente.getTempoEntrada() + "\n"  
        			+ "Tempo de Atendimento: " + horarioAtualHospital + "\n" 
        			+ "Tempo de Espera: " + java.time.Duration.between(proximoPaciente.getTempoEntrada(), horarioAtualHospital).toMinutes() + " minutos"
        	);
        }
        
        return proximoPaciente;
    }

    public void exibirFilas() {
    	JOptionPane.showMessageDialog(null, "Fila Preferencial: " + "\n"
    			+ filaPreferenciais.toString()
    	);
    	
    	JOptionPane.showMessageDialog(null, "Fila Não Preferencial: " + "\n"
    			+ filaNaoPreferenciais.toString()
    	);
    }
}
