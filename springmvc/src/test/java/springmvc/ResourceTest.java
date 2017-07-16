package springmvc;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ResourceTest {

	@Test
	public void test() {
		String[] config = { "applicationContext-mvc.xml" };
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(config);
		MessageSource ms = (MessageSource) ctx.getBean("messageSource");
		Object[] params = new Object[]{};
		String name = ms.getMessage("Pattern.user.name",params,Locale.CHINA );
		System.out.println(name);
	}
}
