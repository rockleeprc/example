package exam.aop.advice.after;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;

public class BusindessAfterAdvice implements AfterReturningAdvice {

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("BusindessBeforeAdvice starting...");
		System.out.println(target.getClass().getName() + "." + method.getName());
		System.out.println(returnValue.getClass().getName());
	}

}
