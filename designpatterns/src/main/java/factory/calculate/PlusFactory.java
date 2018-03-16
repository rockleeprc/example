package factory.calculate;

public class PlusFactory extends AbstractCalculatorFactory {

	@Override
	Calculator createOperator() {
		return new Plus();
	}

}
