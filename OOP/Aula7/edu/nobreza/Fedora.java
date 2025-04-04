package edu.nobreza;

public class Fedora {
    public static void main(String[] args) {

        Soldado soldado1 = new Soldado();
        soldado1.setPatente("Capitão");
        soldado1.setExperiencia(10);
        
        Bispo bispo1 = new Bispo("Ordem dos Padres", "Diocese de Fedora");
        
        Conselheiro conselheiro1 = new Conselheiro("Estratégia");
        conselheiro1.setBispo(bispo1);  
        System.out.println("Conselheiro - " + conselheiro1);
        
        Conde conde = new Conde(conselheiro1, "Domínio do Norte");
        conde.setSoldado(soldado1); 
        conde.administrarDominio();
        System.out.println("Conde - " + conde);
        

        Principe principe = new Principe(conselheiro1, "Reino Central");
        principe.setSoldado(soldado1);
        
        principe.gonvernar();    
        principe.duelar();       
        principe.liderarGuerra();
        System.out.println("Príncipe - " + principe);
        

        Rainha rainha = new Rainha(conselheiro1, "País do Sul");
        rainha.setSoldado(soldado1);

        rainha.gonvernar();
        rainha.fazerDiplomacia();
        rainha.organizarBaileReal();
        System.out.println("Rainha - " + rainha);
        

        soldado1.proteger();
        
        bispo1.rezar();
        bispo1.liderarCerimonia();
        bispo1.celebrarMissa();
        
        System.out.println("Detalhes do Bispo - " + bispo1);
        System.out.println("Detalhes do Conselheiro - " + conselheiro1);
        System.out.println("Detalhes do Conde - " + conde);
        System.out.println("Detalhes do Príncipe - " + principe);
        System.out.println("Detalhes da Rainha - " + rainha);
    }
}