package exam.blockingqueue;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadPoolTest {

	@Test
	public void testArrayBlockingQueue() throws IOException {
		int poolSize = Runtime.getRuntime().availableProcessors();
		// LinkedBlockingDeque<Runnable> queue = new
		// LinkedBlockingDeque<Runnable>();
		// ArrayBlockingQueue<Runnable> queue = new
		// ArrayBlockingQueue<Runnable>(2);
		SynchronousQueue queue = new SynchronousQueue();
		ExecutorService threadPool = new ThreadPoolExecutor(poolSize + 1, poolSize * 2, 60L * 10, TimeUnit.SECONDS,
				queue);

		for (int i = 0; i < 5; i++) {
			threadPool.submit(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName());
						TimeUnit.SECONDS.sleep(5);
						//printThreadPoolInfo(threadPool);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			System.out.println("taks=" + i);
		}
		System.out.println("end");
		System.in.read();
	}

	private void printThreadPoolInfo(ExecutorService threadPool) {
		ThreadPoolExecutor tpe = (ThreadPoolExecutor) threadPool;
		System.out.println(" getPoolSize=" + tpe.getPoolSize());
		System.out.println(" getTaskCount=" + tpe.getTaskCount());
		System.out.println(" getActiveCount=" + tpe.getActiveCount());
		System.out.println(" getCompletedTaskCount=" + tpe.getCompletedTaskCount());
	}
}
