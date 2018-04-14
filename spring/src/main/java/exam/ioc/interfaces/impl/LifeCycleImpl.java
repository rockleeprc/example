package exam.ioc.interfaces.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import exam.ioc.interfaces.LifeCycle;

public class LifeCycleImpl implements LifeCycle, BeanFactoryAware, BeanNameAware, ApplicationContextAware,
		InitializingBean, DisposableBean {

	private String name;

	public void init() {
		System.out.println("init");
	}

	public void destroyMethod() {
		System.out.println("destroyMethod");
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

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("BeanFactoryAware.setBeanFactory()");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("BeanNameAware.setBeanName()-" + name);
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("DisposableBean.destroy()");

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
