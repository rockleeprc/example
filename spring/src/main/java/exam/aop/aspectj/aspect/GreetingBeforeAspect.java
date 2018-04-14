package exam.aop.aspectj.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

//标识该类为一个切面
@Aspect
public class GreetingBeforeAspect {

	/**
	 * @Before：advice类型
	 * execution(* greetTo(..))：pointcut表达式
	 * beoreGreeting()：advice横切逻辑
	 */
	@Before("execution(* greetTo(..))")
	public void beforeGreeting() {
		System.out.println("How are you。。。。");
	}
	
//	@AfterReturning("execution(* serveTo(..))")
	@After("execution(* serveTo(..))")
	public void afterGreeting() {
		System.out.println("say goodby。。。。");
	}

	@Around("execution(* greetTo(..))")
	public void aroundServeTo(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("before around ");
		System.out.println(pjp.getTarget().getClass());
		Object[] args = pjp.getArgs();
		System.out.println(args[0]);
		pjp.proceed();
		System.out.println("after around ");
	}
}
