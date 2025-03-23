import java.util.Scanner;

class Dinossauro {
    // Características do dinossauro
    int energia;
    int velocidade;
    int temperatura;
    String humor;

    // Construtor
    public Dinossauro() {
        energia = 50;
        velocidade = 50;
        temperatura = 25;
        humor = "Neutro";
    }

    // Métodos comportamentais
    public void pular() {
        energia -= 10;
        velocidade -= 5;
        humor = "Feliz";
    }

    public void correr() {
        energia -= 15;
        velocidade -= 10;
        humor = "Feliz";
    }

    public void comer() {
        energia += 20;
        velocidade -= 5;
        humor = "Feliz";
    }

    public void cantar() {
        energia -= 5;
        humor = "Feliz";
    }

    public void tomarSol() {
        velocidade += 10;
        temperatura += 5;
        humor = "Feliz";
    }

    public void ficarNaSombra() {
        energia += 10;
        temperatura -= 5;
        humor = "Triste";
    }

    // Método para exibir características
    public void mostrarCaracteristicas() {
        System.out.println("Energia: " + energia);
        System.out.println("Velocidade: " + velocidade);
        System.out.println("Temperatura: " + temperatura);
        System.out.println("Humor: " + humor);
    }
}

