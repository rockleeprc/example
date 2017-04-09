package decorator.coffee;

import decorator.Beverage;

/**
 * 浓咖啡
 *
 * ConcreateComponent
 *
 * @author Rock.Lee
 *
 */
public class Espresso extends Beverage {

	public Espresso() {
		super();
		description = "浓咖啡";
	}

	/**
	 * 浓咖啡的价格，不需要管调料的价格	
	 */
	@Override
	public double cost() {
		return 1.99;
	}

}
