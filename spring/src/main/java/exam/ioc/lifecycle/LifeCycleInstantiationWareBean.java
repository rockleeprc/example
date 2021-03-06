package exam.ioc.lifecycle;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

public class LifeCycleInstantiationWareBean extends InstantiationAwareBeanPostProcessorAdapter {

	public LifeCycleInstantiationWareBean() {
		super();
		System.out.println("LifeCycleInstantiationWareBean");
	}

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		System.out.println("LifeCycleInstantiationWareBean-" + beanName);
		return super.postProcessBeforeInstantiation(beanClass, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("LifeCycleInstantiationWareBean-" + beanName);
		 return super.postProcessAfterInitialization(bean, beanName);
	}

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean,
			String beanName) throws BeansException {
		System.out.println("LifeCycleInstantiationWareBean-" + beanName);
		return super.postProcessPropertyValues(pvs, pds, bean, beanName);
	}

}
