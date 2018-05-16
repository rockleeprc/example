package singleton;

public class InnerSingleton {

	private static boolean initialized = false;

	private InnerSingleton() {
		synchronized (InnerSingleton.class) {
			System.out.println(initialized);
			if (initialized == false) {
				initialized = !initialized;
			} else {
				throw new RuntimeException("单例已被侵犯");
			}
		}

	}

	public static final InnerSingleton getInstace() {
		return LazyHolder.INSTANCE;

	}

	private static class LazyHolder {
		private static final InnerSingleton INSTANCE = new InnerSingleton();
	}
}
