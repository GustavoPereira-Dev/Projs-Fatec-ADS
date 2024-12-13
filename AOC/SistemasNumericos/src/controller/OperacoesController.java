package controller;

public class OperacoesController {

    public String soma(String bin1, String bin2) {
        StringBuilder resultado = new StringBuilder();
        int carry = 0;

        int i = bin1.length() - 1;
        int j = bin2.length() - 1;
        while (i >= 0 || j >= 0 || carry == 1) {
            int soma = carry;
            if (i >= 0) soma += bin1.charAt(i--) - '0';
            if (j >= 0) soma += bin2.charAt(j--) - '0';
            resultado.append(soma % 2);
            carry = soma / 2;
        }
        return resultado.reverse().toString();
    }

    public String subtracao(String bin1, String bin2) {
        StringBuilder resultado = new StringBuilder();
        int borrow = 0;

        // Igualar os tamanhos das strings binárias preenchendo com zeros à esquerda
        while (bin1.length() < bin2.length()) {
            bin1 = "0" + bin1;
        }
        while (bin2.length() < bin1.length()) {
            bin2 = "0" + bin2;
        }

        // Iterar pelos bits de trás para frente
        for (int i = bin1.length() - 1; i >= 0; i--) {
            int bit1 = bin1.charAt(i) - '0';
            int bit2 = bin2.charAt(i) - '0';

            int sub = bit1 - bit2 - borrow;
            if (sub == -1) {
                sub = 1;
                borrow = 1;
            } else if (sub == -2) {
                sub = 0;
                borrow = 1;
            } else {
                borrow = 0;
            }

            resultado.append(sub);
        }

        // Remover zeros à esquerda
        while (resultado.length() > 1 && resultado.charAt(resultado.length() - 1) == '0') {
            resultado.setLength(resultado.length() - 1);
        }

        return resultado.reverse().toString();
    }


    public String multiplicacao(String bin1, String bin2) {
        String resultado = "0";
        int i = 0;

        for (int j = bin2.length() - 1; j >= 0; j--) {
            if (bin2.charAt(j) == '1') {
                resultado = soma(resultado, bin1 + "0".repeat(i));
            }
            i++;
        }
        return resultado;
    }

    public String divisao(String bin1, String bin2) {
        if (bin2.equals("0")) {
            throw new ArithmeticException("Divisão por zero não é permitida");
        }

        StringBuilder resultado = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < bin1.length(); i++) {
            temp.append(bin1.charAt(i));
            if (compare(temp.toString(), bin2) >= 0) {
                resultado.append("1");
                temp = new StringBuilder(subtracao(temp.toString(), bin2));
            } else {
                resultado.append("0");
            }
        }
        return resultado.toString().replaceFirst("^0+(?!$)", "");
    }

    private int compare(String bin1, String bin2) {
        bin1 = bin1.replaceFirst("^0+(?!$)", "");
        bin2 = bin2.replaceFirst("^0+(?!$)", "");
        if (bin1.length() > bin2.length()) return 1;
        if (bin1.length() < bin2.length()) return -1;
        return bin1.compareTo(bin2);
    }
    
    public String somaOctal(String oct1, String oct2) {
        int len1 = oct1.length();
        int len2 = oct2.length();
        int maxLen = Math.max(len1, len2);
        StringBuilder resultado = new StringBuilder();
        int carry = 0;
        
        for (int i = 0; i < maxLen; i++) {
            int digit1 = (i < len1) ? Character.getNumericValue(oct1.charAt(len1 - 1 - i)) : 0;
            int digit2 = (i < len2) ? Character.getNumericValue(oct2.charAt(len2 - 1 - i)) : 0;
            int soma = digit1 + digit2 + carry;
            carry = soma / 8;
            resultado.append(soma % 8);
        }
        if (carry != 0) {
            resultado.append(carry);
        }
        return resultado.reverse().toString();
    }

    public String subtracaoOctal(String oct1, String oct2) {
        int len1 = oct1.length();
        int len2 = oct2.length();
        StringBuilder resultado = new StringBuilder();
        int borrow = 0;

        for (int i = 0; i < len1; i++) {
            int digit1 = Character.getNumericValue(oct1.charAt(len1 - 1 - i));
            int digit2 = (i < len2) ? Character.getNumericValue(oct2.charAt(len2 - 1 - i)) : 0;
            int sub = digit1 - digit2 - borrow;
            if (sub < 0) {
                sub += 8;
                borrow = 1;
            } else {
                borrow = 0;
            }
            resultado.append(sub);
        }
        return resultado.reverse().toString().replaceFirst("^0+(?!$)", "");
    }

    public String multiplicacaoOctal(String oct1, String oct2) {
        String resultado = "0";
        int len2 = oct2.length();

        for (int i = 0; i < len2; i++) {
            int digit2 = Character.getNumericValue(oct2.charAt(len2 - 1 - i));
            StringBuilder temp = new StringBuilder();
            int carry = 0;

            for (int j = 0; j < digit2; j++) {
                temp.append("0");
            }

            for (int j = oct1.length() - 1; j >= 0; j--) {
                int digit1 = Character.getNumericValue(oct1.charAt(j));
                int multiplicacao = digit1 * digit2 + carry;
                carry = multiplicacao / 8;
                temp.append(multiplicacao % 8);
            }

            if (carry != 0) {
                temp.append(carry);
            }

            resultado = somaOctal(resultado, temp.reverse().toString());
        }
        return resultado;
    }

    public String divisaoOctal(String oct1, String oct2) {
        if (oct2.equals("0")) {
            throw new ArithmeticException("Divisão por zero não é permitida");
        }

        StringBuilder resultado = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < oct1.length(); i++) {
            temp.append(oct1.charAt(i));
            while (temp.length() > 0 && temp.charAt(0) == '0') {
                temp.deleteCharAt(0);
            }
            if (compareOctal(temp.toString(), oct2) >= 0) {
                resultado.append("1");
                temp = new StringBuilder(subtracaoOctal(temp.toString(), oct2));
            } else {
                resultado.append("0");
            }
        }
        return resultado.toString().replaceFirst("^0+(?!$)", "");
    }

    private int compareOctal(String oct1, String oct2) {
        oct1 = oct1.replaceFirst("^0+(?!$)", "");
        oct2 = oct2.replaceFirst("^0+(?!$)", "");
        if (oct1.length() > oct2.length()) return 1;
        if (oct1.length() < oct2.length()) return -1;
        return oct1.compareTo(oct2);
    }

    public String somaHexadecimal(String hex1, String hex2) {
        int len1 = hex1.length();
        int len2 = hex2.length();
        int maxLen = Math.max(len1, len2);
        StringBuilder resultado = new StringBuilder();
        int carry = 0;

        for (int i = 0; i < maxLen; i++) {
            int digit1 = (i < len1) ? Character.getNumericValue(hex1.charAt(len1 - 1 - i)) : 0;
            int digit2 = (i < len2) ? Character.getNumericValue(hex2.charAt(len2 - 1 - i)) : 0;
            int soma = digit1 + digit2 + carry;
            carry = soma / 16;
            resultado.append(Integer.toHexString(soma % 16).toUpperCase());
        }
        if (carry != 0) {
            resultado.append(Integer.toHexString(carry).toUpperCase());
        }
        return resultado.reverse().toString();
    }

    public String subtracaoHexadecimal(String hex1, String hex2) {
        int len1 = hex1.length();
        int len2 = hex2.length();
        StringBuilder resultado = new StringBuilder();
        int borrow = 0;

        for (int i = 0; i < len1; i++) {
            int digit1 = Character.getNumericValue(hex1.charAt(len1 - 1 - i));
            int digit2 = (i < len2) ? Character.getNumericValue(hex2.charAt(len2 - 1 - i)) : 0;
            int sub = digit1 - digit2 - borrow;
            if (sub < 0) {
                sub += 16;
                borrow = 1;
            } else {
                borrow = 0;
            }
            resultado.append(Integer.toHexString(sub).toUpperCase());
        }
        return resultado.reverse().toString().replaceFirst("^0+(?!$)", "");
    }

    public String multiplicacaoHexadecimal(String hex1, String hex2) {
        String resultado = "0";
        int len2 = hex2.length();

        for (int i = 0; i < len2; i++) {
            int digit2 = Character.getNumericValue(hex2.charAt(len2 - 1 - i));
            StringBuilder temp = new StringBuilder();
            int carry = 0;

            for (int j = 0; j < digit2; j++) {
                temp.append("0");
            }

            for (int j = hex1.length() - 1; j >= 0; j--) {
                int digit1 = Character.getNumericValue(hex1.charAt(j));
                int multiplicacao = digit1 * digit2 + carry;
                carry = multiplicacao / 16;
                temp.append(Integer.toHexString(multiplicacao % 16).toUpperCase());
            }

            if (carry != 0) {
                temp.append(Integer.toHexString(carry).toUpperCase());
            }

            resultado = somaHexadecimal(resultado, temp.reverse().toString());
        }
        return resultado;
    }

    public String divisaoHexadecimal(String hex1, String hex2) {
        if (hex2.equals("0")) {
            throw new ArithmeticException("Divisão por zero não é permitida");
        }

        StringBuilder resultado = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < hex1.length(); i++) {
            temp.append(hex1.charAt(i));
            while (temp.length() > 0 && temp.charAt(0) == '0') {
                temp.deleteCharAt(0);
            }
            if (compareHexadecimal(temp.toString(), hex2) >= 0) {
                resultado.append("1");
                temp = new StringBuilder(subtracaoHexadecimal(temp.toString(), hex2));
            } else {
                resultado.append("0");
            }
        }
        return resultado.toString().replaceFirst("^0+(?!$)", "");
    }

    private int compareHexadecimal(String hex1, String hex2) {
        hex1 = hex1.replaceFirst("^0+(?!$)", "");
        hex2 = hex2.replaceFirst("^0+(?!$)", "");
        if (hex1.length() > hex2.length()) return 1;
        if (hex1.length() < hex2.length()) return -1;
        return hex1.compareTo(hex2);
    }


}
