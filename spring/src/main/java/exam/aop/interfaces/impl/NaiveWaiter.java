package exam.aop.interfaces.impl;

import exam.aop.interfaces.IWaiter;

public class NaiveWaiter implements IWaiter {

	@Override
	public void greetTo(String name) {
		System.out.println("waiter greet to " + name);
	}

	@Override
	public void serveTo(String name) {
		System.out.println("waiter serve to " + name);
//		throw new RuntimeException("exam.aop.interfaces.impl.NaiveWaiter.serveTo(String) 抛出异常");
	}
}
