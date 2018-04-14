package factory.actract;

public abstract class Beanz implements Car {

	@Override
	public String brand() {
		return "Beanz";
	}

	public void run() {
		System.out.println(this.brand() + this.version());
	}

}
