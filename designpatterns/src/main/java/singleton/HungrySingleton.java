package singleton;

public class HungrySingleton {
	private static final HungrySingleton INSTANCE = new HungrySingleton();

	private HungrySingleton() {
	}

	public static HungrySingleton getInstance() {
		return INSTANCE;
	}
}
