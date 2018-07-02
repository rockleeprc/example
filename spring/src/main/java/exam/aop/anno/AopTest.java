package exam.aop.anno;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {

	@Test
	public void t() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);

		AnnotationService aService = context.getBean(AnnotationService.class);
		aService.add();

		MethodService mService = context.getBean(MethodService.class);
		mService.add();

		context.close();
	}
}
