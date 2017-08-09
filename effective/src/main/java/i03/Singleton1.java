package i03;

public class Singleton1 {
	public static final Singleton1 INSTACE = new Singleton1();

	private Singleton1() {
	}
	
	public void method(){
		System.out.println("Singleton1.method()");
	}

}
