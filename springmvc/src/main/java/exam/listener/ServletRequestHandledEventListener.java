package exam.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

@Component
public class ServletRequestHandledEventListener implements ApplicationListener<ServletRequestHandledEvent> {

	@Override
	public void onApplicationEvent(ServletRequestHandledEvent event) {
		System.out.println("-->"+event.getDescription());
	}

}
