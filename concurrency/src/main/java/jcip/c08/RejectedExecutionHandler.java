package jcip.c08;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectedExecutionHandler {
	
	//固定大小线程池,有界队列
	ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 100, 0L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>(50));
	
}
