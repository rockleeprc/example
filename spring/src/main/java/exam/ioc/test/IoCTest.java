package exam.ioc.test;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
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

}
