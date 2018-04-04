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
			//释放锁
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
		ReentrantLockCondition task = new ReentrantLockCondition();
		Thread t = new Thread(task, "t");
		t.start();
		
		TimeUnit.SECONDS.sleep(2);

		//先获取锁才能调用signal
		lock.lock();
		//主线程唤醒在condition等待的t线程
		condition.signal();
		System.out.println("signal");
		lock.unlock();
	}
}
