package exam.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.Test;

public class ArrayBlockingQueueTest {

	/**
	 * 如果试图的操作无法立即执行，抛一个异常
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testAdd() throws InterruptedException {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(2);
		for (int i = 0; i < 5; i++) {
			queue.add(i);
			System.out.println(i);
		}
	}

	/**
	 * 如果试图的操作无法立即执行，该方法调用将会发生阻塞，直到能够执行
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testPut() throws InterruptedException {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(2);
		for (int i = 0; i < 5; i++) {
			System.out.println("peek:"+queue.peek());
			queue.put(i);
			System.out.println(i);
		}
	}

	/**
	 * 如果试图的操作无法立即执行，该方法调用将会发生阻塞，直到能够执行
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testTake() throws InterruptedException {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(2);
		queue.take();
	}
}
