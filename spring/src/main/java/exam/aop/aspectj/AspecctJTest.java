package exam.aop.aspectj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import exam.aop.aspectj.aspect.GreetingBeforeAspect;
import exam.aop.interfaces.IWaiter;
import exam.aop.interfaces.impl.NaiveWaiter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:aop/applicationContext-aspectj.xml")
public class AspecctJTest {

	@Qualifier("waiter")
	@Autowired
	IWaiter waiter;

	@Test
	public void aspectJProxy4Config() {

		// waiter.greetTo("jack");
//		 waiter.greetTo("tom");
		waiter.serveTo("marry");
	}

	/**
	 * 硬编码实现AspectJ
	 */
	@Test
	public void aspectJProxy() {
		IWaiter waiter = new NaiveWaiter();
		AspectJProxyFactory factory = new AspectJProxyFactory();
		factory.setTarget(waiter);
		factory.addAspect(GreetingBeforeAspect.class);
		IWaiter proxy = factory.getProxy();
		proxy.greetTo("jack");
		proxy.greetTo("tom");
		proxy.serveTo("marry");
	}
}
