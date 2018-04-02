package exam.syn;

public class AccountingVol implements Runnable {
	static AccountingVol instance = new AccountingVol();
	static volatile int i = 0;

	@Override
	public void run() {
		for (int j = 0; j < 10000000; j++) {
			i++;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(instance);
		Thread t2 = new Thread(instance);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		//i值大部分输出都会小于10000000
		System.out.println(i);
	}
}
