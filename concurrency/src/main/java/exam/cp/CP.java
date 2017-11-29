package exam.cp;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class CP {

	private static final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);
//	private static final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

	public void produce(String str) throws InterruptedException {
		queue.put(str);
		System.out.println("生产：" + str);
	}

	public void consume() throws InterruptedException {
		String take = queue.take();
		System.out.println("消费：" + take);
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		CP cp = new CP();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					try {
						TimeUnit.SECONDS.sleep(1);
						cp.produce("data-" + i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					try {
						cp.consume();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		System.in.read();
	}

}
