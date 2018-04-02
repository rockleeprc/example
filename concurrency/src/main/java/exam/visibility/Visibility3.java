package exam.visibility;

import java.util.concurrent.TimeUnit;


/**
 * volatile 只保证可见性，不能保证原子性，synchronized 可以保证可见性和原子性
 * 
 * @author Administrator
 *
 */
public class Visibility3 {

	volatile int count = 0;

	public /* synchronized */ void m() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Visibility3 v = new Visibility3();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					v.m();
				}
			}).start();
		}
		TimeUnit.SECONDS.sleep(5);

		System.out.println(v.count);
	}

}