package exam.visibility;

import java.util.concurrent.TimeUnit;

import exam.util.Tools;

public class Visibility2 {

	static class Task implements Runnable {
		/**
		 * 不加volatile程序可能不结束
		 */
		private volatile boolean toCancel = false;

		@Override
		public void run() {
			System.out.println("start...");
			while (!toCancel) {
				doSomething();
			}
			System.out.println("end...");
		}

		/**
		 * 模拟业务操作
		 */
		private void doSomething() {
			System.out.println("业务操作");
			Tools.randomPause(50);
		}

		public void cancel() {
			toCancel = true;
			System.out.println(this + " cancel");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Task task = new Task();
		Thread t = new Thread(task);
		t.start();
		TimeUnit.SECONDS.sleep(2);
		task.cancel();
	}
}
