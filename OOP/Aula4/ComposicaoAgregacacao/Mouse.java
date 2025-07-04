public class Mouse{
	String marca;
	String cor;
	String modelo;
	String usb;
	boolean semFio;
	int dpi;
	
	public Mouse(){
		super();
	}
	
	public Mouse(String marca, String modelo, String cor, String usb, int dpi){
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.usb = usb;
		this.semFio = false;
		this.dpi = dpi;
	}
	
	public Mouse(String marca, String modelo, String cor, int dpi){
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.semFio = true;
		this.dpi = dpi;
	}
	
}