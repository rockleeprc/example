package exam.lock.dead;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock() 不会导致死锁
 * 
 * @author Administrator
 *
 */
public class DeadLock2 implements Runnable {
	private static ReentrantLock lock1 = new ReentrantLock();
	private static ReentrantLock lock2 = new ReentrantLock();
	private int lock;

	public DeadLock2(int lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			if (lock == 1) {
				lock1.tryLock();
				TimeUnit.SECONDS.sleep(1);
				lock2.tryLock();
			} else {
				lock2.tryLock();
				TimeUnit.SECONDS.sleep(1);
				lock1.tryLock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (lock1.isHeldByCurrentThread()) {
				lock1.unlock();
			}
			if (lock2.isHeldByCurrentThread()) {
				lock2.unlock();
			}
			System.out.println(Thread.currentThread().getId() + " 退出");
		}

	}

	public static void main(String[] args) {
		DeadLock2 d1 = new DeadLock2(1);
		DeadLock2 d2 = new DeadLock2(2);

		Thread t1 = new Thread(d1);
		Thread t2 = new Thread(d2);

		t1.start();
		t2.start();

	}

}
