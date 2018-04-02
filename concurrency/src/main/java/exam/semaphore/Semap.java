package exam.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Semap implements Runnable {
	private static Semaphore sema = new Semaphore(5);

	@Override
	public void run() {
		try {
			sema.acquire();
			TimeUnit.SECONDS.sleep(2);
			System.out.println(Thread.currentThread().getId() + " permits");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			sema.release();
		}

	}

	public static void main(String[] args) {
		Semap s = new Semap();
		ExecutorService threadPool = Executors.newFixedThreadPool(20);
		for (int i = 0; i < 20; i++) {
			System.out.println(i);
			threadPool.submit(s);
		}
		threadPool.shutdown();
	}

}
