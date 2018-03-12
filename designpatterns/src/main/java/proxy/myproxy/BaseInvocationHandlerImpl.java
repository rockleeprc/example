package proxy.myproxy;

import java.lang.reflect.Method;

import proxy.interfaces.IBusiness;

public class BaseInvocationHandlerImpl implements BaseInvocationHandler {

	private IBusiness target;

	public Object getInstance(IBusiness target) throws Exception {
		this.target = target;
		Class<?> clazz = target.getClass();
		// 用来生成一个新的对象（字节码重组来实现）
		return MyProxy.newProxyInstance(new MyClassLoader(), clazz.getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("start...这是BaseInvocationHandler");
		method.invoke(this.target, args);
		System.out.println("end...这是BaseInvocationHandler");
		return null;
	}

}
