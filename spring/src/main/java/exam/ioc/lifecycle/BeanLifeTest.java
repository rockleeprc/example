package exam.ioc.lifecycle;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanLifeTest {

	@Test
	public void testBeanLife() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProPostConfig.class);
		BeanLife bean = context.getBean(BeanLife.class);
		context.close();
	}

	@Test
	public void testJSR250Life() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProPostConfig.class);
		JSR250Life bean = context.getBean(JSR250Life.class);
		context.close();
	}

}
