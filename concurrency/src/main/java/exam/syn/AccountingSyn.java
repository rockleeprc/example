package exam.syn;

public class AccountingSyn implements Runnable {
	static AccountingSyn instance = new AccountingSyn();
	static volatile int i = 0;

	@Override
	public void run() {
		for (int j = 0; j < 10000000; j++) {
			synchronized (instance) {
				i++;
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		//两个线程都必须指向同一个Runnable接口实力，线程才能关注到同一个对象锁上
		Thread t1 = new Thread(instance);
		Thread t2 = new Thread(instance);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}
