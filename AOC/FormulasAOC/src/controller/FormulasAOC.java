package controller;

public class FormulasAOC {
	public double taxaExecucaoInstrucao(double[][] cpiI, double iC){
        double numerador = 0.0;
        int i;
        for(i = 0; i < cpiI[0].length; i++){
            numerador += (cpiI[0][i] * cpiI[1][i]);
        }
        
        return numerador / iC;
    }
    
    public double tempoProcessador(double iC, double cpi, double r, double f){
        return iC * cpi * Math.pow(r,1 / f);
    }
        
    public double fatoresDesempenho(double iC, double p, double m, double k, double t){
        return iC * (p + (m * k)) * t;
    }
        
     public double mips(double f, double cpi){
         return f / (cpi * Math.pow(10,6));
     }   
         
     public double bips(double f, double cpi){
         return f / (cpi * Math.pow(10,9));
     }       
         
     public double speedup(double f, double n){
         return 1 / (1 - f) + (f / n);
     }    
         
     public double ddrSdram(int modelo){
         return Math.pow(2,modelo);
     }
}
