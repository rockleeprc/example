package exam.threadlocal;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocal4 {
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
		/*
		 * 如果把一个共享的对象直接保存到ThreadLocal中，那么多个线程的ThreadLocal.get()取得的还是这个共享对象本身，
		 * 还是有并发访问问题。 所以要在保存到ThreadLocal之前，通过克隆或者new来创建新的对象，然后再进行保存。
		 */
		String str = "ABC";

		for (int i = 0; i < 3; i++) {
			threadPool.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread() + " set : " + str);
					threadLocal.set(str);

					System.out.println(Thread.currentThread() + " set : " + str);
					threadLocal.set(str);
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
