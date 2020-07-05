package exam.aop.aspect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import exam.aop.interfaces.IWaiter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:aop/applicationContext-advisor-autoproxy.xml")
public class AspectAutoProxyAOPTest {

	@Qualifier("watier")
	@Autowired
	IWaiter waiterAutoProxy;

	/**
	 * 根据配置文件定义两种自动代理
	 * 	BeanNameAutoProxyCreator
	 * 	DefaultAdvisorAutoProxyCreator
	 */
	@Test
	public void autoProxyCreator() {
		waiterAutoProxy.greetTo("liyan");
		waiterAutoProxy.greetTo("yanli");
		waiterAutoProxy.serveTo("jack");
		System.out.println(waiterAutoProxy.getClass());

	}
}
