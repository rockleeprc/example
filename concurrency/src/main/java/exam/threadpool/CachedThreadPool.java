package exam.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

import org.junit.Test;

public class CachedThreadPool {

	
	@Test
	public void testSynchronousQueue() throws InterruptedException{
		SynchronousQueue<Integer> queue = new SynchronousQueue<>();
		System.out.println(queue.offer(1));
	}
	
	@Test
	public void testnewCachedThreadPool() throws InterruptedException{
		ExecutorService threadPool = Executors.newCachedThreadPool();
		threadPool.execute(new Task());
		threadPool.execute(new Task());
	}
	
	public static class Task implements Runnable {
		@Override
		public void run() {
			System.out.println(System.currentTimeMillis() + " Thread ID:" + Thread.currentThread().getId());
			try {
				Thread.sleep(Integer.MAX_VALUE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
