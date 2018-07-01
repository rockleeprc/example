package exam.ioc.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class JSR250Life {

	public JSR250Life() {
		super();
		System.out.println("exam.ioc.beanlife.JSR250Life.JSR250Life()");
	}

	@PostConstruct
	public void init() {
		System.out.println("exam.ioc.beanlife.JSR250Life.init()");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("exam.ioc.beanlife.JSR250Life.destroy()");
	}

}
