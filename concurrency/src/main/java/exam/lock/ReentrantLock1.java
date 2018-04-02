package exam.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 使用
 * 
 * @author mint
 *
 */
public class ReentrantLock1 {

	private Lock lock = new ReentrantLock();

	public void m1() {
		try {
			lock.lock();
			for (int i = 0; i < 10; i++) {
				System.out.println("m1 " + i);
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void m2() {
		try {
			lock.lock();
			System.out.println("m2");
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		ReentrantLock1 rl = new ReentrantLock1();
		new Thread(rl::m1, "t1").start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				rl.m2();
			}
		}, "t2").start();
	}
}
