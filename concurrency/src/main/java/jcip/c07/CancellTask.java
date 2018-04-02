package jcip.c07;

import java.util.concurrent.TimeUnit;

public class CancellTask {
	public static void main(String[] args) throws InterruptedException {
		Task task =new Task();
		Thread t = new Thread(task);
		t.start();
		System.out.println(t.isInterrupted());
		TimeUnit.SECONDS.sleep(5);
		System.out.println(t.isInterrupted());
		task.cancel();
	}

	public static class Task implements Runnable {
		private volatile boolean cancelled = false;

		@Override
		public void run() {
			while (!cancelled) {
				System.out.println("task is running。。。");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void cancel() {
			cancelled = true;
		}

	}
}
