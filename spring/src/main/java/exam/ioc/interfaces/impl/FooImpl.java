package exam.ioc.interfaces.impl;

import exam.ioc.interfaces.Bar;
import exam.ioc.interfaces.Foo;

public class FooImpl implements Foo {
	private Bar bar;

	@Override
	public void method() {
		System.out.println("FooImpl");
	}

	public Bar getBar() {
		return bar;
	}

	public void setBar(Bar bar) {
		this.bar = bar;
	}


}
