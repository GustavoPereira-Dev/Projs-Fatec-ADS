import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class GestaoAlunos {
	private List<Aluno> alunos = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void criar() {
        System.out.print("Digite o ID do aluno: ");
        long id = Long.parseLong(scanner.nextLine());

        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o RA do aluno: ");
        String ra = scanner.nextLine();

        System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
        Date nascimento;
        try {
            nascimento = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Data inválida. Aluno não cadastrado.");
            return;
        }
        

        alunos.add(new Aluno(id, nascimento, ra, nome));
        System.out.println("Aluno criado com sucesso!");
    }

    public void exibir() {
        System.out.print("Digite o RA do aluno para exibir: ");
        String ra = scanner.nextLine();

        for (Aluno aluno : alunos) {
            if (aluno != null && aluno.ra.equals(ra)) {
                System.out.println("Aluno encontrado:");
                System.out.println(aluno);
                return;
            }
        }
        System.out.println("Aluno com RA " + ra + " não encontrado.");
    }

    public void excluir() {
    	int indice = 0;
        System.out.print("Digite o RA do aluno para excluir: ");
        String ra = scanner.nextLine();

        for (Aluno aluno: alunos) {
            if (aluno != null && aluno.ra.equals(ra)) {
                alunos.remove(indice);
                System.out.println("Aluno com RA " + ra + " excluído.");
                return;
            }
            indice++;
        }
        System.out.println("Aluno com RA " + ra + " não encontrado.");
    }

    public void atualizar() {
        System.out.print("Digite o RA do aluno para atualizar: ");
        String ra = scanner.nextLine();

        for (Aluno aluno : alunos) {
            if (aluno != null && aluno.ra.equals(ra)) {
                System.out.print("Digite o novo nome do aluno: ");
                aluno.nome = scanner.nextLine();

                System.out.print("Digite a nova data de nascimento (dd/MM/yyyy): ");
                try {
                    aluno.nascimento = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
                } catch (ParseException e) {
                    System.out.println("Data inválida. Atualização cancelada.");
                    return;
                }
                System.out.println("Aluno atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Aluno com RA " + ra + " não encontrado.");
    }

    public void menu() {
        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("(C)riar  (E)xibir  (R)emover  (A)tualizar  (S)air");

            String textoMaiusculo = scanner.nextLine().toUpperCase();
            char letra = textoMaiusculo.charAt(0);

            switch (letra) {
                case 'C':
                    criar();
                    break;
                case 'E':
                    exibir();
                    break;
                case 'R':
                    excluir();
                    break;
                case 'A':
                    atualizar();
                    break;
                case 'S':
                    System.out.println("Saindo do sistema...");
					System.exit(0);
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}