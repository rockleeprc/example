package exam.cp;

import java.util.LinkedList;

/**
 * 在方法上添加synchronized实现生产者、消费者模型
 * @author Administrator
 *
 * @param <T>
 */
public class ConsumerProducer1<T> {
	private final LinkedList<T> list = new LinkedList<>();
	private final int MAX_SIZE = 10;
	private int count = 0;

	/**
	 * 生产
	 * 
	 * @param t
	 */
	public synchronized void put(T t) {
		// 为什么用不用if,wait()经常于while一起使用(effective java)
		while (list.size() >= MAX_SIZE) {
			// System.out.println("put wait()");
			try {
				// 超过容器的最大容量,生产线程等待
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println(Thread.currentThread().getName()+",put:"+ t);
		list.add(t);
		++count;
		// 生产数据后,唤醒等待的消费者,notify()有可能叫醒生产者线程
		this.notifyAll();

	}

	/**
	 * 消费
	 * 
	 * @return
	 */
	public synchronized T get() {
		while (list.size() <= 0) {
			// System.out.println("get wait()");
			try {
				// 容器无内容,消费线程等待
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		T t = list.removeFirst();
		System.out.println(Thread.currentThread().getName()+",remove:"+ t);
		count--;
		// 消费数据,唤醒等待的生产者
		this.notifyAll();
		return t;
	}

	public static void main(String[] args) {
		ConsumerProducer1<String> container = new ConsumerProducer1<>();
		// 消费者
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 5; j++) {
//						System.out.println(Thread.currentThread().getName() + " " + container.get());
						container.get();

					}
				}
			}, "c" + i).start();
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
