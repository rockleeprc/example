package exam.wait;

public class WaitNotify {
	public static Object obj = new Object();

	static class T1 extends Thread {

		@Override
		public void run() {
			synchronized (obj) {
				System.out.println(System.currentTimeMillis() + " t1 start");
				try {
					obj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//T2休眠后不释放锁，等待2秒后输出
				System.out.println(System.currentTimeMillis() + " t1 end");
			}
		}

	}

	static class T2 extends Thread {
		@Override
		public void run() {
			synchronized (obj) {
				System.out.println(System.currentTimeMillis() + " t2 start");
				obj.notify();
				System.out.println(System.currentTimeMillis() + " t2 end");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
public static void main(String[] args) {
	Thread t1 = new T1();
	Thread t2 = new T2();
	t1.start();
	t2.start();
}
}
