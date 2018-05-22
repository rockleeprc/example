package exam.ioc.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import exam.pojo.Person;

public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof Person) {
			Person p = (Person) bean;
			System.out.println("Before:" + p);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof Person) {
			Person p = (Person) bean;
			System.out.println("After:" + p);
		}
		return bean;
	}

}
