package org.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import org.framework.annotation.Inject;
import org.framework.util.ArrayUtil;
import org.framework.util.CollectionUtil;
import org.framework.util.ReflectionUtil;
import org.smart.controller.CustomerController;

/**
 * 依赖注入助手类
 *
 * @author huangyong
 * @since 1.0.0
 */
public final class IocHelper {

	static {
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		if (CollectionUtil.isNotEmpty(beanMap)) {
			for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				Field[] beanFields = beanClass.getDeclaredFields();
				if (ArrayUtil.isNotEmpty(beanFields)) {
					for (Field beanField : beanFields) {
						if (beanField.isAnnotationPresent(Inject.class)) {
							Class<?> beanFieldClass = beanField.getType();
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							if (beanFieldInstance != null) {
								ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
							}
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		CustomerController controller = BeanHelper.getBean(CustomerController.class);
		System.out.println(controller);
		System.out.println(controller.customerService);
	}
}
