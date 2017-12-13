package exam.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// execute();
		submit();
	}

	public static void trace() {
		ThreadPoolExecutor threadPool = new TraceThreadPoolExecutor(10, 10);
		for (int i = 0; i < 5; i++) {
			threadPool.execute(new DivTask(100, i));
		}
		threadPool.shutdown();
	}

	/**
	 * 有异常信息提示
	 */
	public static void execute() {
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, Integer.MAX_VALUE, 0, TimeUnit.MILLISECONDS,
				new SynchronousQueue<Runnable>());
		for (int i = 0; i < 5; i++) {
			threadPool.execute(new DivTask(100, i));
		}
		threadPool.shutdown();
	}

	/**
	 * submit 提交不会有任何异常提示
	 * 
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static void submit() throws InterruptedException, ExecutionException {
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, Integer.MAX_VALUE, 0, TimeUnit.MILLISECONDS,
				new SynchronousQueue<Runnable>());
		for (int i = 0; i < 5; i++) {
			Future<?> submit = threadPool.submit(new DivTask(100, i));
			System.out.println(submit.get());
		}
		threadPool.shutdown();
	}
}
