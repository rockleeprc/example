package org.framework.helper;

import java.util.HashSet;
import java.util.Set;

import org.framework.annotation.Controller;
import org.framework.annotation.Service;
import org.framework.util.ClassUtil;

public class ClassHelper {

	private static final Set<Class<?>> CLASS_SET;

	static {
		String basePackage = ConfigHelper.getAppBasePackage();
		System.out.println(basePackage);
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}

	public Set<Class<?>> getClassSet() {
		return CLASS_SET;
	}

	public static Set<Class<?>> getControllerClassSet() {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> clazz : CLASS_SET) {
			if (clazz.isAnnotationPresent(Controller.class)) {
				classSet.add(clazz);
			}
		}
		return classSet;
	}

	public static Set<Class<?>> getServiceClassSet() {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> clazz : CLASS_SET) {
			if (clazz.isAnnotationPresent(Service.class)) {
				classSet.add(clazz);
			}
		}
		return classSet;
	}

	public static Set<Class<?>> getBeanClassSet() {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		classSet.addAll(getControllerClassSet());
		classSet.addAll(getServiceClassSet());
		return classSet;
	}

	public static void main(String[] args) {
		Set<Class<?>> set = getControllerClassSet();
		System.out.println(set.size());
		for (Class<?> clazz : set) {
			System.out.println(clazz.getSimpleName());
		}
	}
}
