package factory.calculate;

public class CalculatorSimpleFactory {

	public Calculator getInstance(char type) {
		switch (type) {
		case '+':
			return new Plus();
		case '-':
			return new Minus();
		default:
			return null;
		}
	}
}
