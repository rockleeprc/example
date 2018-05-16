package singleton;

import java.lang.reflect.Constructor;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class SingletonTest {

	ExecutorService threadPool = Executors.newFixedThreadPool(10);

	@Test
	public void testReflect() {
		try {
			Class<?> clazz = InnerSingleton.class;
			// 通过反射拿到私有的构造方法
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
