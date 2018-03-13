package factory.method;

import factory.Car;

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

	protected abstract Car createCar(String type);

}
