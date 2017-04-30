package exam.aop.schema;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

public class AdviceMethod {

	public void beforeAdvice() {
		System.out.println("How are you。。。。");
	}

	public void aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("before around ");
		System.out.println(pjp.getTarget().getClass());
		Object[] args = pjp.getArgs();
		System.out.println(args[0]);
		pjp.proceed();
		System.out.println("after around ");
	}
}
