package i03;

import org.junit.Test;

public class SingletonTest {
	
	@Test
	public void singleton3() {
		Singleton3 s1 = Singleton3.INSTANCE;
		Singleton3 s2 = Singleton3.INSTANCE;
		s1.method();
		s2.method();
		System.out.println(s1 == s2);
	}

	@Test
	public void singleton2() {
		Singleton2 s1 = Singleton2.getInstance();
		Singleton2 s2 = Singleton2.getInstance();
		s1.method();
		s2.method();
		System.out.println(s1 == s2);
	}

	@Test
	public void singleton1() {
		Singleton1 s1 = Singleton1.INSTACE;
		Singleton1 s2 = Singleton1.INSTACE;
		s1.method();
		s2.method();
		System.out.println(s1 == s2);
	}
}
