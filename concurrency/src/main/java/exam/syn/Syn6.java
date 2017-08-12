package exam.syn;

/**
 * synchronized锁是可重入的，重入锁可以发生在同一个类中，也可以在子父类中，子类调用父类的方法
 * 
 * @author Administrator
 *
 */
public class Syn6 {

	public synchronized void m1() {
		// 不需要获取m2()的锁，当m1()持有锁时，直接可以调用m2()
		m2();
	}

	public synchronized void m2() {

	}
}
