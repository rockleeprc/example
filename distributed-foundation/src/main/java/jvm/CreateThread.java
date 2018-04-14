package jvm;

public class CreateThread {
	public static void main(String[] args) {
		int i=0;
		while (true) {
			new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(10000000);
					} catch (InterruptedException e) {
					}
				}
			}).start();
			System.out.println(i);
			i++;
		}
	}
}
