package exam.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadFactoryMain {

	public static void main(String[] args) {
		m1();
	}

	public static void m1() {
		
		for (int i = 0; i < 5; i++) {
			ThreadFactory threadFactory = Executors.defaultThreadFactory();
			Thread t = threadFactory.newThread(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			});
			t.start();
		}
	}

}
