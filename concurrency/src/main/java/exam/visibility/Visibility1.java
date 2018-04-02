package exam.visibility;

import java.util.concurrent.TimeUnit;

import exam.util.Tools;

/**
 * volatile 可见性，只能保证读取可见性
 * 
 * @author Administrator
 *
 */
public class Visibility1 {
	/**
	 * 有无volatile，观察程序
	 */
	volatile boolean running = true;

	public void m() {
		System.out.println("start...");
		while (running) {
		}
		System.out.println("end...");
	}

	public static void main(String[] args) {
		Visibility1 v = new Visibility1();
		new Thread(new Runnable() {

			@Override
			public void run() {
				v.m();

			}
		}, "t1").start();

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		v.running = false;
	}
}