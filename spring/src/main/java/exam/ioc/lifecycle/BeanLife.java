package exam.ioc.lifecycle;

public class BeanLife {

	public BeanLife() {
		super();
		System.out.println("exam.ioc.beanlife.BeanLife.BeanLife()");
	}

	public void init() {
		System.out.println("exam.ioc.beanlife.BeanLife.init()");
	}

	public void destroy() {
		System.out.println("exam.ioc.beanlife.BeanLife.destroy()");
	}

}
