package exam.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExtThreadPool {
	public static class Task implements Runnable {
		public String name;

		public Task(String name) {
			super();
			this.name = name;
		}

		public void run() {
			System.out.println(System.currentTimeMillis() + " thread id " + Thread.currentThread().getId());
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>()) {
			@Override
			protected void beforeExecute(Thread t, Runnable r) {
				System.out.println("beforeExecute " + ((Task) r).name);
			}

			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				System.out.println("afterExecute " + ((Task) r).name);
			}

			@Override
			protected void terminated() {
				System.out.println("terminated");
			}
		};

		for (int i = 0; i < 10; i++) {
			threadPool.execute(new Task("name=" + i));
		}

		threadPool.shutdown();

	}

}
