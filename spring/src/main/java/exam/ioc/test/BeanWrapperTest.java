package exam.ioc.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import exam.pojo.Person;

public class BeanWrapperTest {

	/**
	 * BeanWrapper操作对象属性
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void beanWrapper() throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		Object person = Class.forName("exam.pojo.Person").newInstance();
		BeanWrapper personWrapper = new BeanWrapperImpl(person);
		personWrapper.setPropertyValue("name", "liyan");
		personWrapper.setPropertyValue("age", 18);

		assertTrue(personWrapper.getWrappedInstance() instanceof Person);
		assertEquals(person, personWrapper.getWrappedInstance());
		assertEquals("liyan", personWrapper.getPropertyValue("name"));
		assertEquals(18, personWrapper.getPropertyValue("age"));
	}

	/**
	 * 反射操作对象属性
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	@Test
	public void reflect() throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			NoSuchFieldException, SecurityException {
		Person person = (Person) Class.forName("exam.pojo.Person").newInstance();

		Class personClazz = person.getClass();
		Field ageField = personClazz.getDeclaredField("age");
		ageField.setAccessible(true);
		ageField.set(person, 18);

		Field nameField = personClazz.getDeclaredField("name");
		nameField.setAccessible(true);
		nameField.set(person, "liyan");

		assertEquals(18, ageField.get(person));
		assertEquals("liyan", nameField.get(person));
	}
}
