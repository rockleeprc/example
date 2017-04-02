package exam.aop.interfaces.impl;

import exam.aop.interfaces.IBusiness;

public class BusinessImpl implements IBusiness {

	public void doSomething() {
		System.out.println("BusinessImpl.doSomething()");
	}

	public String doReturn() {
		return "doReturn";
	}

}
