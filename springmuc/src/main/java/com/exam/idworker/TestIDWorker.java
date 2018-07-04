package com.exam.idworker;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestIDWorker {

	// ==============================Test=============================================
	/** 测试 
	 * @throws InterruptedException */
	public static void main(String[] args) throws InterruptedException {
		t2();
	}

	public static void t2() throws InterruptedException {
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		CountDownLatch countDownLatch = new CountDownLatch(100);
		for (int i = 0; i < 100; i++) {
			System.out.println(i);
			threadPool.execute(new Task(countDownLatch));
		}
		System.out.println("final");
		countDownLatch.await();
		threadPool.shutdown();
	}

	public static class Task implements Runnable {
		CountDownLatch countDownLatch;

		public Task(CountDownLatch countDownLatch) {
			super();
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			long id = IDWorker.nextId();
			System.out.println(Long.toBinaryString(id));
			System.out.println(id);
			countDownLatch.countDown();
		}

	}

	public static void t1() {
		SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
		for (int i = 0; i < 1000; i++) {
			long id = idWorker.nextId();
			System.out.println(Long.toBinaryString(id));
			System.out.println(id);
		}
	}

}
