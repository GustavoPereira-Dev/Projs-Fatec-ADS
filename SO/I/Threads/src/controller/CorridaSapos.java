package controller;

public class CorridaSapos extends Thread{
        private int puloS1;
        private int puloS2;
        private int puloS3;
        private int puloS4;
        private int puloS5;

        private static int sapo1, sapo2, sapo3, sapo4, sapo5, volta, posicaoRecente = 1;
        private static final int distanciaFinal = 50;
        private int[] statusCorrida = new int[5];
        private static String podio = "";
        
        public boolean finalCorrida = false;
        
        public CorridaSapos(int puloS1, int puloS2, int puloS3, int puloS4, int puloS5) {
                this.puloS1 = puloS1;
                this.puloS2 = puloS2;
                this.puloS3 = puloS3;
                this.puloS4 = puloS4;
                this.puloS5 = puloS5;
        }


        public void run() {
                corrida();
        }

        public void corrida() {
                volta++;
                
                sapo1 = validarDistancia(sapo1,puloS1,1);
                sapo2 = validarDistancia(sapo2,puloS2,2);
                sapo3 = validarDistancia(sapo3,puloS3,3);
                sapo4 = validarDistancia(sapo4,puloS4,4);
                sapo5 = validarDistancia(sapo5,puloS5,5);

                if(volta == 1) {
                        System.out.println("Ei amigos da rede Fatec, a corrida irá começar neste exato momento! \n"
                                        + "Sapo1 inicia dando um pulo de " + puloS1 + " metros! \n"
                                        + "Sapo2 inicia dando um pulo de " + puloS2 + " metros! \n"
                                        + "Sapo3 inicia dando um pulo de " + puloS3 + " metros! \n"
                                        + "Sapo4 inicia dando um pulo de " + puloS4 + " metros! \n"
                                        + "Sapo5 inicia dando um pulo de " + puloS5 + " metros! \n");
                } else if(Math.max(sapo1, Math.max(sapo2, Math.max(sapo3, Math.max(sapo4, sapo5)))) < distanciaFinal - 5) {
                        System.out.println("Vamos no placar de agora: \n"
                                        + "Sapo1 deu um pulo de " + puloS1 + " metros, dando uma distância pecorrida total de " + sapo1 + " metros! \n"
                                        + "Sapo2 deu um pulo de " + puloS2 + " metros, dando uma distância pecorrida total de " + sapo2 + " metros! \n"
                                        + "Sapo3 deu um pulo de " + puloS3 + " metros, dando uma distância pecorrida total de " + sapo3 + " metros! \n"
                                        + "Sapo4 deu um pulo de " + puloS4 + " metros, dando uma distância pecorrida total de " + sapo4 + " metros! \n"
                                        + "Sapo5 deu um pulo de " + puloS5 + " metros, dando uma distância pecorrida total de " + sapo5 + " metros! \n");
                } else {
                        System.out.println("O final da corrida de sapos está chegando! \n"
                                        + "Sapo1 deu um pulo de " + puloS1 + " metros, dando uma distância pecorrida total de " + sapo1 + " metros! \n"
                                        + "Sapo2 deu um pulo de " + puloS2 + " metros, dando uma distância pecorrida total de " + sapo2 + " metros! \n"
                                        + "Sapo3 deu um pulo de " + puloS3 + " metros, dando uma distância pecorrida total de " + sapo3 + " metros! \n"
                                        + "Sapo4 deu um pulo de " + puloS4 + " metros, dando uma distância pecorrida total de " + sapo4 + " metros! \n"
                                        + "Sapo5 deu um pulo de " + puloS5 + " metros, dando uma distância pecorrida total de " + sapo5 + " metros! \n");
                        if(posicaoRecente > 5){
                                System.out.println("A partida de corrisa foi finalizada");
                                System.out.println("Vejamos o pódio...");
                                finalCorrida = true;
                                
                                try{
                                      Thread.sleep(1000);  
                                } catch(Exception e){
                                        
                                }
                                
                                System.out.println(podio);
                        }
                }

                
        }
        
        
        public int validarDistancia(int s, int p, int n){
                if(s < distanciaFinal){
                        if(s+p >= distanciaFinal){
                                statusCorrida[n-1] = 1;
                                statusCorrida[n-1] = posicaoRecente;
                                System.out.println("O " + posicaoRecente + "° sapo já chegou!");
                                podio+="O sapo " + n + " ficou em " + posicaoRecente + " lugar!\n";
                                posicaoRecente++;
                                
                        }
                        s+=p;
                        
                }
                return s;
        }
        
        

        


}