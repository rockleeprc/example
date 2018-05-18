package observer;

import org.junit.Test;

public class ObserverTest {

	@Test
	public void t() {
		ConcreteSubject subject = new ConcreteSubject();
		Observer a = new ObserverA(subject);
		Observer b = new ObserverB(subject);
		subject.set(1, "a");
		subject.removeObserver(a);
		subject.set(2, "C");
	}
}
