package exam.ioc.interfaces.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import exam.ioc.interfaces.LifeCycle;

public class LifeCycleImpl implements LifeCycle, ApplicationContextAware,  InitializingBean {

	public void init() {
		System.out.println("init");
	}

	public void destory() {
		System.out.println("destory");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("ApplicationContextAware");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean");
	}

	@Override
	public void m() {
		System.out.println("m");
	}


}
