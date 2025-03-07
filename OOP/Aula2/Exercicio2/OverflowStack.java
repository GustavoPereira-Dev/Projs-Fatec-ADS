public class OverflowStack{
    public static void main(String[] args) {
		overflow(5);
	}

	public static int overflow(int num){
        return num + overflow(num - 1);
    }

}