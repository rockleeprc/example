package exam.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ExtThreadPool {
	public static class Task implements Runnable {
		public String name;

		public Task(String name) {
			super();
			this.name = name;
		}

		public void run() {
			// System.out.println(System.currentTimeMillis() + " thread id " +
			// Thread.currentThread().getId());
			System.out.println("thread neam " + name);
			try {
				TimeUnit.MINUTES.sleep(Integer.MAX_VALUE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(8, 8, Long.MAX_VALUE, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(7));

		for (int i = 0; i < 15; i++) {
			System.out.println("i="+i);
			threadPool.execute(new Task("t" + i));
		}
		threadPool.shutdown();
	}

	@Test
	public void t2() {
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, Long.MAX_VALUE, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(100));

		for (int i = 0; i < 20; i++) {
			threadPool.execute(new Task("t" + i));
		}
		threadPool.shutdown();
	}

	@Test
	public void t1() {
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
