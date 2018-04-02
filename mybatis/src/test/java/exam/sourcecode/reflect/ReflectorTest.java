package exam.sourcecode.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.junit.Before;
import org.junit.Test;
import exam.mybatis.model.User;

public class ReflectorTest {
	Reflector userReflector;

	@Before
	public void init() {
		ReflectorFactory facotry = new DefaultReflectorFactory();
		userReflector = facotry.findForClass(User.class);
	}
	
	@Test
	public void testGetterType() {
		Class<?> clazzName = userReflector.getGetterType("userName");
		System.out.println(clazzName.getName());
		
		Class<?> clazzSex = userReflector.getGetterType("sex");
		System.out.println(clazzSex.getName());
		
	}

	@Test
	public void testGetInvoker() throws IllegalAccessException, InvocationTargetException, InstantiationException {
		Invoker invoker = userReflector.getGetInvoker("sex");
		System.out.println(invoker);
		Class<?> clazz = invoker.getType();
		System.out.println(clazz);
	}

	@Test
	public void testHasGetter() {
		System.out.println(userReflector.hasGetter("userName"));
		System.out.println(userReflector.hasGetter("age"));
		System.out.println(userReflector.hasGetter("sex"));
	}

	@Test
	public void testGetGetablePropertyNames() {
		String[] propertyNames = userReflector.getGetablePropertyNames();
		for (String property : propertyNames) {
			System.out.println(property);
		}
	}

	@Test
	public void testGetDefaultConstructor() {
		Constructor<?> defaultConstructor = userReflector.getDefaultConstructor();
		System.out.println(defaultConstructor.getName());
	}
}
