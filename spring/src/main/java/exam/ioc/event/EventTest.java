package exam.ioc.event;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EventTest {

	@Test
	public void testEvent() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
		MyPublisher myPublisher = context.getBean(MyPublisher.class);
		myPublisher.publisher("hello event");
		context.close();
	}
}
