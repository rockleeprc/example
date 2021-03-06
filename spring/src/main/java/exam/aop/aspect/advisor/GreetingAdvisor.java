package exam.aop.aspect.advisor;

import java.lang.reflect.Method;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import exam.aop.interfaces.IWaiter;

/**
 * 
 * 根据类的方法签名匹配
 * @author Rock.Lee
 *
 */
public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor {

	/**
	 * Pointcut 方法匹配规则
	 */
	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		return "greetTo".equals(method.getName());
	}

	/**
	 * Pointcut 类匹配规则
	 */
	@Override
	public ClassFilter getClassFilter() {
		ClassFilter classFilter = new ClassFilter() {
			/**
			 * 匹配Watier类或Watier子类
			 */
			@Override
			public boolean matches(Class<?> clazz) {
				return IWaiter.class.isAssignableFrom(clazz);
			}
		};
		return classFilter;
	}

}
