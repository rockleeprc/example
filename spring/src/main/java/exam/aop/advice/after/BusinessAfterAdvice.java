package exam.aop.advice.after;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;

public class BusinessAfterAdvice implements AfterReturningAdvice {

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("BusindessBeforeAdvice starting...");
		System.out.println("目标类："+target.getClass().getName() + "." + method.getName());
		System.out.println("返回值类型："+returnValue.getClass().getName());
	}

}
