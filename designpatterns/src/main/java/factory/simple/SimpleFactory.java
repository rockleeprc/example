package factory.simple;

import factory.BMW;
import factory.Benz;
import factory.Car;
import factory.Toyota;

/**
 * 对扩展开放，对修改封闭。静态工厂增加需要是修改源代码，对修改不封闭，不符合开闭原则。<br/>
 * 
 * 由一个工厂对象决定创建出哪一种产品类的实例<br/>
 * 
 * @author Administrator
 *
 */
public class SimpleFactory {
	public Car getInstance(String type) {
		switch (type) {
		case "BMW":
			return new BMW();
		case "Benz":
			return new Benz();
		case "Toyota":
			return new Toyota();
		default:
			return null;
		}
	}
}
