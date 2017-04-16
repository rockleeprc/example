package exam.aop.aspect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import exam.aop.aspect.target.Seller;
import exam.aop.aspect.target.Watier;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:aop/applicationContext-advisor.xml")
public class AspecctAOPTest {

	@Autowired
	@Qualifier("waiterProxy")
	Watier waiter;

	@Autowired
	@Qualifier("sellerProxy")
	Seller seller;

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
