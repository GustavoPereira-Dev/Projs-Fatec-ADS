public class Componentes{
	String memoriaRAM;
	String placaMae;
	String fonte;
	String bateria;
	String armazenamento;
	String processador;
	boolean eSoldado;
	
	public Componentes(String memoriaRAM, String placaMae, String fonte, String bateria, String armazenamento, String processador, boolean eSoldado){
		this.memoriaRAM = memoriaRAM;
		this.placaMae = placaMae;
		this.fonte = fonte;
		this.bateria = bateria;
		this.armazenamento = armazenamento;
		this.processador = processador;
		this.eSoldado = eSoldado;
	}
	
	public Componentes(){
		super();
	}
	
}