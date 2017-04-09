package decorator.condiment;

import decorator.Beverage;
import decorator.CondimentDecorator;

/**
 * 摩卡
 * 
 * 具体装饰者组件
 *
 * @author Rock.Lee
 *
 */
public class Mocha extends CondimentDecorator {

	Beverage beverage;

	public Mocha(Beverage beverage) {
		super();
		this.beverage = beverage;

	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ".Mocha";
	}

	@Override
	public double cost() {

		return 0.20 + beverage.cost();
	}
}
