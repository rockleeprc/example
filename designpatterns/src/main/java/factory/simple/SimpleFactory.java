package factory.simple;

import factory.BMW;
import factory.Benz;
import factory.Car;
import factory.Toyota;

public class SimpleFactory {
	public Car getInstance(String type) {
		switch (type) {
		case "BMW":
			return new BMW();
		case "Benz":
			return new Benz();
		case "Toyota":
			return new Toyota();
		default:
			return null;
		}
	}
}
