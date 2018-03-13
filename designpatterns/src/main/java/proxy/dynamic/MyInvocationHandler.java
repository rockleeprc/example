package proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {
	private Object target;

	public MyInvocationHandler(Object targe) {
		super();
		this.target = targe;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("------------------before------------------");
		Object result = method.invoke(target, args);
		System.out.println("-------------------after------------------");
		return result;
	}

	/**
	 * 获取目标对象的代理对象
	 * 
	 * @return 代理对象
	 */
	public Object getProxy() {
		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(),
				this);
	}

}
