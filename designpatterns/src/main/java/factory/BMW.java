package factory;

public class BMW implements Car{

	public Car getCar() {
		return new BMW();
	}

}
