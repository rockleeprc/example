package exam.ioc.executor;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExecutorTest {

	@Test
	public void testExecut() throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);

		AsyncTaskService service = context.getBean(AsyncTaskService.class);
		for (int i = 0; i < 10; i++) {
			service.executTask(i);
			service.executTaskPlus(i);
		}

		context.close();
		System.in.read();
	}
}
