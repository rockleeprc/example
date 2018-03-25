package exam.ioc.test;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import exam.pojo.Person;

public class IoCTest {
	@Test
	public void testResourcePatternResolver() throws IOException {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("classpath:ioc/applicationContext-beanFactory.xml");
		for (Resource resource : resources) {
			System.out.println(resource.getDescription());
		}

	}

	@SuppressWarnings("resource")
	@Test
	public void testFileSystemXmlApplicationContext() {
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"src/main/resources/ioc/applicationContext-beanFactory.xml");
		Person p = (Person) applicationContext.getBean("person");
		System.out.println(p);

	}

	@Test
	public void testBeanFactory() {
		// 配置文件被抽象为Resource
		ClassPathResource res = new ClassPathResource("ioc/applicationContext-beanFactory.xml");
		// DefaultListableBeanFactory是一个默认的功能完整的IOC容器实现
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		// 读取配置文件
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(res);

		Person p1 = (Person) factory.getBean("person");
		Person p2 = (Person) factory.getBean(Person.class);
		// Person p3 = (Person) factory.getBean("person", "a", 10);

		System.out.println(p1);
		System.out.println(p2);
		// System.out.println(p3);

		System.out.println(factory.isSingleton("person"));
		System.out.println(factory.isPrototype("person"));
		System.out.println(factory.isTypeMatch("person", Person.class));
		System.out.println(factory.getType("person"));
		System.out.println(factory.containsBean("person"));
	}

	/**
	 * 读取配置文件
	 */
	@Test
	public void readProperties() {
		DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
		BeanFactory container = (BeanFactory) bindViaPropertiesFile(beanRegistry);
		Person person = (Person) container.getBean("person");
		System.out.println(person);
	}

	public BeanFactory bindViaPropertiesFile(BeanDefinitionRegistry registry) {
		PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(registry);
		reader.loadBeanDefinitions("classpath:ioc/applicationContext.properties");
		return (BeanFactory) registry;
	}

	/**
	 * 读取xml配置
	 */
	@Test
	public void readXML() {
		DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
		BeanFactory container = (BeanFactory) bindViaXMLFile(beanRegistry);

		Person p = (Person) container.getBean("person");
		System.out.println(p);
	}

	public BeanFactory bindViaXMLFile(BeanDefinitionRegistry registry) {
		// 通过BeanDefinitionReader读取方式
		// XmlBeanDefinitionReader负责读取Spring指定格式的XML配置文件并解析，之后将解析后的文件内容映射到相应的BeanDefinition，并加载到相应的BeanDefinitionRegistry中
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
		reader.loadBeanDefinitions("classpath:ioc/applicationContext-beanFactory.xml");
		return (BeanFactory) registry;
		// 通过XmlBeanFactory方式
		// return new XmlBeanFactory(new
		// ClassPathResource("ioc/applicationContext-beanFactory.xml"));

	}

	/**
	 * 直接编码方式
	 */
	@Test
	public void hardCode() {
		DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
		BeanFactory container = (BeanFactory) bindViaCode(beanRegistry);
		Person person = (Person) container.getBean("person");
		System.out.println(person);
	}

	public BeanFactory bindViaCode(BeanDefinitionRegistry registry) {
		AbstractBeanDefinition person = new RootBeanDefinition(Person.class, true);
		// 将bean定义注册到容器中
		registry.registerBeanDefinition("person", person);

		// 指定依赖关系
		// 1. 可以通过构造方法注入方式
		ConstructorArgumentValues argValues = new ConstructorArgumentValues();
		argValues.addIndexedArgumentValue(0, "liyan");
		argValues.addIndexedArgumentValue(1, 18);
		person.setConstructorArgumentValues(argValues);

		// 2. 或者通过setter方法注入方式
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("name", "liyan"));
		propertyValues.addPropertyValue(new PropertyValue("age", 18));
		person.setPropertyValues(propertyValues);
		// 绑定完成
		return (BeanFactory) registry;
	}

}
