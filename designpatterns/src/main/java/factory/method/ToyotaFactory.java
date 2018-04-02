package factory.method;

import factory.Benz;
import factory.Car;
import factory.Toyota;

public class ToyotaFactory implements FactoryMethod {

	@Override
	public Car getCar() {
		return new Toyota();
	}

}
