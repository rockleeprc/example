package exam.thread;

public class PrintThread extends Thread {
	@Override
	public void run() {
		System.out.println(getName() + " is running.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getName() + " is running again.");
	}
}
