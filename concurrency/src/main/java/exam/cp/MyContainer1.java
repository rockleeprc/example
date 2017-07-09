package exam.cp;

import java.util.LinkedList;

public class MyContainer1<T> {
	private final LinkedList<T> list = new LinkedList<>();
	private final int MAX_SIZE = 10;
	private int count = 0;

	public synchronized void put(T t) {
		// 为什么用if
		while (list.size() >= MAX_SIZE) {
//			System.out.println("put wait()");
			try {
				// 超过容器的最大容量,生产线程等待
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("put " + t);
		list.add(t);
		++count;
		// 生产数据后,唤醒等待的消费者
		this.notifyAll();

	}

	public synchronized T get() {
		while (list.size() <= 0) {
//			System.out.println("get wait()");
			try {
				// 容器无内容,消费线程等待
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		T t = list.removeFirst();
		count--;
		// 消费数据,唤醒等待的生产者
		this.notifyAll();
		return t;
	}

	public static void main(String[] args) {
		MyContainer1<String> container = new MyContainer1<>();
		// 消费者
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 5; j++) {
						System.out.println(Thread.currentThread().getName() + " " + container.get());

					}
				}
			}, "c"+i).start();
		}

		// 生产者
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 25; j++) {
						container.put("data-"+j);
					}
				}
			}, "p"+i).start();
		}
	}
}
