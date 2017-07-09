package exam.visibility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author mint
 *
 */
public class MyContainer4 {
	List<Object> list = new ArrayList<Object>();

	public void add(Object o) {
		list.add(o);
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		MyContainer4 container = new MyContainer4();
		final Object lock = new Object();

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("t2 运行");
				// 4 t1.wait后释放锁，t2运行
				synchronized (lock) {
					if (container.size() != 5) {
						try {
							// 1 t1释放锁
							System.out.println("t2 wait");
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// 5 唤醒t1线程
						lock.notify();
					}
				}
				System.out.println("t2 结束");
			}
		}, "t2").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("t1 运行");
				synchronized (lock) {
					for (int i = 0; i < 10; i++) {
						container.add(new Object());
						System.out.println("add:" + i);
						if (container.size() == 5) {
							// 2 t1不释放锁
							System.out.println("t1 notify");
							lock.notify();
							try {
								// 3 为了让t1释放锁
								System.out.println("t1 wait");
								lock.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}, "t1").start();

	}
}
