package decorator;

import org.junit.Test;

import decorator.coffee.DarkRoast;
import decorator.coffee.Espresso;
import decorator.condiment.Mocha;
import decorator.condiment.Whip;

public class Coffee {

	@Test
	public void espresso() {
		// 浓咖啡
		Beverage espresso = new Espresso();
		System.out.println(espresso.getDescription() + " $" + espresso.cost());

	}

	@Test
	public void darkRoast() {
		// 深烘焙咖啡
		Beverage darkRoast = new DarkRoast();
		// 调料：摩卡装饰*2，泡奶装饰*1
		darkRoast = new Mocha(darkRoast);
		darkRoast = new Mocha(darkRoast);
		darkRoast = new Whip(darkRoast);
		System.out.println(darkRoast.getDescription() + " $" + darkRoast.cost());
	}
}
