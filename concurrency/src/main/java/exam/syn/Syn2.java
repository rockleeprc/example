package exam.syn;

/**
 * synchronized(Object)
 * 
 * @author Administrator
 *
 */
public class Syn2 {
	private static class Task implements Runnable {
		private int count = 10;
		private Object obj = new Object();

		@Override
		public void run() {
			// 锁定的是obj对象，堆内存的对象，不是栈内存的引用
			synchronized (obj) {
				count--;
				System.out.println(Thread.currentThread().getName() + "," + count);
			}
		}
	}

	public static void main(String[] args) {
		Task task = new Task();
		for (int i = 0; i < 5; i++) {
			new Thread(task, "t" + i).start();
		}
	}
}
