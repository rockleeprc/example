package exam.aop.aspect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import exam.aop.interfaces.IWaiter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:aop/applicationContext-advisor.xml")
public class AspectAOPTest {

	@Autowired
	@Qualifier("waiterRegexp")
	IWaiter waiterRegexp;

	/**
	 * 正则匹配切面
	 */
	@Test
	public void regexpAspect() {
		waiterRegexp.greetTo("tom");
		waiterRegexp.greetTo("marry");
		waiterRegexp.serveTo("jack");
	}

	@Autowired
	@Qualifier("waiterProxy")
	IWaiter waiter;

	@Autowired
	@Qualifier("waiterProxy")
	IWaiter seller;

	/**
	 * 静态普通方法签名匹配切面
	 */
	@Test
	public void staticMethodAspect() {
		// ApplicationContext ctx = new
		// ClassPathXmlApplicationContext("classpath:aop/applicationContext-advisor.xml");
		// Watier waiter = (Watier) ctx.getBean("waiterProxy");

		waiter.greetTo("tom");
		waiter.serveTo("jack");
		seller.greetTo("jerry");
	}
}
