package exam.visibility;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 对数据++、--中保证原子性的操作，可以使用原子类AtomicXX效率更高，调用原子类的两个方法，不保证原子性
 * 
 * @author Administrator
 *
 */
public class Visibility4 {

	AtomicInteger count = new AtomicInteger(0);

	public void m() {
		for (int i = 0; i < 10000; i++) {
			/*
			不保证原子性
			if(count.get()<1000){
				count.incrementAndGet();
			}*/
			count.incrementAndGet();// count++
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Visibility4 v = new Visibility4();
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