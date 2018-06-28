package exam.ioc.event;

import org.springframework.context.ApplicationEvent;

public class FooEvent extends ApplicationEvent {

	private static final long serialVersionUID = 3557853369632007731L;

	public FooEvent(Object source) {
		super(source);
	}



}
