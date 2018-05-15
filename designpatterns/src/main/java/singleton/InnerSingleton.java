package singleton;

public class Singleton {

	private Singleton() {
	}

	public static Singleton getInstace() {
		return LazyHolder.INSTANCE;

	}

	private static class LazyHolder {
		private static final Singleton INSTANCE = new Singleton();
	}
}
