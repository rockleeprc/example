package exam.syn;

/**
 * synchronized method()
 * 
 * @author Administrator
 *
 */
public class Syn4 {
	private static class Task implements Runnable {
		private int count = 10;

		@Override
		public void run() {
			m();
		}

		/**
		 * 等同于synchronized(this)，锁定当前对象
		 */
		public synchronized void m() {
			count--;
			System.out.println(Thread.currentThread().getName() + "," + count);
		}
	}

	public static void main(String[] args) {
		Task task = new Task();
		for (int i = 0; i < 5; i++) {
			new Thread(task, "t" + i).start();
		}
	}
}
