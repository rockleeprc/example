package proxy.myproxy;

import java.lang.reflect.Method;

public interface BaseInvocationHandler {
	Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
