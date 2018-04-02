package exam.visibility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 一个容器，提供add，size，两个线程，t1添加1-10个元素，t2监控元素个数，当个数=5时，t2退出，并提示
 * 程序运行后t2不会结束，t1、t2线程间可见性引起
 * 
 * @author mint
 *
 */
public class MyContainer1 {
	List<Object> list = new ArrayList<Object>();

	public void add(Object o) {
		list.add(o);
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		MyContainer1 container = new MyContainer1();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					container.add(new Object());
					System.out.println("add:" + i);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t1").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					if (container.size() == 5) {
						break;
					}
				}
				System.out.println("t2 结束");

			}
		}, "t2").start();
	}
}
