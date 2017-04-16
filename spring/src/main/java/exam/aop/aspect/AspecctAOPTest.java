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
	@Qualifier("watier")
	@Autowired
	Watier waiterAutoProxy;

	/**
	 * 使用BeanNameAutoProxyCreator根据名称自动创建代理，
	 * 避免配置文件中大量的ProxyFactoryBean配置
	 */
	@Test
	public void beanNameAutoProxyCreator() {
		waiterAutoProxy.greetTo("liyan");
		waiterAutoProxy.greetTo("yanli");
		System.out.println(waiterAutoProxy.getClass());

	}

	@Autowired
	@Qualifier("waiterRegexp")
	Watier waiterRegexp;

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
