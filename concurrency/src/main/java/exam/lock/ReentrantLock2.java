package exam.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可以尝试性的获取锁,也可以在获取锁时设置等待时间
 * @author mint
 *
 */
public class ReentrantLock2 {

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
		boolean tryLock = false;
		try {
			/*
			 * 尝试获取锁 lock.tryLock();
			 */
			tryLock = lock.tryLock(5, TimeUnit.SECONDS);
			System.out.println("m2 tryLock=" + tryLock);
			if (tryLock) {
				// TODO
			} else {
				// TODO
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (tryLock)
				lock.unlock();
		}
	}

	public static void main(String[] args) {
		ReentrantLock2 rl = new ReentrantLock2();
		new Thread(rl::m1, "t1").start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				rl.m2();
			}
		}, "t2").start();
	}
}
