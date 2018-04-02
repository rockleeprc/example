package exam.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal使用空间换时间,锁使用时间换空间
 * 
 * @author mint
 *
 */
public class ThreadLocal1 {
	private static final ThreadLocal<Person> tl = new ThreadLocal<>();

	private static class Person {
		String name = "zhangsan";
	}

	public static void main(String[] args) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//不同线程间只能获取自己的set的变量
				System.out.println(tl.get());
			}
		}, "t1").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				tl.set(new Person());
			}
		}, "t2").start();
	}
}
