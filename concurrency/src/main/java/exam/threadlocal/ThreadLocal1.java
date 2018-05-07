package exam.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal使用空间换时间,锁使用时间换空间
 * @see https://xxxblank.github.io/2018/02/11/ThreadLocal%E2%80%94%E2%80%94%E7%BA%BF%E7%A8%8B%E7%9A%84%E5%B1%80%E9%83%A8%E5%8F%98%E9%87%8F/
 * @author mint
 *
 */
public class ThreadLocal1 {
	private static final ThreadLocal<Person> tl = new ThreadLocal<>();

	private static class Person {

		String name = "zhangsan";

		public Person(String name) {
			super();
			this.name = name;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + "]";
		}

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
				tl.set(new Person("B"));
				// 不同线程间只能获取自己的set的变量
				System.out.println("t1:" + tl.get());
			}
		}, "t1").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				tl.set(new Person("A"));
				System.out.println("t2:" + tl.get());
			}
		}, "t2").start();
	}
}
