package factory.actract;

public class BeanzFactory implements CarFactory {

	@Override
	public Car createCar() {
		return new BenzS();
	}

}
