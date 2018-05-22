package singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class SingletonTest {

	ExecutorService threadPool = Executors.newFixedThreadPool(10);

	/**
	 * 单例对象反序列化时，要在单例对象内重写readResolve()，不然反序列化时会重新new一个对象
	 */
	@Test
	public void testSingletonSerial() {
		HungrySingleton s1 = HungrySingleton.getInstance();
		HungrySingleton s2 = null;
		try (FileOutputStream fos = new FileOutputStream("HungrySingleton.obj");
				FileInputStream fis = new FileInputStream("HungrySingleton.obj");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			oos.writeObject(s1);
			s2 = (HungrySingleton) ois.readObject();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(s1);
		System.out.println(s2);
	}

	/**
	 * 通过反射实例化案例对象
	 */
	@Test
	public void testReflect() {
		try {
			Class<?> clazz = InnerSingleton.class;
			Constructor<?> c = clazz.getDeclaredConstructor();
			c.setAccessible(true);

			Object o1 = c.newInstance();
			Object o2 = c.newInstance();

			System.out.println(o1);
			System.out.println(o2);

			System.out.println(o1 == o2);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 并发实现单例类
	 */
	@Test
	public void testConcurrency() {
		int count = 10;
		CountDownLatch latch = new CountDownLatch(count);
		System.out.println(latch.getCount());
		for (int i = 0; i < count; i++) {

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						latch.await();
						// LazySingleton instance = LazySingleton.getInstance();
						// HungrySingleton instance =
						// HungrySingleton.getInstance();
						InnerSingleton instance = InnerSingleton.getInstace();
						System.out.println(instance);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}).start();
			latch.countDown();
		}

	}

	/**
	 * 静态内部类实现单例
	 */
	@Test
	public void testInner() {
		InnerSingleton s1 = InnerSingleton.getInstace();
		InnerSingleton s2 = InnerSingleton.getInstace();
		InnerSingleton s3 = InnerSingleton.getInstace();
		InnerSingleton s4 = InnerSingleton.getInstace();
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
	}
}
