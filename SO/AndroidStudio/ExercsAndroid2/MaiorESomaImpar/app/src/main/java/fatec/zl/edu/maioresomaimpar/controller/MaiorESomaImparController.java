package fatec.zl.edu.maioresomaimpar.controller;


public class MaiorESomaImparController {
    public String verMaior(int num1, int num2){
        String res;
        if(num1 > num2){
            res = "num1 é o maior";

        } else{
            res = "num2 é o maior";
        }

        return res;
        //int tvRes = Integer.parseInt(etNum1.getText().toString());
    }

    public int verSomatoriaImpar(int num1, int num2){
        String maiorN = verMaior(num1,num2);
        int maior = maiorN.contains("num1") ? num1 : num2;
        int menor = !(maiorN.contains("num1")) ? num1 : num2;
        int cont = 0;
        StringBuilder res = new StringBuilder();

        res.append(menor);
        for(int i = menor + 1; i <= maior; i++){
            if(i % 2 == 1){
                cont += i;
            }
        }


        return cont;
    }

}
