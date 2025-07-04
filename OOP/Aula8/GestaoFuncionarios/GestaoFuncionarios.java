import java.util.Scanner;
import java.util.Date;

class GestaoFuncionarios {
    private int indice = 0;
    private Funcionario[] funcionarios = new Funcionario[50];
    private Scanner scan = new Scanner(System.in);

    public void criar() {
        if (indice >= funcionarios.length) {
            System.out.println("Capacidade máxima atingida!");
            return;
        }
        Funcionario f = new Funcionario();

        System.out.println("Digite o ID:");
        f.setId(scan.nextLong());
        scan.nextLine();

        System.out.println("Digite o Nome:");
        f.setNome(scan.nextLine());

        System.out.println("Digite a Matrícula:");
        f.setMatricula(scan.nextLine());

        System.out.println("Digite a Admissão (yyyy-mm-dd):");
        f.setAdmissao(new Date(scan.nextLine()));

        System.out.println("Digite a Demissão (yyyy-mm-dd):");
        f.setDemissao(new Date(scan.nextLine()));

        System.out.println("Digite o Salário:");
        f.setSalario(scan.nextFloat());
        scan.nextLine();

        System.out.println("Digite o Horário:");
        f.setHorario(scan.nextLine());

        funcionarios[indice++] = f;
        System.out.println("Funcionário criado com sucesso!");
    }

    public void exibir() {
        System.out.println("Digite a Matrícula para buscar:");
        String matricula = scan.nextLine();
        boolean encontrado = false;

        for (Funcionario f : funcionarios) {
            if (f != null && f.getMatricula().equals(matricula)) {
				System.out.println(f.toString());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum funcionário encontrado com a matrícula fornecida.");
        }
    }

    public void excluir() {
        System.out.println("Digite a Matrícula para excluir:");
        String matricula = scan.nextLine();
        boolean excluido = false;

        for (int i = 0; i < funcionarios.length; i++) {
            if (funcionarios[i] != null && funcionarios[i].getMatricula().equals(matricula)) {
                funcionarios[i] = null;
                excluido = true;
                System.out.println("Funcionário excluído com sucesso.");
            }
        }

        if (!excluido) {
            System.out.println("Nenhum funcionário encontrado com a matrícula fornecida.");
        }
    }

    public void atualizar() {
        System.out.println("Digite a Matrícula para atualizar:");
        String matricula = scan.nextLine();

        for (Funcionario f : funcionarios) {
            if (f != null && f.getMatricula().equals(matricula)) {
                System.out.println("Digite o Novo Nome:");
                f.setNome(scan.nextLine());

                System.out.println("Digite a Nova Admissão (yyyy-mm-dd):");
                f.setAdmissao(new Date(scan.nextLine()));

                System.out.println("Digite a Nova Demissão (yyyy-mm-dd):");
                f.setDemissao(new Date(scan.nextLine()));

                System.out.println("Digite o Novo Salário:");
                f.setSalario(scan.nextFloat());
                scan.nextLine(); // Consumir quebra de linha

                System.out.println("Digite o Novo Horário:");
                f.setHorario(scan.nextLine());
                System.out.println("Funcionário atualizado com sucesso!");
                return;
            }
        }

        System.out.println("Nenhum funcionário encontrado com a matrícula fornecida.");
    }

    public void menu() {
        while (true) {
            System.out.println("(C)riar (E)xibir (R)emover (A)tualizar (S)air");
            String textoMaiusculo = scan.nextLine().toUpperCase();
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
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}