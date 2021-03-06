package decorator.condiment;

import decorator.Beverage;
import decorator.CondimentDecorator;

/**
 * 奶泡
 * 
 * ConcreteComponent组件，每一个ConcreteComponent都有一个Component实例引用
 * 扩展Component功能
 *
 * @author Rock.Lee
 *
 */
public class Whip extends CondimentDecorator {

	Beverage beverage;

	public Whip(Beverage beverage) {
		super();
		this.beverage = beverage;

	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ".Whip";
	}

	@Override
	public double cost() {

		return 0.40 + beverage.cost();
	}
}
