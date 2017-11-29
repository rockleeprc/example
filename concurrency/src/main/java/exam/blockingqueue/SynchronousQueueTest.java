package exam.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import org.junit.Test;

public class SynchronousQueueTest {
	
	@Test
	public void testPut() throws InterruptedException {
		BlockingQueue<Integer> queue = new SynchronousQueue<Integer>();
		for (int i = 0; i < 5; i++) {
			System.out.println(i);
			queue.put(i);
		}
	}
}
