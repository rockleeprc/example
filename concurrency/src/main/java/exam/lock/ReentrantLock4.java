package exam.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可以设置公平锁/非公平锁
 * 
 * @author mint
 *
 */
public class ReentrantLock4 implements Runnable {
	// true:公平锁,默认飞公平锁
	ReentrantLock lock = new ReentrantLock(true);

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + "," + i);
			} finally {
				if (lock.isHeldByCurrentThread()) {
					lock.unlock();
				}
			}
		}
	}

	public static void main(String[] args) {
		ReentrantLock4 task = new ReentrantLock4();
		new Thread(task, "t1").start();
		new Thread(task, "t2").start();
	}

}
