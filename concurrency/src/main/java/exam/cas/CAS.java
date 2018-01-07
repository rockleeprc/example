package exam.cas;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

import org.junit.Test;

public class CAS {
	private volatile long count = 0;
	private static final AtomicLongFieldUpdater<CAS> fieldUpdater = AtomicLongFieldUpdater.newUpdater(CAS.class,
			"count");

	@Test
	public void t1() {
		CAS c = new CAS();
		boolean flag = c.compareAndSwap(0, 10);
		System.out.println(flag + "-" + c.count);
		flag = c.compareAndSwap(10, 20);
		System.out.println(flag + "-" + c.count);
	}

	private boolean compareAndSwap(long oldValue, long newValue) {
		boolean isOK = fieldUpdater.compareAndSet(this, oldValue, newValue);
		return isOK;
	}
}
