package exam.ioc.aware;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AwareTest {

	@Test
	public void aware() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);
		
		AwareService service = context.getBean(AwareService.class);
		service.print();
		
		context.close();
	}
}
