package exam.ioc.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class LifeCycleBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("LifeCycleBeanFactoryPostProcessor");
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("lifeCycleImpl");
		beanDefinition.getPropertyValues().add("name", "A");
	}

}
