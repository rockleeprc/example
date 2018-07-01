package exam.ioc.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyPublisher {

	@Autowired
	private ApplicationContext context;

	public void publisher(String info) {
		context.publishEvent(new MyEvent(this, info));
	}

}
