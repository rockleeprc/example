package decorator.condiment;

import decorator.Beverage;
import decorator.CondimentDecorator;

/**
 * 摩卡
 * 
 * ConcreteComponent组件，每一个ConcreteComponent都有一个Component实例引用
 * 扩展Component功能
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
