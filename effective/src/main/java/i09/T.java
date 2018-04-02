package i09;

import org.junit.Test;

public class T {
	

	@Test
	public void m2() {
		PhoneNumber p1 = new PhoneNumber((short) 1, (short) 2, (short) 3);
		System.out.println(p1.hashCode());
		System.out.println(p1.hashCode());
	}

	@Test
	public void m1() {
		PhoneNumber p1 = new PhoneNumber((short) 1, (short) 2, (short) 3);
		PhoneNumber p2 = new PhoneNumber((short) 1, (short) 2, (short) 3);
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
	}
}
