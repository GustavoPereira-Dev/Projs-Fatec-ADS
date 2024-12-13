package controller;

public class ASCIIController {

    public String binarioParaAscii(String binarios) {
        StringBuilder resultado = new StringBuilder();
        String[] binariosArray = binarios.split(" ");
        
        for (String binario : binariosArray) {
            int decimal = Integer.parseInt(binario, 2);
            char caractere = (char) decimal;
            resultado.append(caractere);
        }

        return resultado.toString();
    }

    public String asciiParaBinario(String texto) {
        StringBuilder resultado = new StringBuilder();
        
        for (char caractere : texto.toCharArray()) {
            String binario = Integer.toBinaryString(caractere);
            while (binario.length() < 8) {
                binario = "0" + binario;  // Preencher com zeros à esquerda
            }
            resultado.append(binario).append(" ");
        }

        return resultado.toString().trim();
    }

}
