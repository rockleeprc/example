package exam.juc;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLikedQueueMain {

	public static void main(String[] args) {
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.offer("String");
		queue.offer("String");
		System.out.println(queue);
		String poll = queue.poll();
		System.out.println(poll);
		System.out.println(queue);
	}

}
