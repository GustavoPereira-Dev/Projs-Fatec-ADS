public class Notebook{
	String marca;
	float polegadasTela;
	String so;
	private Componentes componentes;
	Mouse mouse = null;
	
	public Notebook(){
		super();
	}
	
	public Notebook(String marca, float polegadasTela, String so, String memoriaRAM, String placaMae, String fonte, String bateria, String armazenamento, String processador, boolean eSoldado){
		this.marca = marca;
		this.polegadasTela = polegadasTela;
		this.so = so;
		this.componentes = new Componentes(memoriaRAM, placaMae, fonte, bateria, armazenamento, processador, eSoldado);
	}
	
	public Notebook(String marca, float polegadasTela, String memoriaRAM, String placaMae, String fonte, String bateria, String armazenamento, String processador, boolean eSoldado, 
	Mouse mouse)
	{
		this.marca = marca;
		this.polegadasTela = polegadasTela;
		this.componentes = new Componentes(memoriaRAM, placaMae, fonte, bateria, armazenamento, processador, eSoldado);
		this.mouse = mouse;
	}
	
	public String toString(){
		if(mouse == null)
			return "Marca notebook: " + marca + "; polegadas da tela: " + polegadasTela + "; Sistema Operacional: " + so + "; memoria RAM: " + componentes.memoriaRAM + "; fonte: " 
		+ componentes.fonte + "; bateria: " + componentes.bateria + "; CPU: " + componentes.processador + "; e soldado: " + (componentes.eSoldado ? "sim" : "nao");
		if(mouse.usb == null)
			return "Marca notebook: " + marca + "; polegadas da tela: " + polegadasTela + "; Sistema Operacional: " + so + "; memoria RAM: " + componentes.memoriaRAM + "; fonte: " 
			+ componentes.fonte + "; bateria: " + componentes.bateria + "; CPU: " + componentes.processador + "; e soldado: " + (componentes.eSoldado ? "sim" : "nao")
			+ "; Marca do Mouse: " + mouse.marca + "; Modelo do Mouse: " + mouse.modelo + "; Cor do Mouse: " + mouse.cor + "; Mouse sem fio: sim" + "; DPI do mouse: " + mouse.dpi;
		return "Marca notebook: " + marca + "; polegadas da tela: " + polegadasTela + "; Sistema Operacional: " + so + "; memoria RAM: " + componentes.memoriaRAM + "; fonte: " 
		+ componentes.fonte + "; bateria: " + componentes.bateria + "; CPU: " + componentes.processador + "; e soldado: " + (componentes.eSoldado ? "sim" : "nao")
		+ "; Marca do Mouse: " + mouse.marca + "; Modelo do Mouse: " + mouse.modelo + "; Cor do Mouse: " + mouse.cor + "; Mouse sem fio: nao" + "; USB: " + mouse.usb + "; DPI do mouse: " + mouse.dpi;
			
	}
	
}