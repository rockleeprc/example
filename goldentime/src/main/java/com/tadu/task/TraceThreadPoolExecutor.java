package com.tadu.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

	private TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize) {
		this(corePoolSize, maximumPoolSize, 0L, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>());
	}

	private TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	
	public static TraceThreadPoolExecutor newInstance(int corePoolSize, int maximumPoolSize){
		return new TraceThreadPoolExecutor(corePoolSize, maximumPoolSize);
	}

	@Override
	public void execute(Runnable command) {
		super.execute(wrap(command, clientTrace(), Thread.currentThread().getName()));
	}

	private Runnable wrap(Runnable task, Exception clientStack, String threadName) {

		return new Runnable() {

			@Override
			public void run() {
				try {
					task.run();
				} catch (Exception e) {
					clientStack.printStackTrace();
					throw e;
				}
			}
		};
	}

	private Exception clientTrace() {
		return new Exception("Client statck trace");
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
//		System.out.println("准备执行：" + Thread.currentThread().getName());
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
//		System.out.println("执行完成：" + Thread.currentThread().getName());
	}

	@Override
	protected void terminated() {
		System.out.println("线程池退出");
	}

}
