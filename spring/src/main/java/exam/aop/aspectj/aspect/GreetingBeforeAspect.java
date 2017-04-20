package exam.aop.aspectj.aspect;

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
	public void beoreGreeting() {
		System.out.println("How are you。。。。");
	}
}
