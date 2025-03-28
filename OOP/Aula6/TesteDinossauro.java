import java.util.Scanner;

public class TesteDinossauro {
    public static void main(String[] args) {
        // Instâncias
        Dinossauro skeep = new Dinossauro();
        Scanner scan = new Scanner(System.in);

        while (true) {
            // Exibir características
            System.out.println("\nCaracterísticas do Skeep:");
            skeep.mostrarCaracteristicas();

            // Exibir menu
            System.out.println("\nEscolha uma ação:");
            System.out.println("(P)ular  (C)orrer  Co(M)er  C(A)ntar  Tomar (S)ol  Ficar na S(O)mbra");
            
            // Pegue a opção do usuário
            String textoMaiusculo = scan.nextLine().toUpperCase();
            char letra = textoMaiusculo.charAt(0);

            // Executar a opção correspondente
            switch (letra) {
                case 'P':
                    skeep.pular();
                    break;
                case 'C':
                    skeep.correr();
                    break;
                case 'M':
                    skeep.comer();
                    break;
                case 'A':
                    skeep.cantar();
                    break;
                case 'S':
                    skeep.tomarSol();
                    break;
                case 'O':
                    skeep.ficarNaSombra();
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
	}
}