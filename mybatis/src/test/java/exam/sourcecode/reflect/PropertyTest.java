package exam.sourcecode.reflect;

import org.apache.ibatis.reflection.property.PropertyCopier;
import org.apache.ibatis.reflection.property.PropertyNamer;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.junit.Test;

import exam.mybatis.model.User;

public class PropertyTest {

	/**
	 * 同类对象属性拷贝
	 */
	@Test
	public void testPropertyCopier() {
		User u1 = new User();
		u1.setId(1);
		u1.setAddress("BJ");
		User u2 = new User();
		PropertyCopier.copyBeanProperties(User.class, u1, u2);
		System.out.println(u2);
	}

	/**
	 * 方法名->属性名转换
	 */
	@Test
	public void testPropertyNamer() {
		System.out.println(PropertyNamer.isGetter("getName"));
		System.out.println(PropertyNamer.isGetter("geName"));
		System.out.println(PropertyNamer.methodToProperty("getName"));
		System.out.println(PropertyNamer.methodToProperty("isFemale"));
		// System.out.println(PropertyNamer.methodToProperty("xxFemale"));
		System.out.println(PropertyNamer.isProperty("isTrue"));
		System.out.println(PropertyNamer.isProperty("true"));
	}

	/**
	 * 解谐resultMap中的property
	 */
	@Test
	public void testPropertyTokenizer() {
		String property = "orders[0].litems[0].name";
		PropertyTokenizer pt = new PropertyTokenizer(property);
		System.out.println(pt.getName());
		System.out.println(pt.getIndex());
		System.out.println(pt.getIndexedName());
		System.out.println(pt.getChildren());
	}
}
