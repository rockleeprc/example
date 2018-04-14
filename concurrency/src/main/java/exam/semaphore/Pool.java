package exam.semaphore;

import java.util.concurrent.Semaphore;

public class Pool {
	private static final int MAX_AVAILABLE = 10;
	private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

	public Object getItem() throws InterruptedException {
		available.acquire();
		return getNextAvailableItem();
	}

	public void putItem(Object x) {
		if (markAsUnused(x))
			available.release();
	}

	protected Object[] items = new Object[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
	protected boolean[] used = new boolean[MAX_AVAILABLE];

	protected synchronized Object getNextAvailableItem() {
		for (int i = 0; i < MAX_AVAILABLE; ++i) {
			if (!used[i]) {
				used[i] = true;
				return items[i];
			}
		}
		return null; // not reached
	}

	protected synchronized boolean markAsUnused(Object item) {
		for (int i = 0; i < MAX_AVAILABLE; ++i) {
			if (item == items[i]) {
				if (used[i]) {
					used[i] = false;
					return true;
				} else
					return false;
			}
		}
		return false;
	}

	public static void main(String[] args) throws InterruptedException {
		Pool p = new Pool();
		for (int i = 0; i < 10; i++) {
			System.out.println(p.getItem());
		}
		boolean[] used = p.used;
		for (Object o : used) {
			System.out.println(o);
		}
	}
}
