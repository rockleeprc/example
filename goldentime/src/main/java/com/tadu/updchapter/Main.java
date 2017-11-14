package com.tadu.updchapter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import com.tadu.constant.FilePathConsts;
import com.tadu.io.FileParser;

public class Main {
	private static final int threadPoolSize = Runtime.getRuntime().availableProcessors() + 1;
	private static final ExecutorService threadPool = new ThreadPoolExecutor(threadPoolSize, threadPoolSize * 2,
			60L * 10, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
	private static final CompletionService<String> completionService = new ExecutorCompletionService<String>(
			threadPool);

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		long begin = System.currentTimeMillis();
		doYou();
		long end = System.currentTimeMillis();
		System.out.println((end - begin) / 1000 + "s");

	}

	private static void doYou() throws IOException, InterruptedException, ExecutionException {
		List<InputYWData> parseData = FileParser.newInstance(new File(FilePathConsts.YW_QUERY_BOOK_INFO)).parseYW();
		System.out.println(parseData.size());
		for (int i = 0, len = parseData.size(); i < len; i++) {
			System.out.println("producer=" + i + "/" + len);
			
			InputYWData data = parseData.get(i);
			submitThreadPool(data);
		}
		threadPool.shutdown();
	}

	
	private static void submitThreadPool(InputYWData data)
			throws IOException, InterruptedException, ExecutionException {
		threadPool.execute(new ChapterCallable(data));
	}
	
//	private static void submitThreadPool(InputYWData data)
//			throws IOException, InterruptedException, ExecutionException {
//		Future<String> future = threadPool.submit(new ChapterCallable(data));
//		String result = future.get();
//		FileHelper.writeLine("D:\\yw_chapter.txt", result);
//	}

	@Deprecated
	private static void printThreadPoolInfo(ExecutorService threadPool) {
		ThreadPoolExecutor tpe = (ThreadPoolExecutor) threadPool;
		System.out.println(" getPoolSize=" + tpe.getPoolSize());
		System.out.println(" getTaskCount=" + tpe.getTaskCount());
		System.out.println(" getActiveCount=" + tpe.getActiveCount());
		System.out.println(" getCompletedTaskCount=" + tpe.getCompletedTaskCount());
	}

}
