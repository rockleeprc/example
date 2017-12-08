package exam.barrier;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Barraier {

	private static class Solder implements Runnable {

		private CyclicBarrier cb;
		private String name;

		public Solder(CyclicBarrier cb, String name) {
			this.cb = cb;
			this.name = name;
		}

		@Override
		public void run() {
			try {
				System.out.println("solder 报道： " + name);
				cb.await();
				System.out.println("执行任务");
				doJob();
				cb.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}

		private void doJob() {
			System.out.println("solder 执行任务： " + name);
			try {
				int i = Math.abs(new Random(5).nextInt()%10000);
				System.out.println("sleep = "+i);
				Thread.sleep(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws IOException {
		CyclicBarrier cb = new CyclicBarrier(10);
		Thread[] solders = new Thread[15];
		for(int i=0;i<15;i++){
			solders[i] = new Thread(new Solder(cb, "solder["+i+"]"));
			solders[i].start();
		}
		System.in.read();
	}
}
