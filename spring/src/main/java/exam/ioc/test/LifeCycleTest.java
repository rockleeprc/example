package exam.ioc.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import exam.ioc.interfaces.LifeCycle;

public class LifeCycleTest {

	@Test
	public void test() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:ioc/applicationContext-lifeCycle.xml");
		LifeCycle lc = (LifeCycle) applicationContext.getBean("lifeCycleImpl");
		System.out.println(lc);
		applicationContext.close();

	}

}
