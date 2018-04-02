package proxy.myproxy;

import proxy.interfaces.IBusiness;
import proxy.interfaces.impl.BusinessImpl;

public class MyProxyTest {
	public static void main(String[] args) {

		try {
			final IBusiness target = new BusinessImpl();
			IBusiness proxy = (IBusiness) new BaseInvocationHandlerImpl().getInstance(target);
			System.out.println(proxy.getClass());
			proxy.doSomething();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
