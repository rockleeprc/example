package exam.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock在获取锁时,可以响应中断
 * 
 * @author mint
 *
 */
public class ReentrantLock3 {

	public static void main(String[] args) throws InterruptedException {
		ReentrantLock lock = new ReentrantLock();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					lock.lock();
					System.out.println("t1 lock");
					TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}, "t1");
		t1.start();
		// t1.interrupt();

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					/*
					 * lock.lock(); 不会响应t2的中断 lockInterruptibly()可以响应中断
					 */
					lock.lockInterruptibly();
					System.out.println("t2 lock");
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					System.out.println("t2 interrupt");
					e.printStackTrace();
				} finally {
					if (lock.isHeldByCurrentThread()) {
						lock.unlock();
					}
				}
			}
		}, "t2");

		t2.start();
		TimeUnit.SECONDS.sleep(3);
		t2.interrupt();
	}
}
