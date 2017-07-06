package exam.visibility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * volatile list 线程间可见，但t1、t2没有同步， 
 * t2 break时并不准确，t2 while(true)浪费资源
 * 
 * @author mint
 *
 */
public class MyContainer2 {
	List<Object> list = new ArrayList<Object>();

	public void add(Object o) {
		list.add(o);
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		MyContainer2 container = new MyContainer2();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					container.add(new Object());
					System.out.println("add:" + i);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}, "t1").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					// System.out.println(container.size());
					if (container.size() == 5) {
						break;
					}
					// try {
					// TimeUnit.SECONDS.sleep(1);
					// } catch (InterruptedException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }
				}
				System.out.println("t2 结束");

			}
		}, "t2").start();
	}
}
