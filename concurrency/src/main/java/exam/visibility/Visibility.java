package exam.visibility;

import java.util.concurrent.TimeUnit;

import exam.util.Tools;

public class Visibility {

	public static void main(String[] args) throws InterruptedException {
		Task task = new Task();
		Thread t = new Thread(task);
		t.start();
		TimeUnit.SECONDS.sleep(10);
		task.cancel();
	}
}

class Task implements Runnable {
	/**
	 * 不加volatile程序应该会不结束 但实际不是
	 */
	private volatile boolean toCancel = false;

	@Override
	public void run() {

		while (!toCancel) {
			doSomething();
		}
		if (toCancel) {
			System.out.println("Task 退出");
		} else {
			System.out.println("Task 执行");
		}

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