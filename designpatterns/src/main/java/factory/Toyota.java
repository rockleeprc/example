package factory;

public class Toyota implements Car {

	public Car getCar() {
		return new Toyota();
	}

}
