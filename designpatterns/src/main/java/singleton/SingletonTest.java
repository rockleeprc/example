package singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class SingletonTest {

	ExecutorService threadPool = Executors.newFixedThreadPool(10);

	@Test
	public void testConcurrency() {
		for (;;) {
			threadPool.execute(new Runnable() {

				@Override
				public void run() {
					Singleton s = Singleton.getInstace();
					System.out.println(s);
				}
			});
		}
	}

	@Test
	public void testInner() {
		Singleton s1 = Singleton.getInstace();
		Singleton s2 = Singleton.getInstace();
		Singleton s3 = Singleton.getInstace();
		Singleton s4 = Singleton.getInstace();
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
	}
}
