package exam.ioc.profile;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProfileTest {
	@Test
	public void testProfile() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("prod");
		context.register(ProfileConfig.class);
		context.refresh();

		InfoBean bean = context.getBean(InfoBean.class);
		System.out.println(bean.getInfo());
		context.close();
	}
	@Test
	public void testProfileException() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProfileConfig.class);
		context.getEnvironment().setActiveProfiles("dev");
		
		InfoBean bean = context.getBean(InfoBean.class);
		System.out.println("--"+bean.getInfo());
		context.close();
	}

}
