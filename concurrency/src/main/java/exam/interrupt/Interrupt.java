package exam.interrupt;

import org.junit.Test;

public class Interrupt {

	@Test
	public void inerrupt3() throws InterruptedException {
		Thread t = new Thread() {
			@Override
			public void run() {
				while (true) {
					System.out.println(System.currentTimeMillis());
					// 中断逻辑
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("线程中断推出");
						break;
					}
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						System.out.println("InterruptedException");
						/*
						 * 在业务逻辑中，为保证数据一致性和完整性，不会立刻退出
						 * Thread.sleep()由于中断异常，会清除中断标记
						 * Thread.currentThread().isInterrupted()无法捕获这次中断，在catch
						 * ()重新设置中断标记
						 */
						Thread.currentThread().interrupt();
					}
					Thread.yield();
				}
			}
		};
		t.start();
		Thread.sleep(2000);
		t.interrupt();
	}

	/**
	 * 增加中断逻辑
	 * @throws InterruptedException
	 */
	@Test
	public void interrupt2() throws InterruptedException {
		Thread t = new Thread() {
			@Override
			public void run() {
				while (true) {
					System.out.println(System.currentTimeMillis());
					// 中断逻辑
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("线程中断推出");
						break;
					}
					Thread.yield();
				}
			}
		};
		t.start();
		Thread.sleep(2000);
		t.interrupt();

	}

	/**
	 * 中断不会发生任何作用
	 * @throws InterruptedException
	 */
	@Test
	public void interrupt1() throws InterruptedException {
		Thread t = new Thread() {
			@Override
			public void run() {
				while (true) {
					System.out.println(System.currentTimeMillis());
					Thread.yield();
				}
			}
		};
		t.start();
		Thread.sleep(2000);
		// 对t进行中断，t被设置中断状态，但t并没有中断处理逻辑，中断不会发生任何作用
		t.interrupt();
	}

}
