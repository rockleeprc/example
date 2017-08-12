package exam.syn;

/**
 * 数据不一致性，count出现错误值
 * 
 * @author Administrator
 *
 */
public class Syn1 {
	private static class Task implements Runnable {
		private int count = 10;

		@Override
		public void run() {
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
