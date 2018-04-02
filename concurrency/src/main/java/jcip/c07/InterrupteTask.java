package jcip.c07;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class InterrupteTask {
	private static final BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<>(10);

	public static void main(String[] args) throws InterruptedException {
		Task task = new Task(queue);
		Thread t = new Thread(task);
		t.start();
		TimeUnit.SECONDS.sleep(5);
		t.interrupt();
	}

	public static class Task implements Runnable {
		private final BlockingQueue<BigInteger> queue;

		public Task(BlockingQueue<BigInteger> queue) {
			super();
			this.queue = queue;
		}

		@Override
		public void run() {
			BigInteger p = BigInteger.ONE;
			Thread.currentThread();
			while (!Thread.currentThread().isInterrupted()) {
				p = p.nextProbablePrime();
				System.out.println(Thread.currentThread().isInterrupted()+" task is running。。。"+p);
				try {
					queue.put(p);
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					//TODO 线程退出逻辑，重新设置中断标记
					Thread.currentThread().interrupt();
					System.out.println(Thread.currentThread().getName() + " 中断 "+Thread.currentThread().isInterrupted());
				}
			}
		}

	}
}
