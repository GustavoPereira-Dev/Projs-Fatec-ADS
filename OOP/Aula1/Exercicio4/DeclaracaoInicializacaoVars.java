public class DeclaracaoInicializacaoVars{
	
	public static void main(String[] args){
		
	   //  Nome da empresa com no máximo 25 caracteres. 
       String empresa = "MarSol";

       // Total de salários pagos no mês, em uma grande empresa 
       double totalSomaSalarios = 32213.21;
       
       // Quantidade de dias de faturamento, normalmente esta informação será fixa e nunca mais alterada 
       final int quantDiasFaturamento = 30;
       
       // Todas as notas de um aluno de uma disciplina 
       float[] notasDisciplina = {6.2f, 7.5f, 8.1f, 9.3f};

       // Todos os dados de um carro (placa, chassi, modelo, ano, cor, nome do dono) que foi multado. 
       Carro toyota = new Carro("AS2JID1", "OSUWA", "Corolla", "Vermelho", "José");
       toyota.acumularMulta(30); 

       //  O número de ouro da matemática valor 1.61803... 
       final double numeroOuro = 1.6180339887;

       //  Os nomes dos alunos de uma turma com 10 alunos 
       String[] alunos = {"Ana", "Bruno", "Carlos", "Daniela", "Eduardo", "Fernanda", "Gabriel", "Helena", "Igor", "Juliana"};

       // Quantidade de pares de tênis de um armário 
       int quantPares = 6;

       System.out.println("1 - Nome da Empresa: " + empresa);
       
       System.out.println("2 - Total de Salários: " + totalSomaSalarios);
       
       System.out.println("3- Dias de Faturamento: " + quantDiasFaturamento);
       
       System.out.println("4 - Notas do Aluno: ");
       for (int i = 0; i < notasDisciplina.length; i++) {
           System.out.println(notasDisciplina[i]);
       }
       
       System.out.println("5 - Dados do Carro Multado:");
       System.out.println(toyota.toString());
       
       System.out.println("6 - Número de Ouro: " + numeroOuro);
       System.out.println("7 - Nomes dos Alunos: ");
       for (String nome : alunos) {
           System.out.println(nome);
       }
       System.out.println("8 - Quantidade de Tênis: " + quantPares);
   }
  
   
}

class Carro{
    public String placa;
    public String chassi;
    public String modelo;
    public String cor;
    public String nomeDono;
    public int multa = 0;

    public Carro(String placa, String chassi, String modelo, String cor, String nomeDono){
        this.placa = placa;
        this.chassi = chassi;
        this.modelo = modelo;
        this.cor = cor;
        this.nomeDono = nomeDono;
    }

    public int acumularMulta(int valor){
        return this.multa += multa;
    }

    public int desaacumularMulta(int valor){
        return this.multa -= multa;
    }

    public String toString(){
        return "Placa: " + placa + ", Chassi: " + chassi + ", Modelo: " + modelo + ", Cor: " + 
        cor +  ", Dono: " + nomeDono + ", Multa acumulada: " + multa;
    }

}   