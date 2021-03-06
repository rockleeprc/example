package decorator;

/**
 * 调料的装饰者类
 *
 * Decorator组件 ，共同实现的抽象类（接口）
 * 
 * @author Rock.Lee
 *
 */
public abstract class CondimentDecorator extends Beverage {

	/**
	 * 完整的得到调料的描述信息，所有的调料类都需要实现该方法
	 */
	public abstract String getDescription();
}
