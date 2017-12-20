package exam.cp;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过ReentrantLock实现生产者、消费者模型
 * @author Administrator
 *
 * @param <T>
 */
public class ConsumerProducer2<T> {
	private final LinkedList<T> list = new LinkedList<>();
	private final int MAX_SIZE = 10;
	private int count = 0;

	private final ReentrantLock lock = new ReentrantLock();
	private final Condition producer = lock.newCondition();
	private final Condition consumer = lock.newCondition();

	/**
	 * 生产,使用lock后方法不需要同步
	 * 
	 * @param t
	 */
	public void put(T t) {
		try {
			lock.lock();
			while (list.size() >= MAX_SIZE) {
				// 生产者线程等待
				producer.await();
			}

			System.out.println(Thread.currentThread().getName() + ",put:" + t);
			list.add(t);
			++count;
			// 唤醒消费者线程
			consumer.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (lock.isHeldByCurrentThread()) {
				lock.unlock();
			}
		}
	}

	/**
	 * 消费
	 * 
	 * @return
	 */
	public T get() {
		T t = null;
		try {
			lock.lock();
			while (list.size() <= 0) {
				// 消费者线程等待
				consumer.await();
			}
			t = list.removeFirst();
			System.out.println(Thread.currentThread().getName() + ",remove:" + t);
			count--;
			// 唤醒生产者
			producer.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (lock.isHeldByCurrentThread()) {
				lock.unlock();
			}
		}
		return t;
	}

	public static void main(String[] args) {
		ConsumerProducer2<String> container = new ConsumerProducer2<>();
		// 消费者
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 5; j++) {
						container.get();
					}
				}
			}, "c" + i).start();
		}

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 生产者
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 25; j++) {
						container.put("data-" + j);
					}
				}
			}, "p" + i).start();
		}
	}
}
