package exam.ioc.executor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Async
public class AsyncTaskService {

	public void executTask(int i) {
		System.out.println("executTask i = " + i);
	}

	public void executTaskPlus(int i) {
		System.out.println("executTaskPlus i =" + (i + 1));
	}

}
