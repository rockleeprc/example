package exam.visibility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 使用CountDownLatch
 * 
 * @author mint
 *
 */
public class MyContainer5 {
	List<Object> list = new ArrayList<Object>();

	public void add(Object o) {
		list.add(o);
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		MyContainer5 container = new MyContainer5();

		CountDownLatch count = new CountDownLatch(1);
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (container.size() != 5) {
					try {
						count.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("t2 结束");
			}
		}, "t2").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("t1 运行");
				for (int i = 0; i < 10; i++) {
					container.add(new Object());
					System.out.println("add:" + i);
					if (container.size() == 5) {
						count.countDown();
					}
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t1").start();

	}
}
