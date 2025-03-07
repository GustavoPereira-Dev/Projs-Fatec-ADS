public class Teste{
	public static void main(String[] args){
		Mouse mouse1 = new Mouse("Rise Mode", "RM-MO-01-FB", "Preto", "2.0", 3200);
		Mouse mouse2 = new Mouse("Logitech", "M170", "Rosa", 2000);
		Notebook note1 = new Notebook("Samsung", 21.2f, "Windows 11 Home", "LPDDR4x 8GB", "NP730QFG-KF2BR", "65 Watts", "4 Celulas (59Wh)", "SSD NVMe: 256 GB", "Intel Core i5-1335U", true);
		
		System.out.println(note1);
		
		note1.mouse = mouse1;
		System.out.println(note1);

		note1.mouse = mouse2;
		System.out.println(note1);
		
	}

}