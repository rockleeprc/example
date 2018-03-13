package factory.method;

import factory.BMW;
import factory.Car;

public class BMWCarStore extends CarStore {

	@Override
	public Car createCar(String type) {
		System.out.println(type);
		return new BMW();
	}

}
