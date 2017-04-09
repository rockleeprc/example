package exam.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

import exam.aop.interfaces.IBusiness;
import exam.aop.interfaces.impl.BusinessImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 *	cglib：性能比jdk高，但创建代理对象花费的时间比jdk多，适合singleton对象，不能针对final class/method<br/>
 *	jdk：性能不如cglib，但创建代理时间较少 
 *
 */
public class BusinessProxy {

	/**
	 * cglib代理，为目标类生成子类，在子类中调用父类的方法
	 */
	@Test
	public void cglibProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(BusinessImpl.class);
		enhancer.setCallback(new MethodInterceptor() {
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.out.println("cglib proxy starting...");
				System.out.println(obj.getClass().getName() + "." + method.getName());
				// 通过代理调用父类中的方法
				Object result = proxy.invokeSuper(obj, args);
				return result;
			}
		});
		IBusiness business = (IBusiness) enhancer.create();
		business.doSomething();

	}

	/**
	 * jdk动态代理，针对接口
	 */
	@Test
	public void jdkProxy() {
		// 被代理对象
		final IBusiness target = new BusinessImpl();
		IBusiness proxy = (IBusiness) Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), new InvocationHandler() {

					/**
					 * 将被代理对象逻辑和横切代码编织在一起
					 */
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("jdk proxy starting...");
						System.out.println(target.getClass().getName() + "." + method.getName());
						// invoke 调用被代理的对象
						Object obj = method.invoke(target, args);
						return obj;
					}
				});
		proxy.doSomething();
	}

}
