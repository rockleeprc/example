package factory.test;

import org.junit.Test;

import factory.Car;
import factory.method.BMWCarStore;
import factory.method.BMWFactory;
import factory.method.BenzFactory;
import factory.method.CarStore;
import factory.method.FactoryMethod;
import factory.simple.SimpleFactory;

public class FactoryTest {

	/**
	 * 工厂方法
	 */
	@Test
	public void testAbstract() {
		CarStore factory = new BMWCarStore();
		Car car = factory.getCar("bmw");
	}

	/**
	 * 工厂方法
	 */
	@Test
	public void testMethodFactory() {
		FactoryMethod facotry = new BenzFactory();
		Car benz = facotry.getCar();
		System.out.println(benz);

		facotry = new BMWFactory();
		Car bmw = facotry.getCar();
		System.out.println(bmw);
	}

	/**
	 * 简单工厂
	 */
	@Test
	public void testSimpleFactory() {
		SimpleFactory facotry = new SimpleFactory();
		Car bmw = facotry.getInstance("BMW");
		System.out.println(bmw);

		Car benz = facotry.getInstance("Benz");
		System.out.println(benz);

		Car toyota = facotry.getInstance("Toyota");
		System.out.println(toyota);

		Car honda = facotry.getInstance("Honda");
		System.out.println(honda);
	}
}
