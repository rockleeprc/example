package exam.visibility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 增加同步，所以用wait/notify
 * 
 * wait会释放锁
 * notify不会释放锁
 * 
 * @author mint
 *
 */
public class MyContainer3 {
	List<Object> list = new ArrayList<Object>();

	public void add(Object o) {
		list.add(o);
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		MyContainer3 container = new MyContainer3();
		final Object lock = new Object();

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("t2 运行");
				synchronized (lock) {
					if (container.size() != 5) {
						try {
							lock.wait();
							System.out.println("t2 wait");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
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
							/**
							 * 不释放锁，t2会被唤醒，但因获取不到锁，无法执行
							 * 需要等待t1结束会，t2才会执行
							 */
							lock.notify();
							System.out.println("t1 notify");
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
