package exam.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ScheduledThreadPool {

	static class Task implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(System.currentTimeMillis() / 1000);
		}

	}

	public static void main(String[] args) {
		ScheduledExecutorService schedul = Executors.newScheduledThreadPool(10);
		/*
		 * 任务执行时间超过period时间，当任务结束后将会立刻执行下一次任务， 现在程序讲每8秒周期执行
		 */
		schedul.scheduleAtFixedRate(new Task(), 0, 2, TimeUnit.SECONDS);
		/*
		 * 不管任务是否超时，都会delay后在执行
		 */
		schedul.scheduleWithFixedDelay(new Task(), 0, 2, TimeUnit.SECONDS);
	}
}
