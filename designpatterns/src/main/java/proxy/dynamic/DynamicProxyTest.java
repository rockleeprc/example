package proxy.dynamic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

import org.junit.Test;

import proxy.interfaces.IBusiness;
import proxy.interfaces.impl.BusinessImpl;


/**
 * @see http://blog.csdn.net/lh513828570/article/details/74078773
 * @author Administrator
 *
 */
public class DynamicProxyTest {

	/**
	 * test1简洁实现
	 */
	@Test
	public void test2() {
		final IBusiness target = new BusinessImpl();
		IBusiness proxy = (IBusiness) new MyInvocationHandler(target).getProxy();
		proxy.doSomething();
	}

	/**
	 * 详细步骤
	 * 
	 */
	@Test
	public void test1() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// 生成$Proxy0的class文件
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		// 获取动态代理类
		Class<?> proxyClazz = Proxy.getProxyClass(IBusiness.class.getClassLoader(), IBusiness.class);
		// 获得代理类的构造函数，并传入参数类型InvocationHandler.class
		Constructor<?> constructor = proxyClazz.getConstructor(InvocationHandler.class);
		// 通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
		IBusiness bussiness = (IBusiness) constructor.newInstance(new MyInvocationHandler(new BusinessImpl()));
		// 通过代理对象调用目标方法
		bussiness.doSomething();
	}

}
