package exam.threadpool;

import java.util.concurrent.SynchronousQueue;

import org.junit.Test;

public class CachedThreadPool {

	
	@Test
	public void testSyn() throws InterruptedException{
		SynchronousQueue<Integer> queue = new SynchronousQueue<>();
		System.out.println(queue.offer(1));
	}
}
