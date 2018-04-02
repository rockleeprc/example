package exam.syn;

/**
 * static synchronized method()
 * 
 * @author Administrator
 *
 */
public class Syn5 {
	private static class Task implements Runnable {
		private static int count = 10;

		@Override
		public void run() {
			m();
		}

		/**
		 * 等同于synchronized(Syn5.class)，锁定Syn5.class
		 * 静态方法没有synchronized(this)，静态方法不需要实例对象就可以被调用
		 */
		public static synchronized void m() {
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
