package exam.aop.advice.before;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class BusinessBeforeAdvice implements MethodBeforeAdvice {

	/**
	 * @param method  目标类方法
	 * @param args 目标类方法参数
	 * @param target 目标类实例
	 * @throws
	 */
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("BusindessBeforeAdvice starting...");
		System.out.println("目标类：" + target.getClass().getName() + "." + method.getName());
	}

}
