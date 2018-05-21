package exam.ioc.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import exam.pojo.Person;

public class PostProcessorTest {
	@SuppressWarnings("resource")
	@Test
	public void testPostProcessor() {
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"src/main/resources/postprocessor/applicationContext-postprocessor.xml");
		Person p = (Person) applicationContext.getBean("person");
		System.out.println(p);
		p = (Person) applicationContext.getBean("person");
		System.out.println(p);

	}
}
