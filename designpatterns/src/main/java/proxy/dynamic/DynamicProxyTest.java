package proxy.dynamic;

import org.junit.Test;

import proxy.interfaces.IBusiness;
import proxy.interfaces.impl.BusinessImpl;

public class DynamicProxyTest {
	@Test
	public void test() {
		final IBusiness target = new BusinessImpl();
		IBusiness proxy = (IBusiness) new MyInvocationHandler(target).getProxy();
		proxy.doSomething();
	}
}
