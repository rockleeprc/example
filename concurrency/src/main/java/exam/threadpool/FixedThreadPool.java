package exam.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.omg.PortableServer.ThreadPolicy;

public class FixedThreadPool {

	public static class Task implements Runnable {
		@Override
		public void run() {
			System.out.println(System.currentTimeMillis() + " Thread ID:" + Thread.currentThread().getId());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Task task = new Task();
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			// 输出时应该差1秒，一次执行5个线程
			threadPool.submit(task);
		}
		threadPool.shutdown();
	}
}
