package exam.syn;

/**
 * synchronized(this)
 * 
 * @author Administrator
 *
 */
public class Syn3 {
	private static class Task implements Runnable {
		private int count = 10;

		@Override
		public void run() {
			// 锁定的是this对象
			synchronized (this) {
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
