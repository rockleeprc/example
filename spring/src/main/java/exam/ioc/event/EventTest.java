package exam.ioc.event;

import org.junit.Test;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EventTest {

	@Test
	public void testEvent() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
		MyPublisher myPublisher = context.getBean(MyPublisher.class);
		myPublisher.publisher("hello event");
		myPublisher.publisher("hello event agin");
		context.close();
	}

	@Test
	public void testEvent2() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.addApplicationListener(new ApplicationListener<FooEvent>() {

			@Override
			public void onApplicationEvent(FooEvent event) {
				System.out.println("处理事件：" + event.getSource());
			}
		});
		context.refresh();

		context.publishEvent(new FooEvent("a"));
		context.publishEvent(new FooEvent(1));
		context.publishEvent(new FooEvent(true));

		context.close();
	}
}
