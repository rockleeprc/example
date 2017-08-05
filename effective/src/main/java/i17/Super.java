package i17;

public class Super {

	public Super() {
		/*
		 * 会调用子类的方法
		 */
		overriedMe();
	}

	public void overriedMe() {
		System.out.println("Super.overriedMe()");
	}

}
