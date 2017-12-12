package exam.rejected;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectedPolicy {

	public static class Task implements Runnable {
		public void run() {
			System.out.println(System.currentTimeMillis() + " thread id " + Thread.currentThread().getId());
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(),
				new RejectedExecutionHandler() {

					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						System.out.println(r.toString() + " is dis");
					}
				});
		Task t = new Task();
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			threadPool.submit(t);
			Thread.sleep(10);
		}
	}
}
