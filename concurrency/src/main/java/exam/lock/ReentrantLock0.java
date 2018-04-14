package exam.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock0 {

	public static void main(String[] args) throws InterruptedException {
		Task task = new Task();
		Thread t1 = new Thread(task);
		Thread t2 = new Thread(task);
		t1.start();
		t1.join();
		t2.start();
		t2.join();
		System.out.println(j);
	}

	private static final ReentrantLock lock = new ReentrantLock();
	private static int j = 0;

	public static class Task implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 10000; i++) {
				try {
					lock.lock();
					lock.lock();
					j++;
				} finally {
					lock.unlock();
					lock.unlock();
				}
			}

		}
	}

}
