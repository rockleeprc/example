package exam.syn;

import java.util.concurrent.locks.ReentrantLock;

public class ReeterLock implements Runnable {

	public static ReentrantLock lock = new ReentrantLock();

	public static int i = 0;

	@Override
	public void run() {
		for (int j = 0; j < 10000000; j++) {
			lock.lock();
			try {
				i++;
			} finally {
				// 业务操作中可能会发生异常，finally保证可以释放锁
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ReeterLock rl = new ReeterLock();
		Thread t1 = new Thread(rl, "t1");
		Thread t2 = new Thread(rl, "t2");
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}
