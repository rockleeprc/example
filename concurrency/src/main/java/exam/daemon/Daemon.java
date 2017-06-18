package exam.daemon;

public class Daemon {
	public static class DaemonThread extends Thread {
		@Override
		public void run() {
			while (true) {
				System.out.println(" i am alive");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
public static void main(String[] args) throws InterruptedException {
	Thread t = new DaemonThread();
	t.start();
	t.setDaemon(true);
	Thread.sleep(2000);
	//程序中只有main线程和t线程为守护线程，main线程2s后退出，整个Java程序也会随之退出
}
}
