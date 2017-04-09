package decorator.condiment;

import decorator.Beverage;
import decorator.CondimentDecorator;

/**
 * 豆浆 
 * 
 * 具体装饰者组件
 *
 * @author Rock.Lee
 *
 */
public class Soy extends CondimentDecorator {

	Beverage beverage;

	public Soy(Beverage beverage) {
		super();
		this.beverage = beverage;

	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ".Soy";
	}

	@Override
	public double cost() {

		return .40 + beverage.cost();
	}
}
