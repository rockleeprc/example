package exam.aop.aspect.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class GreetingBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println(target.getClass().getName() + "." + method.getName());
		String name = (String) args[0];
		System.out.println("参数："+name);
		System.out.println("exam.aop.aspect.advice.GreetingBeforeAdvice.before(Method, Object[], Object)...");
	}

}
