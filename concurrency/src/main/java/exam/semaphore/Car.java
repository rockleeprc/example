package exam.semaphore;

import java.util.concurrent.Semaphore;

public class Car implements Runnable {

	private final Semaphore parkingSlot;
	private int carNo;

	/**
	 * @param parkingSlot
	 * @param carName
	 */
	public Car(Semaphore parkingSlot, int carNo) {
		this.parkingSlot = parkingSlot;
		this.carNo = carNo;
	}

	public void run() {
		try {
			parking();
			sleep(300);
			leaving();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void parking() throws InterruptedException {
		parkingSlot.acquire();
		System.out.println(String.format("%d号车泊车", carNo));
	}

	private void leaving() {
		parkingSlot.release();
		System.out.println(String.format("%d号车离开车位", carNo));
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}