package jcip.c08;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class TimingThreadPool extends ThreadPoolExecutor {
	public TimingThreadPool() {
		super(10, 10, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	}

	private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	private final Logger log = Logger.getLogger("TimingThreadPool");
	private final AtomicLong numTasks = new AtomicLong();
	private final AtomicLong totalTime = new AtomicLong();

	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		log.info(String.format("Thread %s: start %s", t, r));
		startTime.set(System.nanoTime());
	}

	protected void afterExecute(Runnable r, Throwable t) {
		try {
			long endTime = System.nanoTime();
			long taskTime = endTime - startTime.get();
			numTasks.incrementAndGet();
			totalTime.addAndGet(taskTime);
			log.info(String.format("Thread %s: end %s, time=%dns", t, r, taskTime));
		} finally {
			super.afterExecute(r, t);
		}
	}

	protected void terminated() {
		try {
			log.info(String.format("Terminated: avg time=%dns", totalTime.get() / numTasks.get()));
		} finally {
			super.terminated();
		}
	}

	public static void main(String[] args) {
		TimingThreadPool pool = new TimingThreadPool();
		for (int i = 0; i < 2; i++) {
			pool.submit(new Runnable() {

				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		pool.shutdown();
	}
}
