package factory.method;

import factory.BMW;
import factory.Car;

public class BMWFactory implements FactoryMethod {

	@Override
	public Car getCar() {
		return new BMW();
	}

}
