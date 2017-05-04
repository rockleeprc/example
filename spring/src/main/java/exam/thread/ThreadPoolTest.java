package exam.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:thread/applicationContext-threadpool.xml")
public class ThreadPoolTest {
//	@Qualifier("threadPool")
//	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	/**
	 * Spring线程池结合非Spring Bean。
	 */
	@Test
	public void testPool2() {
		taskExecutor.execute(new PrintTask("Thread 1"));
		taskExecutor.execute(new PrintTask("Thread 2"));
		taskExecutor.execute(new PrintTask("Thread 3"));
		taskExecutor.execute(new PrintTask("Thread 4"));
		taskExecutor.execute(new PrintTask("Thread 5"));
		while (true) {
			int count = taskExecutor.getActiveCount();
			System.out.println("Active Threads : " + count);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (count == 0) {
				taskExecutor.shutdown();
				break;
			}
		}
	}

	@Autowired
	PrintThread pt1;
	@Autowired
	PrintThread pt2;
	@Autowired
	PrintThread pt3;
	@Autowired
	PrintThread pt4;
	@Autowired
	PrintThread pt5;

	/**
	 * Thread Bean由Spring容器托管
	 */
	@Test
	public void testPool1() {
		pt1.setName("pt1");
		pt2.setName("pt2");
		pt3.setName("pt3");
		pt4.setName("pt4");
		pt5.setName("pt5");
		
		pt1.start();
		pt2.start();
		pt3.start();
		pt4.start();
		pt5.start();
	}

}
