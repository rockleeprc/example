package org.framework;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.framework.annotation.Inject;
import org.smart.controller.CustomerController;

/**
 * 
 * @author Administrator
 *
 */
public class Test {
	public static void main(String[] args) throws Exception {
		String packagePath = "org.smart";
		Set<Class<?>> beanClassSet = getBeanClassSet(packagePath);
		Map<Class<?>, Object> ioc = getIocContainer(beanClassSet);
		inject(ioc);
		CustomerController controller = (CustomerController) ioc.get(CustomerController.class);
		System.out.println(controller);
		System.out.println(controller.customerService);
	}

	private static void inject(Map<Class<?>, Object> ioc) throws IllegalArgumentException, IllegalAccessException {
		for (Map.Entry<Class<?>, Object> entry : ioc.entrySet()) {
			Class<?> key = entry.getKey();
			Object value = entry.getValue();
			Field[] fields = key.getFields();
			for (Field f : fields) {
				if (f.isAnnotationPresent(Inject.class)) {
					f.set(value, ioc.get(f.getType()));
				}
			}
		}
	}

	private static Map<Class<?>, Object> getIocContainer(Set<Class<?>> beanClassSet) throws Exception {
		Map<Class<?>, Object> map = new HashMap<>();
		for (Class<?> cls : beanClassSet) {
			Object instance = cls.newInstance();
			map.put(cls, instance);
		}
		return map;
	}

	private static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	private static Set<Class<?>> getBeanClassSet(String packagePath) throws Exception {
		Set<Class<?>> classSet = new HashSet<>();
		Enumeration<URL> resources = getClassLoader().getResources(packagePath.replace(".", "/"));
		while (resources.hasMoreElements()) {
			URL url = resources.nextElement();
			if ("file".equals(url.getProtocol())) {
				loadClass(classSet, url.getPath(), packagePath);
			}
		}
		return classSet;
	}

	private static void loadClass(Set<Class<?>> classSet, String path, String packagePath)
			throws ClassNotFoundException {
		File[] files = new File(path)
				.listFiles((f) -> (f.isFile() && f.getName().endsWith(".class")) || f.isDirectory());
		for (File file : files) {
			if (file.isFile()) {
				String className = packagePath + "." + file.getName().substring(0, file.getName().lastIndexOf("."));
				Class<?> clazz = Class.forName(className, false, getClassLoader());
				classSet.add(clazz);
			} else {
				String subPath = path + "/" + file.getName().replace(".", "/");
				String subPackagePath = packagePath + "." + file.getName();
				loadClass(classSet, subPath, subPackagePath);
			}
		}
	}

}
