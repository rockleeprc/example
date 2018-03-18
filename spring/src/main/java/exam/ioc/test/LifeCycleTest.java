package exam.ioc.test;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import exam.ioc.interfaces.impl.LifeCycleImpl;
import exam.ioc.lifecycle.LifeCycleInstantiationWareBean;
import exam.ioc.lifecycle.LifeCyclePostProcessor;

/**
 * 
 * @author Administrator
 *
 */
public class LifeCycleTest {

	/**
	 * ApplicationContext利用反射机制自动识别xxxPostProcessor，注册到容器中
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void applicationContextLifeCycle()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:ioc/applicationContext-lifeCycle.xml");
		LifeCycleImpl lc = (LifeCycleImpl) applicationContext.getBean("lifeCycleImpl");
		System.out.println(lc.getName());
		applicationContext.close();

	}

	/**
	 * BeanFactory的生命周期，需要硬编码实现xxxPostProcessor
	 */
	@Test
	public void beanFactoryLifeCycle() {
		Resource rs = new ClassPathResource("ioc/applicationContext-lifeCycle.xml");
		BeanFactory bf = new XmlBeanFactory(rs);
		((ConfigurableBeanFactory) bf).addBeanPostProcessor(new LifeCyclePostProcessor());
		((ConfigurableBeanFactory) bf).addBeanPostProcessor(new LifeCycleInstantiationWareBean());

		LifeCycleImpl lc = (LifeCycleImpl) bf.getBean("lifeCycleImpl");
		System.out.println(lc.getName());

		((XmlBeanFactory) bf).destroySingletons();
	}

}
