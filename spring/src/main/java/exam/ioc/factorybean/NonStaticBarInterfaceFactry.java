package exam.ioc.factorybean;

import exam.ioc.interfaces.Bar;
import exam.ioc.interfaces.impl.BarImpl;

public class NonStaticBarInterfaceFactry {

	public Bar getInstace() {
		return new BarImpl();
	}
}
