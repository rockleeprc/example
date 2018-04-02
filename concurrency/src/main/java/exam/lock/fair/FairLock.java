package exam.lock.fair;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁和非公平锁
 * 
 * synchronized 锁是非公平锁
 * 
 * @author Administrator
 *
 */
public class FairLock implements Runnable {

	/**
	 * 公平锁
	 */
	private static ReentrantLock fairLock = new ReentrantLock(true);
	// private static ReentrantLock fairLock = new ReentrantLock(false);

	@Override
	public void run() {
		while (true) {
			try {
				fairLock.lock();
				System.out.println(Thread.currentThread().getName() + " 获得锁");
			} finally {
				if (fairLock.isHeldByCurrentThread()) {
					fairLock.unlock();
				}
			}
		}
	}

	public static void main(String[] args) {
		FairLock fl = new FairLock();

		Thread t1 = new Thread(fl, "t1");
		Thread t2 = new Thread(fl, "t2");

		t1.start();
		t2.start();
	}

}
