package factory.method;

import factory.Car;

/**
 * 工厂方法：<br/>
 * 1、定义创建对象的接口，由子类决定实例化哪个对象<br/>
 * 2、工厂方法让类的实例化推迟到了子类<br/>
 * 
 * @author Administrator
 *
 */
public abstract class CarStore {

	/**
	 * 实例化哪种Car依赖子类决定
	 * 
	 * @param type
	 * @return
	 */
	public Car getCar(String type) {
		Car car = createCar(type);
		System.out.println(car);
		return car;

	}

	/**
	 * 工厂方法，用于区别不同产品实现
	 * 
	 * @param type
	 * @return
	 */
	protected abstract Car createCar(String type);

}
