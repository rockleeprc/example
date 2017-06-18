package exam.group;

import java.util.concurrent.TimeUnit;

public class ThreadGroupName implements Runnable {
	public static void main(String[] args) {
		ThreadGroup tg = new ThreadGroup("PringGroup");
		Thread t1= new Thread(tg,new ThreadGroupName(),"t1");
		Thread t2= new Thread(tg,new ThreadGroupName(),"t2");
		t1.start();
		t2.start();
		//活动线程数
		System.out.println(tg.activeCount());
		//线程组中所有线程信息
		tg.list();
	}

	@Override
	public void run() {
		String groupAndName = Thread.currentThread().getThreadGroup().getName() + "-"
				+ Thread.currentThread().getName();
		
		while(true){
			System.out.println("I am "+ groupAndName);
			try {
				Thread.sleep(TimeUnit.MINUTES.toMillis(3));
			} catch (InterruptedException e) {
				e.printStackTrace();
			};
		}

	}

}
