package exam.aop.advice.after;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;

public class BusinessAfterAdvice implements AfterReturningAdvice {
	/**
	 * @param returnValue 目标类方法返回结果
	 * @param method  目标类方法
	 * @param args 目标类方法参数
	 * @param target 目标类实例
	 * @throws
	 */
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("BusindessBeforeAdvice starting...");
		System.out.println("目标类：" + target.getClass().getName() + "." + method.getName());
		System.out.println("返回值类型：" + returnValue.getClass().getName());
	}

}
