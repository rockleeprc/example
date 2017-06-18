package exam.join;

public class Join {
	public volatile static int i =0;
	
	public  static class T extends Thread{
		@Override
		public void run() {
			for(i=0;i<100000;i++);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new T();
		t.start();
		//阻塞当前线程，直到线程执行完成
		t.join();
		System.out.println(i);
	}
}
