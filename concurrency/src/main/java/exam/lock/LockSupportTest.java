package exam.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.junit.Test;

public class LockSupportTest {

	@Test
	public void t() throws InterruptedException {
		Task task = new Task();
		Thread t = new Thread(task);
		t.start();
		TimeUnit.SECONDS.sleep(1);
		LockSupport.unpark(t);
	}

	private static class Task implements Runnable {

		@Override
		public void run() {
			System.out.println("A");
			//阻塞当前线程
			LockSupport.park();
			System.out.println("B");
		}
	}
}
