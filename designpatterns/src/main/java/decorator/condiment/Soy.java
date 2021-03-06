package decorator.condiment;

import decorator.Beverage;
import decorator.CondimentDecorator;

/**
 * 豆浆
 * 
 * ConcreteComponent组件，每一个ConcreteComponent都有一个Component实例引用
 * 扩展Component功能
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
