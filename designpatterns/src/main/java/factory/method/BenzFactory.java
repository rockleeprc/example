package factory.method;

import factory.Benz;
import factory.Car;

public class BenzFactory implements FactoryMethod {

	@Override
	public Car getCar() {
		return new Benz();
	}

}
