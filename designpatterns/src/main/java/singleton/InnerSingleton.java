package singleton;

public class InnerSingleton {

	private InnerSingleton() {
	}

	public static InnerSingleton getInstace() {
		return LazyHolder.INSTANCE;

	}

	private static class LazyHolder {
		private static final InnerSingleton INSTANCE = new InnerSingleton();
	}
}
