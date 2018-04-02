package exam.ioc.test;

import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import exam.ioc.interfaces.impl.FooImpl;

@SuppressWarnings("deprecation")
public class FactoryBeanTest {

	@Test
	public void testFactoryBean() {
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("ioc/applicationContext-factoryBean.xml"));
		FooImpl foo = (FooImpl) factory.getBean("fooImpl");
		foo.method();
		System.out.println(foo.getBar());
	}
}
