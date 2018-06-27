package exam.ioc.scope;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScopeTest {
	@Test
	public void testSingleton() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);
		SingletonScope b1 = context.getBean(SingletonScope.class);
		SingletonScope b2 = context.getBean(SingletonScope.class);
		System.out.println(b1);
		System.out.println(b2);
		context.close();
	}

	@Test
	public void testPrototype() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);
		PrototypeScope b1 = context.getBean(PrototypeScope.class);
		PrototypeScope b2 = context.getBean(PrototypeScope.class);
		System.out.println(b1);
		System.out.println(b2);
		context.close();
	}

}
