package decorator.coffee;

import decorator.Beverage;

/**
 * 深烘焙咖啡
 *
 * ConcreateComponent，扩展Component，被装饰的对象
 *
 * @author Rock.Lee
 *
 */
public class DarkRoast extends Beverage {

	public DarkRoast() {
		super();
		description = "深烘焙咖啡";
	}

	/**
	 * 浓咖啡的价格，不需要管调料的价格	
	 */
	@Override
	public double cost() {
		return 0.99;
	}

}
