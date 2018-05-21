package singleton;

import java.io.Serializable;

public class HungrySingleton implements Serializable{
	private static final long serialVersionUID = 8272949021898923147L;
	private static final HungrySingleton INSTANCE = new HungrySingleton();

	private HungrySingleton() {
	}

	public static HungrySingleton getInstance() {
		return INSTANCE;
	}

	private Object readResolve() {
		return INSTANCE;
	}

}
