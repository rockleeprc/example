package jcip.c06;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceTest {
	private static final ExecutorService threadPool = Executors.newFixedThreadPool(10);

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletionService<String> cs = new ExecutorCompletionService<String>(threadPool);
		for (int i = 0; i < 100; i++) {
			cs.submit(new Index(String.valueOf(i)));
		}
		for (int i = 0; i < 100; i++) {
			Future<String> future = cs.take();
			String result = future.get();
			System.out.println("i="+result);
		}
	}

	static class Index implements Callable<String> {
		private String idx;

		public Index(String idx) {
			this.idx = idx;
		}

		@Override
		public String call() throws Exception {
//			System.out.println("Index=" + idx);
			return idx;
		}

	}

}
