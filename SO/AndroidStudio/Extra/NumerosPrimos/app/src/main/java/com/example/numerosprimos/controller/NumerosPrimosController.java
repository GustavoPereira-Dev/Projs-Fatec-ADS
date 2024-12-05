package com.example.numerosprimos.controller;

public class NumerosPrimosController {


    public String verSomatoriaNumerosPrimos(int menor, int maior){

        int indx = 0, indxMod;
        boolean primeiroPrimo = false;
        StringBuilder res = new StringBuilder();

        if(maior >= 0 && maior <= 100){
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
        } else if(maior < 0){
            res.append("Erro - Menor");
        } else{
            res.append("Erro - Maior");
        }


        return res.toString();
    }
}
