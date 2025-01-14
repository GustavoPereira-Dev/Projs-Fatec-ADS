package lista3.view;

import lista3.controller.Lista3Controller;
import lista3.controller.PlayerController;
import lista3.model.Musica;
import lista3.model.lista.circular.ListaCircular;
import lista3.model.lista.set.Set;

public class Principal {
	public static void main(String[] args) {
		Lista3Controller lc3 = new Lista3Controller();
		PlayerController playerController = new PlayerController();
		
		int[] vetor = {10, 5, 8, 1, 9, 2, 4, 7, 3, 6};
		System.out.println("-----------------Exercicio 1-----------------");
		ListaCircular<Integer> lista = new ListaCircular<Integer>();
		for(int valor: vetor) {
			lista.append(valor);
		}
		
		System.out.println(lista.toString());
		lista.ordenar();
		System.out.println(lista.toString());
		
		System.out.println("-----------------Exercicio 2-----------------");
		System.out.println("--------Item A--------");
		ListaCircular<Integer> listaUniao = new ListaCircular<Integer>();
		ListaCircular<Integer> listaInterseccao = new ListaCircular<Integer>();
		
		ListaCircular<Integer> listaA = new ListaCircular<Integer>();
		int[] vetorA = {3, 5, 8, 12, 9, 7, 16};
		for(int valor: vetorA) {
			listaA.append(valor);
		}
		
		ListaCircular<Integer> listaB = new ListaCircular<Integer>();
		int[] vetorB = {9, 6, 2, 3, 7};
		for(int valor: vetorB) {
			listaB.append(valor);
		}
		
		listaUniao.uniao(listaA, listaB);
		System.out.println("Uniao: " + listaUniao.toString());
		
		System.out.println("--------Item B--------");
		listaInterseccao.interseccao(listaA, listaB);
		System.out.println("Interseccao: " + listaInterseccao.toString());
		
		System.out.println("-----------------Exercicio 3-----------------");
		lc3.inserirPeloVetor();
		
		System.out.println("-----------------Exercicio 4-----------------");
		int[] vetorSet = {3, 3, 9, 6, 9, 8, 9, 5, 7, 10, 4, 8, 10, 8};
		Set<Integer> set = new Set<Integer>();
		
		for(int valor: vetorSet) {
			set.adicionar(valor);
		}
		
		System.out.println(set.toString());
		
		System.out.println("-----------------Exercicio 5-----------------");
		ListaCircular<Musica> playlist = new ListaCircular<Musica>();

        // Adicionando músicas à playlist
        playerController.adicionaMusica(playlist, "Song 1;Artist 1;180");
        playerController.adicionaMusica(playlist, "Song 2;Artist 2;150");
        playerController.adicionaMusica(playlist, "Song 3;Artist 3;200");
        playerController.adicionaMusica(playlist, "Song 4;Artist 4;230");

        // Exibindo a playlist
        System.out.println("Playlist atual:");
        System.out.println(playlist.toString());

        // Executando a playlist
        System.out.println("\nExecutando a playlist:");
        playerController.executaPlaylist(playlist);

        // Removendo uma música da playlist
        playerController.removeMusica(playlist, 1);

        // Exibindo a playlist após a remoção
        System.out.println("\nPlaylist após remoção:");
        System.out.println(playlist.toString());

        // Ordenando a playlist
        playlist.ordenar();

        // Exibindo a playlist após ordenação
        System.out.println("\nPlaylist após ordenação:");
        System.out.println(playlist.toString());

        // Executando a playlist novamente
        System.out.println("\nExecutando a playlist após ordenação:");
        playerController.executaPlaylist(playlist);

	}
}
