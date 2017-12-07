package exam.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCondition implements Runnable {
	private static ReentrantLock lock = new ReentrantLock();
	private static Condition condition = lock.newCondition();

	@Override
	public void run() {

		try {
			lock.lock();
			condition.await();
			System.out.println(Thread.currentThread().getName()+" is go on");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 只有一个线程不用判断isHeldByCurrentThread()
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ReentrantLockCondition rlc = new ReentrantLockCondition();
		Thread t = new Thread(rlc, "t");
		t.start();
		
		TimeUnit.SECONDS.sleep(2);

		lock.lock();
		condition.signal();
		System.out.println("signal");
		lock.unlock();
	}
}
