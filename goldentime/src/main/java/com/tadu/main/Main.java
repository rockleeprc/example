package com.tadu.main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.tadu.bean.InputData;
import com.tadu.constant.FilePathConsts;
import com.tadu.io.FileParser;
import com.tadu.task.QQTaskCallable;
import com.tadu.task.QQTaskRunable;
import com.tadu.task.ThreeGCallable;
import com.tadu.task.TraceThreadPoolExecutor;
import com.tadu.util.FileHelper;

public class Main {
	private static final TraceThreadPoolExecutor threadPool = TraceThreadPoolExecutor.newInstance(20, 30);

	static {
		File out = new File(FilePathConsts.G3_OUTPUT_FILE_PATH);
		if (out.exists()) {
			out.delete();
		}
		File fail = new File(FilePathConsts.G3_OUTPUT_FAIL_PATH);
		if (fail.exists()) {
			out.delete();
		}
	}

	public static void main(String[] args) throws Exception {
		
	}

	/**
	 * 3G 抓取
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void g3Callable() throws IOException, InterruptedException, ExecutionException {
		FileParser operator = FileParser.newInstance(new File(FilePathConsts.G3_INPUT_FILE_PATH));
		List<InputData> list = operator.prepare3G();

		CountDownLatch countDown = new CountDownLatch(list.size());
		for (int i = 0, len = list.size(); i < len; i++) {
			InputData data = list.get(i);
			Future<String> feutre = threadPool.submit(new ThreeGCallable(data.getOtherId()));
			System.out.println(i + "," + data.getOtherId());
			FileHelper.writeLine(FilePathConsts.G3_OUTPUT_FILE_PATH, feutre.get());
			countDown.countDown();
		}

		countDown.await();
		threadPool.shutdown();

	}

	/**
	 * 阅文Runnable抓取
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ExecutionException
	 */
	public static void qqRunnable() throws InterruptedException, IOException, ExecutionException {
		FileParser operator = FileParser.newInstance(new File(FilePathConsts.QQ_INPUT_FILE_PATH));
		List<InputData> list = operator.prepareQQ("	");
		System.out.println(list.size());

		CountDownLatch countDown = new CountDownLatch(list.size());
		for (int i = 0, len = list.size(); i < len; i++) {
			InputData data = list.get(i);
			threadPool.submit(new QQTaskRunable(data.getTaduId(), data.getOtherId()));
			System.out.println(i + "," + data.getTaduId());
			countDown.countDown();
		}

		countDown.await();
		threadPool.shutdown();
	}

	/**
	 * 阅文callable抓取
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void qqCallable() throws IOException, InterruptedException, ExecutionException {
		FileParser operator = FileParser.newInstance(new File(FilePathConsts.QQ_INPUT_FILE_PATH));
		List<InputData> list = operator.prepareQQ("	");
		System.out.println(list.size());

		CountDownLatch countDown = new CountDownLatch(list.size());
		for (int i = 0, len = list.size(); i < len; i++) {
			InputData data = list.get(i);
			Future<String> futrue = threadPool.submit(new QQTaskCallable(data.getOtherId()));
			String result = futrue.get();
			System.out.println(i + "," + data.getTaduId());

			if ("false".equals(result)) {
				FileHelper.writeLine(FilePathConsts.QQ_OUTPUT_FAIL_PATH, data.getTaduId() + "," + data.getOtherId());
			} else {
				FileHelper.writeLine(FilePathConsts.QQ_OUTPUT_FILE_PATH, data.getTaduId() + "," + result);
			}
			countDown.countDown();
		}

		countDown.await();
		threadPool.shutdown();
	}

}
