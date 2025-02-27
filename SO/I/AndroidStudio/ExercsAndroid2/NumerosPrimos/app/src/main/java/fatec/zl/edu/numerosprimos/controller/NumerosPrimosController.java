package fatec.zl.edu.numerosprimos.controller;

public class NumerosPrimosController {

    public String verMaior(int num1, int num2){
        String res;
        if(num1 > num2){
            res = "num1";

        } else{
            res = "num2";
        }

        return res;
    }

    public String verSomatoriaNumerosPrimos(int num1, int num2){
        String maiorN = verMaior(num1,num2);
        int maior = maiorN.contains("num1") ? num1 : num2;
        int menor = !(maiorN.contains("num1")) ? num1 : num2;
        int indx = 0, indxMod;
        boolean primeiroPrimo = false;
        StringBuilder res = new StringBuilder();

        if(menor >= 0 && maior <= 50){
            for (int i = menor; i <= maior; i++) {
                boolean numeroPrimo = true;
                if (i <= 1) {
                    numeroPrimo = false;
                } else {
                    for (int j = 2; j <= Math.sqrt(i); j++) {
                        if (i % j == 0) {
                            numeroPrimo = false;
                            break;
                        }
                    }
                }
                if (numeroPrimo) {
                    if(primeiroPrimo){
                        res.append(", " + i);
                    } else{
                        res.append(i);
                        primeiroPrimo = true;
                    }
                }
            }
        } else if(menor < 0){
            res.append("Erro - Menor");
        } else{
            res.append("Erro - Maior");
        }


        return res.toString();
    }


}
