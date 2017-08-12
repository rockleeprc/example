package exam.syn;

import java.util.concurrent.TimeUnit;

/**
 * 线程发生异常后释放锁
 * 
 * @author Administrator
 *
 */
public class ThreadException {
	private int count = 0;

	/**
	 * m1不加synchronized，t1、t2都会抛出1/0异常
	 */
	public synchronized void m1() {
		while (true) {
			count++;
			System.out.println(Thread.currentThread().getName() + "," + count);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (count == 5) {
				// 异常发生后当前线程主动释放锁
				int j = 1 / 0;
			}
		}
	}

	public static void main(String[] args) {
		ThreadException te = new ThreadException();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				te.m1();
			}
		}, "t1");
		t1.start();

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(new Runnable() {

			@Override
			public void run() {
				//当t1发生异常时，t2将被执行
				te.m1();
			}
		}, "t2").start();
	}
}
