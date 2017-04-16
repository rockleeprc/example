package exam.aop.advice.before;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class BusinessBeforeAdvice implements MethodBeforeAdvice {

	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("BusindessBeforeAdvice starting...");
		System.out.println("目标类："+target.getClass().getName() + "." + method.getName());
	}

}
