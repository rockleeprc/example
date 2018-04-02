package decorator;

/**
 * 饮料的抽象类
 * 
 * Component组件，由抽象类、接口定义
 * 
 * @author Rock.Lee
 *
 */
public abstract class Beverage {

	protected String description = "Unknown Beverage";

	/**
	 * 子类实现
	 * @return
	 */
	public abstract double cost();

	public String getDescription() {
		return description;
	}

}
