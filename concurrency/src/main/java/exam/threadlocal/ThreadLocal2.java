package exam.threadlocal;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocal2 {
	private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
		protected String initialValue() {
			System.out.println("initialValue : " + Thread.currentThread());
			return "";
		};
	};
	private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 3, 0, TimeUnit.SECONDS,
			new ArrayBlockingQueue<>(10));
	private static final CountDownLatch latch = new CountDownLatch(6);

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			threadPool.execute(new Runnable() {

				@Override
				public void run() {
					String s = UUID.randomUUID().toString();
					System.out.println(Thread.currentThread() + " set : " + s);
					threadLocal.set(s);

					s = UUID.randomUUID().toString();
					System.out.println(Thread.currentThread() + " set : " + s);
					threadLocal.set(s);
					latch.countDown();
				}
			});
		}

		for (int i = 0; i < 1; i++) {
			threadPool.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread() + " remove : ");
					threadLocal.remove();
				}
			});
		}

		for (int i = 0; i < 3; i++) {
			threadPool.execute(new Runnable() {

				@Override
				public void run() {
					String s = threadLocal.get();
					System.out.println(Thread.currentThread() + " get : " + s);
					latch.countDown();
				}
			});
		}

		System.out.println(latch.getCount());
		threadPool.shutdown();

	}
}
