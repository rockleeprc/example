package exam.syn;

import java.util.concurrent.TimeUnit;

/**
 * 对写方法加锁，读方法不加锁，容易产生数据的脏读
 * 
 * @author Administrator
 *
 */
public class Account {
	private String name;
	private double balance;

	public synchronized void set(String name, double balance) {
		this.name = name;
		// 模拟程序在执行过程中可能在此处出现的其它操作
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public /* synchronized */ double getBalance() {
		return balance;
	}

	public static void main(String[] args) {
		Account a = new Account();
		new Thread(new Runnable() {
			@Override
			public void run() {
				a.set("lisi", 100);
			}
		}).start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(a.getBalance());

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(a.getBalance());
	}
}
