package factory.calculate;

public class MinusFactory extends AbstractCalculatorFactory {

	@Override
	Calculator createOperator() {
		return new Minus();
	}

}
