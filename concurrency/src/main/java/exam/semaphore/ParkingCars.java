package exam.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ParkingCars {

	private static final int NUMBER_OF_CARS = 30;
	private static final int NUMBER_OF_PARKING_SLOT = 10;

	public static void main(String[] args) {
		// 采用FIFO, 设置true
		Semaphore parkingSlot = new Semaphore(NUMBER_OF_PARKING_SLOT, true);

		ExecutorService service = Executors.newCachedThreadPool();
		for (int carNo = 1; carNo <= NUMBER_OF_CARS; carNo++) {
			service.execute(new Car(parkingSlot, carNo));
		}

		sleep(3000);
		service.shutdown();

		// 输出还有几个可以用的资源数
		System.out.println(parkingSlot.availablePermits() + " 个停车位可以用!");
	}

	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
/*
Semaphore vs. CountDownLatch 

相同点：   
    两者都是用于线程同步的工具类，都通过定义了一个继承AbstractQueuedSynchronizer的内部类Sync来实现具体的功能. 

不同点： 
1.  Semaphore提供了公平和非公平两种策略, 而CountDownLatch则不具备. 
2.  CountDownLatch： 一个或者是一部分线程，等待另外一部线程都完成操作。 
     Semaphorr: 维护一个许可集.通常用于限制可以访问某些资源（物理或逻辑的）的线程数目. 

3. CountDownLatch中计数是不能被重置的。CountDownLatch适用于一次同步。当使用CountDownLatch时，任何线程允许多次调用countDown(). 那些调用了await()方法的线程将被阻塞，直到那些没有被阻塞线程调用countDown()使计数到达0为止 
。 
Semaphore允许线程获取许可, 未获得许可的线程需要等待.这样防止了在同一时间有太多的线程执行.Semaphore的值被获取到后是可以释放的，并不像CountDownLatch那样一直减到0。 

4. 使用CountDownLatch时，它关注的一个线程或者多个线程需要在其它在一组线程完成操作之后，在去做一些事情。比如：服务的启动等。使用Semaphore时,它关注的是某一个资源最多同时能被几个线程访问. 

*/
