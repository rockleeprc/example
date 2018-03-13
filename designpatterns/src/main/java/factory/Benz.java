package factory;

public class Benz implements Car {

	public Car getCar() {
		return new Benz();
	}

}
