package com.tadu.main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.tadu.constant.FilePathConsts;
import com.tadu.io.FileParser;
import com.tadu.task.TraceThreadPoolExecutor;

public class YWMain {
	private static final TraceThreadPoolExecutor threadPool = TraceThreadPoolExecutor.newInstance(20, 30);

	public static void main(String[] args) throws IOException, InterruptedException {
		FileParser preparation = FileParser.newInstance(new File(FilePathConsts.YW_INPUT_FILE_PATH));
		List<String> list = preparation.prepareYW();
		CountDownLatch countDown = new CountDownLatch(list.size());
		for (int i = 0, len = list.size(); i < len; i++) {
			String id = list.get(i);
			System.out.println("yw_id="+id);
			threadPool.execute(new YWCallable(id));
			countDown.countDown();
		}

		countDown.await();
		threadPool.shutdown();
	}
}
