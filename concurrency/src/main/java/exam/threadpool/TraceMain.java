package exam.threadpool;

import java.util.concurrent.ThreadPoolExecutor;

public class TraceMain {
	static class DivTask implements Runnable {
		int a, b;

		public DivTask(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public void run() {
			double result = a / b;
			System.out.println(result);
		}
	}

	public static void main(String[] args) {
		ThreadPoolExecutor threadPool = new TraceThreadPoolExecutor(10, 10);
		for (int i = 0; i < 5; i++) {
			threadPool.execute(new DivTask(100, i));
		}
		threadPool.shutdown();
	}
}
